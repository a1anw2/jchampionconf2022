package is.alan.jchampionconf2022.aws.apigateway;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class Response extends APIGatewayProxyResponseEvent {

  public Response() {
    setStatusCode(200);
    setIsBase64Encoded(false);
    setHeaders(Map.of(
            "Access-Control-Allow-Origin", "*",
            "Access-Control-Allow-Credentials", "true",
            "Surrogate-Control", "no-store",
            "Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate",
            "Pragma", "no-cache",
            "Expires", "0"));
  }

  public Response setCode(int sc) {
    setStatusCode(sc);
    return this;
  }

  public Response body(Exception e) {
    setStatusCode(500);
    String message = e.getMessage() != null ? e.getMessage() : "";
    setBody(new GsonBuilder().create().toJson(Map.of("code", "500", "message", message)));
    return this;
  }

  public Response body(Map m) {
    setBody(new GsonBuilder().create().toJson(m));
    return this;
  }
}
