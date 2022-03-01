package is.alan.jchampionconf2022.endpoint;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import is.alan.jchampionconf2022.aws.apigateway.EndPointHandler;
import is.alan.jchampionconf2022.aws.apigateway.Response;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LifeCycle extends EndPointHandler {
  private static final Logger LOG = Logger.getLogger(LifeCycle.class);
  private InnerCountThread innerCountThread;

  public LifeCycle () {
    LOG.info("new LifeCycle()");
    innerCountThread = new InnerCountThread();
    innerCountThread.start();
  }

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
    LOG.info("LifeCycle.handleRequest()");

    Response res = getResponse();

    try {

      res.body(Map.of(
              "threadCount", innerCountThread.getCount(),
              "now", new Date(),
              "epoch", new Date().getTime()));

    } catch (Exception e) {
      LOG.error("/LifeCycle", e);
      res.body(e);
    }

    return res;
  }
}

class InnerCountThread extends Thread {
  private static final Logger LOG = Logger.getLogger(InnerCountThread.class);
  AtomicInteger count = new AtomicInteger(0);

  public InnerCountThread(){
    LOG.info("InnerCountThread.start");
  }

  public int getCount() {
    return count.get();
  }

  public void run() {
    for(;;) {
      try {
        sleep(1000);
        count.incrementAndGet();
      } catch (InterruptedException e) {
        break;
      }
    }

    LOG.info("InnerCountThread.end");
  }
}
