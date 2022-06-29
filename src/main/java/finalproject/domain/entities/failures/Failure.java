package finalproject.domain.entities.failures;


import lombok.Getter;
import lombok.Setter;

public class Failure {

  @Getter
  @Setter
  private String message;

  @Getter
  @Setter
  private String[] problems;

  public Failure(String message) {
    this.message = message;
  }
  public Failure(String message, String[] problems) {
    this.message = message;
    this.problems = problems;
  }

}
