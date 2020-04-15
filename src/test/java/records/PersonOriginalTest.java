package records;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class PersonOriginalTest implements WithAssertions {
    
    @Test
    void immutability() {
        // Create a mutable list with 1 element
        var achievements1 = new ArrayList<String>();
        achievements1.add("Speaker");
        achievements1.add("Blogger");
        var achievements2 = List.of("Speaker", "Blogger");
        
        // Create person 1, assigning the list1
        var person1 = new PersonOriginal(
                "John",
                "Doe",
                "USA",
                LocalDate.of(1990, 11, 11),
                achievements1
        );

        // Create person 2, assigning the list2
        var person2 = new PersonOriginal(
                "John",
                "Doe",
                "USA",
                LocalDate.of(1990, 11, 11),
                achievements2
        );

        // Compare the 2 objects
        // Test passes since the fields contain the same values
        assertThat(person1).isEqualTo(person2);

        // Mutate the list used on Model 1
        achievements1.add("AnotherValue");

        // Compare the 2 objects:
        // - PASSES objects ARE EQUAL for POJO
        assertThat(person1).isNotEqualTo(person2);
    }
}