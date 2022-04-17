package domain.entities.failures;

public class BadRequestDto {
    private int statusCode;
    private String error;
    private FieldInvalidDto[] problems;
}
