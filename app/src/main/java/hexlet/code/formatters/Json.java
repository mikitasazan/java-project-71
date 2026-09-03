package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Node;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Json {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String format(List<Node> nodes) throws Exception {
        List<Map<String, Object>> entries =
                nodes.stream().map(Json::toEntry).collect(Collectors.toList());
        return MAPPER.writeValueAsString(entries);
    }

    private static Map<String, Object> toEntry(Node node) {
        Map<String, Object> entry = new LinkedHashMap<>();
        entry.put("key", node.getKey());
        entry.put("type", node.getStatus().name().toLowerCase());
        entry.put("oldValue", node.getOldValue());
        entry.put("newValue", node.getNewValue());
        return entry;
    }
}
