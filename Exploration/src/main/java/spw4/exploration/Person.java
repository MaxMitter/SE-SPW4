package spw4.exploration;

public class Person {
    public static Person create(String name) {
        return new Person(name);
    }

    private int id;

    public int getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age) {
        this(-1, name, age);
    }

    public Person(String name) {
        this(-1, name, -1);
    }
}