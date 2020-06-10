package curtains;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * TwilioSMSSender contains the method sendMessage which creates a new instance of the Message
 * resource and sends an HTTP POST to the Messages resource URI.
 */
public class TwilioSMSSender {

    // declared as static so that the Twilio client is not initialised multiple times if sending multiple messages
    static {
        Twilio.init(Config.TWILIO_ACCOUNT_SID, Config.TWILIO_AUTH_TOKEN);
    }

    /**
     * As per Twilio's documentation, "[sendMessage] creates a new instance of the Message resource
     * and sends an HTTP POST to the Messages resource URI.". See: https://www.twilio.com/docs/sms/quickstart/java#send-an-outbound-sms-message-with-java
     * @param message String. The message to be sent to the RECEIVER_PHONE_NUMBER.
     */
    public void sendMessage(String message) {
        Message.creator(new PhoneNumber(Config.RECEIVER_PHONE_NUMBER),
            new PhoneNumber(Config.TWILIO_PHONE_NUMBER), message).create();
    }
}
