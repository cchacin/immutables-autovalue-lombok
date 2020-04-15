package records;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public final class PersonOriginal {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final LocalDate birthday;
    private final List<String> achievements;

    public PersonOriginal(
            final String firstName,
            final String lastName,
            final String address,
            final LocalDate birthday,
            final List<String> achievements) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.achievements = achievements;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public List<String> getAchievements() {
        return List.copyOf(achievements);
    }

    @Override
    public String toString() {
        return "PersonOriginal{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", achievements=" + achievements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonOriginal that = (PersonOriginal) o;
        return getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getAddress().equals(that.getAddress()) &&
                getBirthday().equals(that.getBirthday()) &&
                getAchievements().equals(that.getAchievements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getAddress(), getBirthday(), getAchievements());
    }
}
