package curtains;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyMessage {

    public String getTodaysMessage() throws IOException {
        LocalDate today = LocalDate.now(Config.MY_TIMEZONE);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd");
        String todaysDate = today.format(dateFormatter);

        Wttr.WttrResult wttr = new Wttr().getTodaysWeather();

        return String.format(
            "Good Morning, it's %s!\n"
                + "The 🌡 is %s and we'll be having %s %s\n"
                + "Humidity is at %s & there will be %s of ☔\n"
                + "The sun sets at %s & the moon's phase is %s",
            todaysDate, wttr.temperature, wttr.weather, wttr.weatherEmoji, wttr.humidity,
            wttr.precipitationMM, wttr.sunset, wttr.moonphaseEmoji
        );
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new DailyMessage().getTodaysMessage());
    }

}
