package lombok2;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class AppTest implements WithAssertions {

    @Test
    void immutability() {
        // Create a mutable list with 1 element
        var myList1 = new ArrayList<String>();
        myList1.add("OneValue");
        var myList2 = List.of("OneValue");

        // Create model 1, assigning the list1
        var myModel1 = MyModel.builder()
                .myOptional(Optional.of(1)) // 😥 🔴 No helper for Optional
                .myString("Hello")
                .myList(myList1) // 😥 🔴 No helper for List
                .build();

        // Create model 2, assigning the list2
        var myModel2 = MyModel.builder() // 😥 🔴 No helper for copying
                .myOptional(Optional.of(1))
                .myString("Hello")
                .myList(myList2)
                .build();

        // Compare the 2 objects
        // Test passes since the fields contain the same values
        assertThat(myModel1).isEqualTo(myModel2);

        // Mutate the list used on Model 1
        myList1.add("AnotherValue");

        // Compare the 2 objects:
        // - PASSES objects are NOT EQUAL for Lombok 😮 🔴
        assertThat(myModel1).isNotEqualTo(myModel2);
    }
}
