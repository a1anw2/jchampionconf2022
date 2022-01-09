package is.alan.jchampionconf2022.endpoint;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import is.alan.jchampionconf2022.aws.apigateway.EndPointHandler;
import is.alan.jchampionconf2022.aws.apigateway.Response;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Map;

public class GetTime extends EndPointHandler {
  private static final Logger LOG = Logger.getLogger(GetTime.class);

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
    LOG.info("GetTime.handleRequest()");

    Response res = getResponse();

    try {

      res.body(Map.of(
              "now", new Date(),
              "epoch", new Date().getTime()));

    } catch (Exception e) {
      LOG.error("GetTime.catch", e);
      res.body(e);
    }

    return res;
  }
}
