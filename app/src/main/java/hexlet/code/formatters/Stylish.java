package hexlet.code.formatters;

import hexlet.code.Node;
import java.util.List;

public class Stylish {

    public static String format(List<Node> nodes) {
        StringBuilder result = new StringBuilder("{\n");
        for (Node node : nodes) {
            switch (node.getStatus()) {
                case UNCHANGED -> result.append(line(' ', node.getKey(), node.getOldValue()));
                case CHANGED -> {
                    result.append(line('-', node.getKey(), node.getOldValue()));
                    result.append(line('+', node.getKey(), node.getNewValue()));
                }
                case REMOVED -> result.append(line('-', node.getKey(), node.getOldValue()));
                case ADDED -> result.append(line('+', node.getKey(), node.getNewValue()));
            }
        }
        result.append("}");
        return result.toString();
    }

    private static String line(char sign, String key, Object value) {
        String prefix = sign == ' ' ? "    " : "  " + sign + " ";
        return String.format("%s%s: %s%n", prefix, key, stringify(value));
    }

    private static String stringify(Object value) {
        return String.valueOf(value);
    }
}
