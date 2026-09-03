package hexlet.code.formatters;

import hexlet.code.Node;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String format(List<Node> nodes) {
        StringBuilder result = new StringBuilder();
        for (Node node : nodes) {
            switch (node.getStatus()) {
                case ADDED ->
                        result.append(
                                String.format(
                                        "Property '%s' was added with value: %s%n",
                                        node.getKey(), stringify(node.getNewValue())));
                case REMOVED ->
                        result.append(String.format("Property '%s' was removed%n", node.getKey()));
                case CHANGED ->
                        result.append(
                                String.format(
                                        "Property '%s' was updated. From %s to %s%n",
                                        node.getKey(),
                                        stringify(node.getOldValue()),
                                        stringify(node.getNewValue())));
                case UNCHANGED -> {}
            }
        }
        return result.toString().stripTrailing();
    }

    private static String stringify(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}
