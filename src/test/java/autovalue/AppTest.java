package autovalue;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class AppTest implements WithAssertions {

    @Test
    void immutability() {
        // Create 2 lists containing the same element
        final List<String> myList1 = new ArrayList<>();
        myList1.add("OneValue");
        final List<String> myList2 = Collections.singletonList("OneValue");

        // Create model 1, assigning the list1
        MyModel myModel1 = new AutoValue_MyModel.Builder()
                .setMyOptional(Optional.of(1)) // 😥 🔴 No helper for Optional
                .setMyString("Hello")
                .setMyList(myList1) // 😥 🔴 No helper for List
                .build();

        // Create model 2, assigning the list2
        MyModel myModel2 = new AutoValue_MyModel.Builder()
                .setMyOptional(Optional.of(1))
                .setMyString("Hello")
                .setMyList(myList2)
                .build();

        // Compare the 2 objects
        // Test passes since the fields contain the same values
        assertThat(myModel1).isEqualTo(myModel2);

        // Mutate the list used on Model 1
        myList1.add("AnotherValue");

        // Compare the 2 objects:
        //   - Test FAILS for AutoValue 😮 🔴
        assertThat(myModel1).isEqualTo(myModel2);
    }
}
