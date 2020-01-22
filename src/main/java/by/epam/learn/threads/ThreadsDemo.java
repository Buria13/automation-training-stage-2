package by.epam.learn.threads;

public class ThreadsDemo {

    public static void main(String[] args) {
        Tunnel tunnelOne = new Tunnel("Tunnel ONE");
        Tunnel tunnelTwo = new Tunnel("Tunnel TWO");

        for (int i = 1; i <= 2; i++) {
            Train train = new Train("Train #" + i, tunnelOne);
            train.thread.start();
        }

        for (int j = 3; j <= 9; j++) {
            Train train = new Train("Train #" + j, tunnelTwo);
            train.thread.start();
        }
    }

}
