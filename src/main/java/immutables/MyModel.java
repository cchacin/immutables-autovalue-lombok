package immutables;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

@Value.Immutable
public interface MyModel {
    Optional<Integer> myOptional();

    String myString();

    List<String> myList();
}
