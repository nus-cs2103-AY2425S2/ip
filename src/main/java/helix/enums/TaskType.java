package helix.enums;

/**
 * Represents the type of tasks in the system.
 */
public enum TaskType {

    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String code;

    /**
     * Constructs a TaskType with the specified code.
     *
     * @param code the short representation of the helix.task type.
     */
    TaskType(String code) {
        this.code = code;
    }

    /**
     * Returns the code representing the helix.task type.
     *
     * @return the helix.task type code.
     */
    public String getCode() {
        return this.code;
    }
}
