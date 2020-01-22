package by.epam.learn.threads;

public class Train implements Runnable {
    private TunnelDispatcher dispatcher;

    Train(TunnelDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - added to the queue");
        dispatcher.findFreeTunnel();
    }
}
