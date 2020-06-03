package records;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.config.PropertyVisibilityStrategy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

public class JsonbShould implements WithAssertions {

    public static record Person(
            String firstName,
            String lastName,
            String address,
            LocalDate birthday,
            List<String>achievements) {
        
        @JsonbCreator
        public Person {}
    }

    @Test
    void serializeRecords() throws Exception {
        // Given
        var person = new Person(
                "John",
                "Doe",
                "USA",
                LocalDate.of(1990, 11, 11),
                List.of("Speaker")
        );

        var json = """
                                   
                {
                    "achievements": [
                        "Speaker"
                    ],
                    "address": "USA",
                    "birthday": "1990-11-11",
                    "firstName": "John",
                    "lastName": "Doe"
                }""";

        // When
        var visibilityStrategy = new PropertyVisibilityStrategy() {
            @Override
            public boolean isVisible(Field field) {
                return true;
            }

            @Override
            public boolean isVisible(Method method) {
                return false;
            }
        };
        var jsonb = JsonbBuilder.create(
                new JsonbConfig()
                        .withFormatting(true)
                        .withPropertyVisibilityStrategy(visibilityStrategy)
        );
        var serialized = jsonb.toJson(person);
        var deserialized = jsonb.fromJson(json, Person.class);

        // Then
        assertThat(deserialized).isEqualTo(person);
        assertThat(serialized).isEqualTo(json);
    }


}
