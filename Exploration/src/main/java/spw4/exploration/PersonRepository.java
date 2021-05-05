package spw4.exploration;
import java.util.*;
import java.util.stream.*;
public class PersonRepository {
    private Map<Integer, Person> persons;
    private int nextId;
    public PersonRepository() {
        persons = new HashMap<>();
        nextId = 0;
    }
    public void createPerson(Person p) {
        Person newPerson = new Person(nextId, p.getName(), p.getAge());
        persons.put(newPerson.getId(), newPerson);
        nextId++;
    }
    public Person readPersonById(int id) {
        return persons.get(id);
    }
    public Person readPersonByName(String name) {
        var entry = persons.entrySet().stream().filter(e -> name.equals(e.getValue().getName())).findFirst().orElse(null);
        return entry == null ? null : entry.getValue();
    }
    public Stream<Person> readAllPersons() {
        return persons.entrySet().stream().map(e -> e.getValue());
    }
    public boolean updatePerson(Person p) {
        if (!persons.containsKey(p.getId())) return false;
        persons.remove(p.getId());
        persons.put(p.getId(), p);
        return true;
    }
    public boolean deletePerson(int id) {
        if (!persons.containsKey(id)) return false;
        persons.remove(id);
        return true;
    }
}