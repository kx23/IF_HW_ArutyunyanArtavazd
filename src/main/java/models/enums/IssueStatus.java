package models.enums;

public enum IssueStatus {

    TO_DO("СДЕЛАТЬ"),
    IN_PROGRESS("В РАБОТЕ"),
    DONE("ГОТОВО");

    private final String value;

    IssueStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
