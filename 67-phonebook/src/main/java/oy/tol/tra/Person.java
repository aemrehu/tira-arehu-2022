package oy.tol.tra;

public class Person implements Comparable<Person> {
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

    @Override
    public boolean equals(Object person) {
        if (person instanceof Person) {
            return this.getFullName().equals(((Person)person).getFullName());
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Person person) {
        return this.getFullName().compareTo(person.getFullName());
    }

    @Override
    public int hashCode() {
        int hash = 5381;
        int x = 33;
        String name = getFullName();
        int length = name.length();
        for (int i=0; i<length; i++) {
            hash = (x * hash) + name.charAt(i);
        }
        return hash;
    }
}
