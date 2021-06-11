package threads.storage;

import java.util.Objects;

public class User implements Userable {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int newId) {
        this.id = newId;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int newAmount) {
        this.amount = newAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}
