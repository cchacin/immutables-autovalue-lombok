package records;

import java.time.LocalDate;
import java.util.List;

public record Person(
        String firstName,
        String lastName,
        String address,
        LocalDate birthday,
        List<String> achievements
) {}
