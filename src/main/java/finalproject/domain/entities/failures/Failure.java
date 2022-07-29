package finalproject.domain.entities.failures;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** Общий класс всех ошибок.
 *
 */
public class Failure {

  @Setter
  private Messages message;

  @Getter
  @Setter
  private String[] problems;

  public String getMessage() {
    return this.message.stringMessage;
  }

  public Failure(Messages message) {
    this.message = message;
  }

  public Failure(Messages message, String[] problems) {
    this.message = message;
    this.problems = problems;
  }

  public Failure(Messages message, List<String> problemList) {
    this.message = message;
    this.problems = problemList.toArray(new String[0]);
  }

}
