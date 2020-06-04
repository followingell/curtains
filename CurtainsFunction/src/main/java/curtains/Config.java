package curtains;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZoneId;

public class Config {

    public static final ZoneId MY_TIMEZONE = ZoneId.of("Europe/London");
    public static final String WTTR_LOCATION = "Stourbridge,+United+Kingdom";
    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String NEWSAPI_KEY = "<NEWSAPI_KEY>";

    public static final String TWILIO_ACCOUNT_SID = "<ACCOUNT_SID>";
    public static final String TWILIO_AUTH_TOKEN = "<AUTH_TOKEN>";
    public static final String TWILIO_PHONE_NUMBER = "<TWILIO PHONE NUMBER>";
    public static final String MY_PHONE_NUMBER = "<OWN MOBILE PHONE NUMBER>";
}
