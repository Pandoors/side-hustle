package pl.sidehustle.app.sidehustle.enums;

public enum JobType {
    PHYSICAL("Fizyczna"),
    MENTAL("Umysłowa");

    private final String type;

    JobType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
