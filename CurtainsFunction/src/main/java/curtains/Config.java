package curtains;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZoneId;

public class Config {

    protected static final ZoneId MY_TIMEZONE = ZoneId.of("Europe/London");
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    protected static final String WTTR_LOCATION = System.getenv("WTTR_LOCATION");

    protected static final String NEWSAPI_API_KEY = System.getenv("NEWSAPI_API_KEY");
    protected static final String GUARDIAN_OPEN_PLATFORM_API_KEY = System.getenv("GUARDIAN_OPEN_PLATFORM_API_KEY");

    protected static final String TWILIO_ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    protected static final String TWILIO_AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    protected static final String TWILIO_PHONE_NUMBER = System.getenv("TWILIO_PHONE_NUMBER");
    protected static final String RECEIVER_PHONE_NUMBER = System.getenv("RECEIVER_PHONE_NUMBER");
}
