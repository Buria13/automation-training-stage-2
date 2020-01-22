package by.epam.learn.threads;

public class Tunnel  {
    private String name;
    private boolean status;

    public Tunnel(String name) {
        this.name = name;
        status = true;
    }

    public String getName() {
        return name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
}
