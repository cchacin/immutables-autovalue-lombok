package records;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class JacksonShould implements WithAssertions {

    public static record Person(
            @JsonProperty("firstName")
            String firstName,
            @JsonProperty("lastName")
            String lastName,
            @JsonProperty("address")
            String address,
            @JsonProperty("birthday")
            LocalDate birthday,
            @JsonProperty("achievements")
            List<String>achievements) {

        @JsonCreator
        public Person(
                @JsonProperty("firstName")
                        String firstName,
                @JsonProperty("lastName")
                        String lastName,
                @JsonProperty("address")
                        String address,
                @JsonProperty("birthday")
                        LocalDate birthday,
                @JsonProperty("achievements")
                        List<String> achievements) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.birthday = birthday;
            this.achievements = achievements;
        }
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
                  "firstName" : "John",
                  "lastName" : "Doe",
                  "address" : "USA",
                  "birthday" : "1990-11-11",
                  "achievements" : [ "Speaker" ]
                }""";

        // When
        var jsonb = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class records.JacksonShould$Person and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
        var serialized = jsonb.writeValueAsString(person);
        var deserialized = jsonb.readValue(json, Person.class);

        // Then
        assertThat(serialized).isEqualTo(json);
        assertThat(deserialized).isEqualTo(person);
    }


}
