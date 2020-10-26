package hello.hellospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
  private String response;
  private String message;
  private Object data;

  public Response() {

  }
}
