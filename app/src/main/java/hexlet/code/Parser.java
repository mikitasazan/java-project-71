package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content, String format) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }
}
