package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;

public class Formatter {

    public static String format(List<Node> nodes, String formatName) {
        return switch (formatName) {
            case "stylish" -> Stylish.format(nodes);
            case "plain" -> Plain.format(nodes);
            default -> throw new IllegalArgumentException("Unknown format: " + formatName);
        };
    }
}
