package src;

public class Task {
    private String name;
    private String description;


    Task(String name, String description){
        this.name = name;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}