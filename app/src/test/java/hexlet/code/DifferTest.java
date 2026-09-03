package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static String getFixturePath(String fileName) {
        return Path.of("src", "test", "resources", "fixtures", fileName).toString();
    }

    private static String readFixture(String fileName) throws Exception {
        return Files.readString(Path.of(getFixturePath(fileName)));
    }

    @Test
    void generateFlatJsonStylishDiff() throws Exception {
        String expected = readFixture("expected_stylish.txt").stripTrailing();
        String actual = Differ.generate(getFixturePath("file1.json"), getFixturePath("file2.json"));
        assertEquals(expected, actual);
    }
}
