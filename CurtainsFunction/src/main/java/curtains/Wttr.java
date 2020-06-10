package curtains;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.concurrent.Immutable;
import org.apache.http.client.fluent.Request;

/**
 * Wttr contains the method getTodaysWeather which retrieves weather information from
 * https://wttr.in/ storing this is a WttrResult object.
 */
public class Wttr {

    /**
     * WttrResult is used to store the information retrieved from https://wttr.in/ via Wttr
     * getTodaysWeather method.
     */
    @Immutable
    public static final class WttrResult {

        private final String temperature;
        private final String weather;
        private final String weatherEmoji;
        private final String humidity;
        private final String precipitationMM;
        private final String wind;
        private final String sunset;

        public WttrResult(String temperature, String weather, String weatherEmoji, String humidity,
            String precipitationMM, String wind, String sunset) {
            this.temperature = temperature;
            this.weather = weather;
            this.weatherEmoji = weatherEmoji;
            this.humidity = humidity;
            this.precipitationMM = precipitationMM;
            this.wind = wind;
            this.sunset = sunset;
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
    }

    /**
     * getTodaysWeather retrieves data from https://wttr.in, formats it and constructs a new
     * WttrResult with the data.
     *
     * %%25t = Temperature (Actual)
     * %%25C = Weather condition textual name
     * %%25c = Weather condition
     * %%25h = Humidity
     * %%25p = precipitation (mm)
     * %%25w = Wind
     * %%25s = Sunset
     * &m = specifies metric system results
     *
     * For more details see: https://github.com/chubin/wttr.in
     *
     * @return WttrResult object containing weather information at time of call.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public WttrResult getTodaysWeather() throws IOException {

        String url = String
            .format("https://wttr.in/%s?format=%%25t,%%25C,%%25c,%%25h,%%25p,%%25w,%%25s&m",
                URLEncoder.encode(Config.WTTR_LOCATION, "UTF-8"));

        String[] response = Request
            .Get(URI.create(url))
            .execute()
            .returnContent()
            .asString()
            .split(",(?! )"); // (?! ) regex prevents .split errors when Weather condition textual name contains a commas

        // format response
        response[1] = response[1].toLowerCase();

        LocalTime unformattedTime = LocalTime.parse(response[6].trim(), DateTimeFormatter.ofPattern("HH:mm:ss"));
        response[6] = unformattedTime.format(DateTimeFormatter.ofPattern("h:mm a"));

        return new WttrResult(response[0], response[1], response[2], response[3], response[4], response[5], response[6]);
    }
}
