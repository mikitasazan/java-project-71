package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DiffBuilder {

    public static List<Node> build(Map<String, Object> data1, Map<String, Object> data2) {
        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        List<Node> nodes = new ArrayList<>();
        for (String key : keys) {
            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);

            if (inFirst && inSecond) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);
                if (Objects.equals(value1, value2)) {
                    nodes.add(new Node(key, Node.Status.UNCHANGED, value1, value2));
                } else {
                    nodes.add(new Node(key, Node.Status.CHANGED, value1, value2));
                }
            } else if (inFirst) {
                nodes.add(new Node(key, Node.Status.REMOVED, data1.get(key), null));
            } else {
                nodes.add(new Node(key, Node.Status.ADDED, null, data2.get(key)));
            }
        }

        return nodes;
    }
}
