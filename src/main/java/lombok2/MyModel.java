package lombok2;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Optional;

@Value
@Builder
public class MyModel {
    Optional<Integer> myOptional;

    String myString;

    List<String> myList;
}