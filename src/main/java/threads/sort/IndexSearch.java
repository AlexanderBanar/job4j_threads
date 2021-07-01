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
        Integer leftResult = leftParallelSearch.join();
        Integer rightResult = rightParallelSearch.join();
        return (leftResult == null) ? rightResult : leftResult;
    }

    private Integer linearSearch() {
        int index = from;
        boolean flag = false;
        while (index <= to) {
            if (array[index].equals(subject)) {
                flag = true;
                break;
            }
            index++;
        }
        return (flag) ? index : null;
    }

    public static Integer search(Object[] array, Object subject) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new IndexSearch(array, subject, 0, array.length - 1));
    }

    public static void main(String[] args) {
        Integer[] array = {1, 2, 5, 8, 10, 22, 16, 18, 7, 4, 55, 56, 58, 59};
        Integer forSearch = 59;
        Integer index = IndexSearch.search(array, forSearch);
        System.out.println(index);
    }
}
