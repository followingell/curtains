package curtains;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import java.io.IOException;

/**
 * DailyMessageHandler contains the eventHandler method which Lambda runs once the function has been
 * invoked.
 */
public class DailyMessageHandler {

    /**
     * eventHandler is the method that is run by Lambda once the function has been invoked, in this
     * case by a ScheduledEvent from Amazon CloudWatch Events. If this happens successfully a
     * message is sent to the RECEIVER_PHONE_NUMBER.
     *
     * @param event   ScheduledEvent. The event that causes the invocation the lambda. See more:
     *                https://docs.aws.amazon.com/lambda/latest/dg/services-cloudwatchevents.html
     * @param context Provides information about the invocation, function, and execution
     *                environment. See more: https://docs.aws.amazon.com/lambda/latest/dg/java-context.html
     */
    public void eventHandler(ScheduledEvent event, Context context) {
        try {
            String todaysMessage = new DailyMessage().getTodaysMessage();
            System.out.println(todaysMessage);
            new TwilioSMSSender().sendMessage(todaysMessage);
            System.out.println("Message sent successfully!");
        } catch (IOException e) {
            System.err.println("Message did not send successfully!");
            e.printStackTrace();
        }
    }
}
