package by.epam.learn.threads;

import java.util.concurrent.TimeUnit;

public class Train implements Runnable {
    Thread thread;
    final Tunnel tunnel;

    Train(String name, Tunnel tunnel) {
        this.thread = new Thread(this, name);
        this.tunnel = tunnel;
    }

    public void run() {
        //System.out.println(thread.getName() + " - добавлен в очередь");
        waitInQueue();
    }

    private void waitInQueue() {
        synchronized (tunnel) {

            while (!tunnel.getStatus()) {
                try {
                    tunnel.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

            passTunnel();
        }
    }

    private void passTunnel() {
        tunnel.setStatus(false);
        System.out.println(Thread.currentThread().getName() +
                " - начал движение по " + tunnel.getName());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(Thread.currentThread().getName() +
                " - прошёл " + tunnel.getName());
        tunnel.setStatus(true);
        tunnel.notify();
    }



}