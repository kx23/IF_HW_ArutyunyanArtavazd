// models/enums/IssueType.java
package models.enums;

public enum IssueType {

    TASK("Задача"),
    BUG("Ошибка");

    private final String value;

    IssueType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}