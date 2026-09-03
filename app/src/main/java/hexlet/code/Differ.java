package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> data1 = readData(filePath1);
        Map<String, Object> data2 = readData(filePath2);

        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder("{\n");
        for (String key : keys) {
            boolean inFirst = data1.containsKey(key);
            boolean inSecond = data2.containsKey(key);

            if (inFirst && inSecond) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);
                if (Objects.equals(value1, value2)) {
                    result.append(formatLine(' ', key, value1));
                } else {
                    result.append(formatLine('-', key, value1));
                    result.append(formatLine('+', key, value2));
                }
            } else if (inFirst) {
                result.append(formatLine('-', key, data1.get(key)));
            } else {
                result.append(formatLine('+', key, data2.get(key)));
            }
        }
        result.append("}");

        return result.toString();
    }

    private static Map<String, Object> readData(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        String extension = getExtension(filePath);
        return Parser.parse(content, extension);
    }

    private static String getExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        return dotIndex == -1 ? "" : filePath.substring(dotIndex + 1);
    }

    private static String formatLine(char sign, String key, Object value) {
        String prefix = sign == ' ' ? "    " : "  " + sign + " ";
        return String.format("%s%s: %s%n", prefix, key, value);
    }
}
