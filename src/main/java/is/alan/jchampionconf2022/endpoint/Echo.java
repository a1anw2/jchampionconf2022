package is.alan.jchampionconf2022.endpoint;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import is.alan.jchampionconf2022.aws.apigateway.EndPointHandler;
import is.alan.jchampionconf2022.aws.apigateway.Response;
import org.apache.log4j.Logger;

import java.util.Map;

public class Echo extends EndPointHandler {
  private static final Logger LOG = Logger.getLogger(GetTime.class);

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
    LOG.info("Echo.handleRequest()");

    Response res = getResponse();

    try {
      res.body(Map.of(
              "headers", apiGatewayProxyRequestEvent.getHeaders(),
              "pathparams", apiGatewayProxyRequestEvent.getPathParameters() != null ? apiGatewayProxyRequestEvent.getPathParameters() : "-" ,
              "queryparams", apiGatewayProxyRequestEvent.getQueryStringParameters() != null ? apiGatewayProxyRequestEvent.getQueryStringParameters() : "-" ,
              "variables", apiGatewayProxyRequestEvent.getStageVariables() != null ? apiGatewayProxyRequestEvent.getStageVariables() : "-" ,
              "body", apiGatewayProxyRequestEvent.getBody() != null ? apiGatewayProxyRequestEvent.getBody() : "-" ,
              "method", apiGatewayProxyRequestEvent.getHttpMethod() != null ? apiGatewayProxyRequestEvent.getHttpMethod() : "-" ,
              "path", apiGatewayProxyRequestEvent.getPath() != null ? apiGatewayProxyRequestEvent.getPath() : "-",
              "remainingTime", context.getRemainingTimeInMillis()
      ));
    } catch (Exception e) {
      LOG.error("Echo.catch", e);
      res.body(e);
    }

    return res;
  }
}