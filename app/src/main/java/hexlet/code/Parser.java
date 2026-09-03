package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content, String format) throws Exception {
        ObjectMapper mapper = getMapper(format);
        return mapper.readValue(content, Map.class);
    }

    private static ObjectMapper getMapper(String format) {
        return switch (format) {
            case "json" -> JSON_MAPPER;
            case "yml", "yaml" -> YAML_MAPPER;
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
