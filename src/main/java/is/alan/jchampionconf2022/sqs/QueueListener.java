package is.alan.jchampionconf2022.sqs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.util.Map;

public class QueueListener implements RequestHandler<SQSEvent, Void> {
  private static final Logger LOG = Logger.getLogger(QueueListener.class);
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  @Override
  public Void handleRequest(SQSEvent sqsEvent, Context context) {

    for (SQSEvent.SQSMessage msg : sqsEvent.getRecords()) {
      Map props = GSON.fromJson(msg.getBody(), Map.class);
      LOG.info(props);
    }

    return null;
  }
}