package OP4_Pruefung;

public class Mission {

    private String target;
    private String description;
    private String location;

    public Mission(String target, String description, String location) {
        this.target = target;
        this.description = description;
        this.location = location;
    }

    public String getTarget() { return target; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
}
