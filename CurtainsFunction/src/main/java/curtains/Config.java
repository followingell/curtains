package curtains;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZoneId;

public class Config {

    public static final ZoneId MY_TIMEZONE = ZoneId.of("Europe/London");
    public static final String WTTR_LOCATION = "<LOCATION>";
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String NEWSAPI_KEY = "<NEWSAPI_KEY>";
    public static final String GUARDIAN_OPEN_PLATFORM_API_KEY = "<GUARDIAN_OPEN_PLATFORM_API_KEY>";

    public static final String TWILIO_ACCOUNT_SID = "<TWILIO_ACCOUNT_SID>";
    public static final String TWILIO_AUTH_TOKEN = "<TWILIO_AUTH_TOKEN>";

    public static final String TWILIO_PHONE_NUMBER = "<TWILIO_PHONE_NUMBER>";
    public static final String MY_PHONE_NUMBER = "<MY_PHONE_NUMBER>";
}
