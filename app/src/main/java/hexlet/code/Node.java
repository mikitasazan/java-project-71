package hexlet.code;

public class Node {

    public enum Status {
        ADDED,
        REMOVED,
        CHANGED,
        UNCHANGED
    }

    private final String key;
    private final Status status;
    private final Object oldValue;
    private final Object newValue;

    public Node(String key, Status status, Object oldValue, Object newValue) {
        this.key = key;
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getKey() {
        return key;
    }

    public Status getStatus() {
        return status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
