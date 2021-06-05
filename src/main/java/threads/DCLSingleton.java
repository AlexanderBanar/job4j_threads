package threads;

public class DCLSingleton {
    private static volatile DCLSingleton inst;

    public static synchronized DCLSingleton instOf() {
        if (inst == null) {
            inst = new DCLSingleton();
        }
        return inst;
    }

    private DCLSingleton() {
    }
}
