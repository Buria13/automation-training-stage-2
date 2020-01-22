package by.epam.learn.threads;

import java.util.concurrent.TimeUnit;

public class TunnelDispatcher {
    private final Tunnel tunnelOne;
    private final Tunnel tunnelTwo;

    public TunnelDispatcher(Tunnel tunnelOne, Tunnel tunnelTwo) {
        this.tunnelOne = tunnelOne;
        this.tunnelTwo = tunnelTwo;
    }

    public void findFreeTunnel() {
        try {
            if (tunnelOne.getLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    passTunnel(tunnelOne);
                } finally {
                    tunnelOne.getLock().unlock();
                }
            } else if (tunnelTwo.getLock().tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    passTunnel(tunnelTwo);
                } finally {
                    tunnelTwo.getLock().unlock();
                }
            } else {
                findFreeTunnel();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void passTunnel(Tunnel tunnel) {
        try {
            System.out.println(Thread.currentThread().getName() +
                    " - start moving through the " + tunnel.getName());
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
