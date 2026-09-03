package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {

    private static final String DEFAULT_FORMAT = "stylish";

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, DEFAULT_FORMAT);
    }

    public static String generate(String filePath1, String filePath2, String formatName)
            throws Exception {
        Map<String, Object> data1 = readData(filePath1);
        Map<String, Object> data2 = readData(filePath2);

        List<Node> diff = DiffBuilder.build(data1, data2);

        return Formatter.format(diff, formatName);
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
}
