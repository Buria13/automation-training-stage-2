package by.epam.learn.threads;

import java.util.concurrent.locks.ReentrantLock;

public class Tunnel  {
    private String name;
    private ReentrantLock lock = new ReentrantLock(true);

    public Tunnel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ReentrantLock getLock() {
        return lock;
    }

}
