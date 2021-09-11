package ua.fedii.servlet_demo.servlet.model;

public class User {
    private long id;
    private String firstName;
    private String secondName;
    private int age;
    private String email;

    public User() {
    }

    public User(String firstName, String secondName, int age, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
    }

    public User(long id, String firstName, String secondName, int age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
