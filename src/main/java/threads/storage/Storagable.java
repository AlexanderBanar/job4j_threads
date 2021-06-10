package threads.storage;

public interface Storagable {
    boolean add(Userable user);
    boolean update(Userable user);
    boolean delete(Userable user);
    boolean transfer(int fromId, int toId, int amount);
}
