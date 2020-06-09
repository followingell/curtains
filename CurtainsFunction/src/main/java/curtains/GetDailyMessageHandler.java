package curtains;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import java.io.IOException;

public class GetDailyMessageHandler {

    public void eventHandler(ScheduledEvent event, Context context) {
        try {
            String todaysMessage = new DailyMessage().getTodaysMessage();
            new TwilioSMSSender().sendMessage(todaysMessage);
            System.out.println("Message sent successfully!");
        } catch (IOException e) {
            System.err.println("Message did not send successfully!");
            e.printStackTrace();
        }
    }
}
