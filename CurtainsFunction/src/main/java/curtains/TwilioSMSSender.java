package curtains;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioSMSSender {

    static {
        Twilio.init(Config.TWILIO_ACCOUNT_SID, Config.TWILIO_AUTH_TOKEN);
    }

    public void sendMessage(String message) {
        Message.creator(
            new PhoneNumber(Config.MY_PHONE_NUMBER),
            new PhoneNumber(Config.TWILIO_PHONE_NUMBER),
            message)
            .create();
    }
}
