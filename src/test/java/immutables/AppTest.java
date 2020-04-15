package immutables;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AppTest implements WithAssertions {

    @Test
    void immutability() {
        // Create a mutable list with 1 element
        var myList1 = new ArrayList<String>();
        myList1.add("OneValue");
        var myList2 = List.of("OneValue");

        // Create model 1, assigning the list1
        var myModel1 = ImmutableMyModel.builder()
                .myOptional(1) // 🎩 ✅ Helper for Optional
                .myString("Hello")
                .myList(myList1)
                .build();

        // Create model 2, assigning the list2
        var myModel2 = ImmutableMyModel.builder()
                .from(myModel1) // 🎩 ✅ Helper for copying
                .myList(myList2)
                .build();

        // Compare the 2 objects
        // Test passes since the fields contain the same values
        assertThat(myModel1).isEqualTo(myModel2);

        // Mutate the list used on Model 1
        myList1.add("AnotherValue");

        // Compare the 2 objects:
        // - Test PASSES objects ARE EQUAL for Immutables 🎩 ✅
        assertThat(myModel1).isEqualTo(myModel2);
    }
}
