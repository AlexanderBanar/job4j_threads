package threads.storage;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStorage implements Storagable {
    private Map<Integer, User> store = new ConcurrentHashMap<>();

    @Override
    public synchronized boolean add(Userable user) {
        return store.putIfAbsent(user.getId(), (User) user) == null;
    }

    @Override
    public synchronized boolean update(Userable user) {
        return store.replace(user.getId(), (User) user) != null;
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
        if (fromUser != null && toUser != null) {
            int newAmountFromUser = fromUser.getAmount() - amount;
            if (newAmountFromUser > 0) {
                fromUser.setAmount(newAmountFromUser);
                toUser.setAmount(toUser.getAmount() + amount);
                result = true;
            }
        }
        return result;
    }
}
