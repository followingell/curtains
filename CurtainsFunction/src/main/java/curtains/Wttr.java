package curtains;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.apache.http.client.fluent.Request;

public class Wttr {

    public static class WttrResult {

        public final String temperature;
        public final String weather;
        public final String weatherEmoji;
        public final String humidity;
        public final String precipitationMM;
        public final String wind;
        public final String sunset;
        public final String moonPhaseEmoji;

        public WttrResult(String temperature, String weather, String weatherEmoji, String humidity, String precipitationMM, String wind, String sunset, String moonPhaseEmoji) {
            this.temperature = temperature;
            this.weather = weather;
            this.weatherEmoji = weatherEmoji;
            this.humidity = humidity;
            this.precipitationMM = precipitationMM;
            this.wind = wind;
            this.sunset = sunset;
            this.moonPhaseEmoji = moonPhaseEmoji;
        }
    }

    public WttrResult getTodaysWeather() throws IOException, ParseException {

        String url = String.format("https://wttr.in/%s?format=%%25t,%%25C,%%25c,%%25h,%%25p,%%25w,%%25s,%%25m&m", URLEncoder.encode(Config.WTTR_LOCATION, "UTF-8"));

        String[] response = Request
            .Get(URI.create(url))
            .execute()
            .returnContent()
            .asString()
            .split(",(?! )");

        response[1] = response[1].toLowerCase();

        LocalTime unformattedTime = LocalTime.parse(response[6], DateTimeFormatter.ofPattern("HH:mm:ss"));
        response[6] = unformattedTime.format(DateTimeFormatter.ofPattern("h:mm a"));

        return new WttrResult(response[0], response[1], response[2], response[3], response[4], response[5], response[6], response[7].trim());
    }

}
