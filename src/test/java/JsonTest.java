import modal.Dog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonTest {
    private ClassLoader cl = ZipTests.class.getClassLoader();

    @Test
    @DisplayName("Проверка json")
    void jsonParsing() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("dog.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            Dog dog = objectMapper.readValue(isr, Dog.class);
            Assertions.assertEquals("Buggy Strong Bear", dog.fullName);
            Assertions.assertEquals("Voronezh", dog.city.current);
            Assertions.assertEquals("Eating", dog.activities.get(2));

        }

    }

}
