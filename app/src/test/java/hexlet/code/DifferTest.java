package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DifferTest {

    private static String getFixturePath(String fileName) {
        return Path.of("src", "test", "resources", "fixtures", fileName).toString();
    }

    private static String readFixture(String fileName) throws Exception {
        return Files.readString(Path.of(getFixturePath(fileName)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    void generateFlatStylishDiff(String extension) throws Exception {
        String expected = readFixture("expected_stylish.txt").stripTrailing();
        String actual =
                Differ.generate(
                        getFixturePath("file1." + extension), getFixturePath("file2." + extension));
        assertEquals(expected, actual);
    }

    @Test
    void generateNestedStylishDiffDefaultsToStylish() throws Exception {
        String expected = readFixture("expected_stylish_nested.txt").stripTrailing();
        String actual =
                Differ.generate(getFixturePath("nested1.json"), getFixturePath("nested2.json"));
        assertEquals(expected, actual);
    }

    @Test
    void generateNestedPlainDiff() throws Exception {
        String expected = readFixture("expected_plain.txt").stripTrailing();
        String actual =
                Differ.generate(
                        getFixturePath("nested1.json"), getFixturePath("nested2.json"), "plain");
        assertEquals(expected, actual);
    }

    @Test
    void generateJsonDiffIsValidAndContainsExpectedEntries() throws Exception {
        String json =
                Differ.generate(getFixturePath("file1.json"), getFixturePath("file2.json"), "json");

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> entries =
                mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});

        Map<String, Object> timeoutEntry =
                entries.stream()
                        .filter(entry -> "timeout".equals(entry.get("key")))
                        .findFirst()
                        .orElseThrow();
        assertEquals("changed", timeoutEntry.get("type"));
        assertEquals(50, timeoutEntry.get("oldValue"));
        assertEquals(20, timeoutEntry.get("newValue"));

        Map<String, Object> hostEntry =
                entries.stream()
                        .filter(entry -> "host".equals(entry.get("key")))
                        .findFirst()
                        .orElseThrow();
        assertEquals("unchanged", hostEntry.get("type"));
    }
}
