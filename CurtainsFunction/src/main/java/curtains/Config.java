package curtains;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZoneId;

/**
 * Config file used to store environment variables and Objects used throughout the project.
 * <p>
 * Note that variables with System.getenv should be added in AWS Lambda Console:
 * https://docs.aws.amazon.com/lambda/latest/dg/configuration-envvars.html
 */
public class Config {

    // ZoneId used to format the current date taken from system
    protected static final ZoneId MY_TIMEZONE = ZoneId.of("Europe/London");

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    // for configuration options see here: https://github.com/chubin/wttr.in
    protected static final String WTTR_LOCATION = System.getenv("WTTR_LOCATION");

    // https://newsapi.org/docs/get-started
    protected static final String NEWSAPI_API_KEY = System.getenv("NEWSAPI_API_KEY");

    // https://open-platform.theguardian.com/access/
    protected static final String GUARDIAN_OPEN_PLATFORM_API_KEY = System.getenv("GUARDIAN_OPEN_PLATFORM_API_KEY");

    // find these here: https://www.twilio.com/console
    protected static final String TWILIO_ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    protected static final String TWILIO_AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    protected static final String TWILIO_PHONE_NUMBER = System.getenv("TWILIO_PHONE_NUMBER");

    // the phone number to send the message to, this must be a Twilio Verified Caller ID
    protected static final String RECEIVER_PHONE_NUMBER = System.getenv("RECEIVER_PHONE_NUMBER");
}
