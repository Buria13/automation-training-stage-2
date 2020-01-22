package by.epam.learn.threads;

public class ThreadsDemo {

    public static void main(String[] args) {
        Tunnel tunnelOne = new Tunnel("Tunnel ONE");
        Tunnel tunnelTwo = new Tunnel("Tunnel TWO");
        TunnelDispatcher dispatcher = new TunnelDispatcher(tunnelOne, tunnelTwo);

        for (int i = 0; i < 2; i++) {
            new Thread(new Train(dispatcher), "Train #" + i).start();
        }

        for (int j = 0; j < 8; j++) {
            new Thread(new Train(dispatcher), "Train #" + j).start();
        }

    }

}
