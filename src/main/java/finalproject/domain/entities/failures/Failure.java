package finalproject.domain.entities.failures;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Failure extends Throwable{
  public String message;

}
