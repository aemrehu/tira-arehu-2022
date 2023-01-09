package oy.tol.tra;

public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    // TODO: Implement equals(), hashCode() and Comparable interface.

    public boolean equals(Object person) {
        if (person instanceof Person) {
            return this.getFullName().equals(((Person)person).getFullName());
        } else {
            return false;
        }
    }

    public int compareTo(Person person) {
        return this.getFullName().compareTo(person.getFullName());
    }

    public int hashCode() {
        return this.getFullName().hashCode();
    }
}
