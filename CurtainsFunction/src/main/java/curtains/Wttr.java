package curtains;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.apache.http.client.fluent.Request;

public class Wttr {

    public static class WttrResult {

        private final String temperature;
        private final String weather;
        private final String weatherEmoji;
        private final String humidity;
        private final String precipitationMM;
        private final String wind;
        private final String sunset;
        private final String moonPhaseEmoji;

        public WttrResult(String temperature, String weather, String weatherEmoji, String humidity,
            String precipitationMM, String wind, String sunset, String moonPhaseEmoji) {
            this.temperature = temperature;
            this.weather = weather;
            this.weatherEmoji = weatherEmoji;
            this.humidity = humidity;
            this.precipitationMM = precipitationMM;
            this.wind = wind;
            this.sunset = sunset;
            this.moonPhaseEmoji = moonPhaseEmoji;
        }

        public String getTemperature() {
            return temperature;
        }

        public String getWeather() {
            return weather;
        }

        public String getWeatherEmoji() {
            return weatherEmoji;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getPrecipitationMM() {
            return precipitationMM;
        }

        public String getWind() {
            return wind;
        }

        public String getSunset() {
            return sunset;
        }

        public String getMoonPhaseEmoji() {
            return moonPhaseEmoji;
        }

    }

    public WttrResult getTodaysWeather() throws IOException {

        String url = String.format("https://wttr.in/%s?format=%%25t,%%25C,%%25c,%%25h,%%25p,%%25w,%%25s,%%25m&m",
                URLEncoder.encode(Config.WTTR_LOCATION, "UTF-8"));

        String[] response = Request
            .Get(URI.create(url))
            .execute()
            .returnContent()
            .asString()
            .split(",(?! )");

        // format responses
        response[1] = response[1].toLowerCase();

        LocalTime unformattedTime = LocalTime.parse(response[6], DateTimeFormatter.ofPattern("HH:mm:ss"));
        response[6] = unformattedTime.format(DateTimeFormatter.ofPattern("h:mm a"));

        return new WttrResult(response[0], response[1], response[2], response[3], response[4],
            response[5], response[6], response[7].trim());
    }

}
