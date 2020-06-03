package curtains;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.client.fluent.Request;

public class Wttr {

    public static class WttrResult {

        public final String weather;
        public final String weatherEmoji;
        public final String temperature;
        public final String humidity;
        public final String precipitationMM;
        public final String sunset;
        public final String moonphaseEmoji;

        public WttrResult(String weather, String weatherEmoji, String temperature, String humidity, String precipitationMM, String sunset, String moonphaseEmoji) {
            this.weather = weather;
            this.weatherEmoji = weatherEmoji;
            this.temperature = temperature;
            this.humidity = humidity;
            this.precipitationMM = precipitationMM;
            this.sunset = sunset;
            this.moonphaseEmoji = moonphaseEmoji;
        }
    }

    public WttrResult getTodaysWeather() throws IOException {

        String url = String.format("https://wttr.in/%s?format=%%25C,%%25c,%%25t,%%25h,%%25p,%%25s,%%25m", URLEncoder.encode(Config.WTTR_LOCATION, "UTF-8"));

        String[] response = Request
            .Get(URI.create(url))
            .execute()
            .returnContent()
            .asString()
            .split(",");

        return new WttrResult(response[0], response[1], response[2], response[3], response[4], response[5], response[6].trim());
    }


}
