package hexlet.code.formatters;

import hexlet.code.Node;
import java.util.List;

public class Formatters {

    public static String format(List<Node> nodes, String formatName) {
        return switch (formatName) {
            case "stylish" -> Stylish.format(nodes);
            default -> throw new IllegalArgumentException("Unknown format: " + formatName);
        };
    }
}
