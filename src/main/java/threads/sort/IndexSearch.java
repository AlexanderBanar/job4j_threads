package threads.sort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class IndexSearch extends RecursiveTask<Integer> {
    private final Object[] array;
    private final Object subject;
    private final int from;
    private final int to;

    public IndexSearch(Object[] array, Object subject, int from, int to) {
        this.array = array;
        this.subject = subject;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return linearSearch();
        }
        int mid = (from + to) / 2;
        IndexSearch leftParallelSearch = new IndexSearch(array, subject, from, mid);
        IndexSearch rightParallelSearch = new IndexSearch(array, subject, mid + 1, to);
        leftParallelSearch.fork();
        rightParallelSearch.fork();
        int leftResult = leftParallelSearch.join();
        int rightResult = rightParallelSearch.join();
        return (leftResult == -1) ? rightResult : leftResult;
    }

    private int linearSearch() {
        int index = from;
        boolean flag = false;
        for (int i = index; i <= to; i++) {
            if (array[i].equals(subject)) {
                flag = true;
                index = i;
                break;
            }
        }
        return (flag) ? index : -1;
    }

    public static Integer search(Object[] array, Object subject) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new IndexSearch(array, subject, 0, array.length - 1));
    }
}
