package finalproject.domain.entities.failures;

import java.util.List;

public class BadRequest extends Failure{
  public BadRequest(Messages message) {
    super(message);
  }

  public BadRequest(Messages message, String[] problems) {
    super(message, problems);
  }

  public BadRequest(Messages message, List<String> problemList) {
    super(message, problemList);
  }

}

