package ru.kiryanovid.domain.entity.errors;

public enum AlreadyExists implements Failure{
    ALREADY_EXISTS("Уже существует");
    private String message;

    AlreadyExists(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
