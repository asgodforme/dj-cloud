package heimaSf.tree.queue;

public class Entry implements Priority {
    private String name;
    private int priority;

    public Entry(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
