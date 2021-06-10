package threads.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStorage implements Storagable {
    private volatile Map<Integer, User> store = new ConcurrentHashMap<>();

    @Override
    public synchronized boolean add(Userable user) {
        User previousUser = store.put(user.getId(), (User) user);
        return previousUser == null;
    }

    @Override
    public synchronized boolean update(Userable user) {
        return add(user);
    }

    @Override
    public synchronized boolean delete(Userable user) {
        return store.remove(user.getId(), user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User fromUser = store.get(fromId);
        User toUser = store.get(toId);
        fromUser.setAmount(fromUser.getAmount() - amount);
        if (fromUser.getAmount() > 0) {
            toUser.setAmount(toUser.getAmount() + amount);
            store.put(fromId, fromUser);
            store.put(toId, toUser);
            result = true;
        }
        return result;
    }
}
