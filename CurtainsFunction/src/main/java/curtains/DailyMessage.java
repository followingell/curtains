package curtains;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

/**
 * DailyMessage utilises other classes to build and format the message to be sent to the
 * RECEIVER_PHONE_NUMBER.
 */
public class DailyMessage {

    /**
     * fetchAndFormatTodaysDate takes today's date and returns it as
     * dayOfWeek-ordinalDayNumber-month formatted String e.g. Wednesday, 10th June.
     * @return String. Today's date in dayOfWeek-ordinalDayNumber-month format.
     */
    public String fetchAndFormatTodaysDate() {

        LocalDate today = LocalDate.now(Config.MY_TIMEZONE);

        // see: https://stackoverflow.com/a/50369812/2667225
        Map<Long, String> ordinalNumbers = new HashMap<>(31);
        ordinalNumbers.put(1L, "1st");
        ordinalNumbers.put(2L, "2nd");
        ordinalNumbers.put(3L, "3rd");
        ordinalNumbers.put(21L, "21st");
        ordinalNumbers.put(22L, "22nd");
        ordinalNumbers.put(23L, "23rd");
        ordinalNumbers.put(31L, "31st");
        for (long d = 1; d <= 31; d++) {
            ordinalNumbers.putIfAbsent(d, "" + d + "th");
        }

        DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
            .appendPattern("EEEE, ")
            .appendText(ChronoField.DAY_OF_MONTH, ordinalNumbers)
            .appendPattern(" MMMM")
            .toFormatter();

        return today.format(dateFormatter);
    }

    /**
     * getTodaysMessage builds the message to be sent to the RECEIVER_PHONE_NUMBER.
     * @return String. The message to be sent to the RECEIVER_PHONE_NUMBER.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public String getTodaysMessage() throws IOException {

        String todaysDate = fetchAndFormatTodaysDate();
        Wttr.WttrResult wttr = new Wttr().getTodaysWeather();
        String affirmation = new Affirmation().getAffirmation();
        String todaysNews = new News().getTodaysNews("bbc-news");
        String historicNews = new News().getHistoricNews();

        // writing 👴 as \uD83D\uDC74 prevents Twilio 30008 Error: Unknown error.
        return String.format(
            "Good Morning 👋, it's %s!\n\n"
                + "The 🌡 is %s, and for the weather, currently, we have %s %s\n"
                + "The humidity is %s with %s of ☔ and %s 🌬\n"
                + "The sun sets at %s\n\n"
                + "In the news today 🗞: %s\n"
                + "In the news 10 years ago \uD83D\uDC74: %s\n\n"
                + "Remember: %s 💆‍‍",
            todaysDate, wttr.getTemperature(), wttr.getWeather(), wttr.getWeatherEmoji(),
            wttr.getHumidity(), wttr.getPrecipitationMM(), wttr.getWind(), wttr.getSunset(),
            todaysNews, historicNews, affirmation
        );
    }
}
