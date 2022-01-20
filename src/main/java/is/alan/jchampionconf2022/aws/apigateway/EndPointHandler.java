package is.alan.jchampionconf2022.aws.apigateway;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public abstract class EndPointHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  protected Response getResponse() {
    return new Response();
  }

  /**
   * Default handler
   *
   * @param apiGatewayProxyRequestEvent
   * @param context
   * @return
   */
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
    Response res = getResponse();
    res.body(new Exception("No Handler Implemented"));
    return res;
  }

}