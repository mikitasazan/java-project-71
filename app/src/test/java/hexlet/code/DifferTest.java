package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
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
}
