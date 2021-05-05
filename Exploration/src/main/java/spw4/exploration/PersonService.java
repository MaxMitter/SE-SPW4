package spw4.exploration;

import java.util.stream.Collectors;

public class PersonService {
    private PersonRepository rep = null;

    public PersonService(PersonRepository repo) {
        rep = repo;
    }

    public void register(String name, int age) {
        if (name.isBlank()) throw new IllegalArgumentException("invalid Name");
        if (age < 16) throw new IllegalArgumentException("Person must be over 16");

        Person p = new Person(name, age);
        rep.createPerson(p);
    }

    public double getAverageAge() {
        return rep.readAllPersons().collect(Collectors.averagingInt(p -> p.getAge()));
    }
}
