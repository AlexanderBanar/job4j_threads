package threads;

import com.google.gson.Gson;
import net.jcip.annotations.ThreadSafe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    private final List<T> list = new ArrayList<>();

    public SingleLockList() {
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        List<T> copiedList = copy(this.list);
        return copiedList.iterator();
    }

    private List<T> copy(List<T> list) {
        List<T> newList = new ArrayList<>();
        for (T value : list) {
            Gson gson = new Gson();
            T deepCopiedT = gson.fromJson(gson.toJson(value), (Type) value.getClass());
            newList.add(deepCopiedT);
        }
        return newList;
    }
}
