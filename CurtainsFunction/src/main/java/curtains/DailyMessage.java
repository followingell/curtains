package curtains;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;

public class DailyMessage {

    public String getTodaysMessage() throws IOException, ParseException {
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

        String todaysDate = today.format(dateFormatter);
        Wttr.WttrResult wttr = new Wttr().getTodaysWeather();
        String affirmation = new Affirmation().getAffirmation();
        String todaysNews = new News().getTodaysNews();
        String historicNews = new News().getHistoricNews();

        return String.format(
            "Good Morning 👋, it's %s!\n\n"
                + "The 🌡 is %s, and for the weather, currently, we have %s %s\n"
                + "The humidity is %s with %s of ☔ and %s 🌬\n"
                + "The sun sets at %s & the moon's phase is %s\n\n"
                + "In the news today 🗞: %s\n"
                + "In the news 10 years ago 👴: %s\n\n"
                + "Remember: %s 💆‍‍\n",
            todaysDate, wttr.temperature, wttr.weather, wttr.weatherEmoji,
            wttr.humidity, wttr.precipitationMM, wttr.wind, wttr.sunset, wttr.moonPhaseEmoji,
            todaysNews, historicNews, affirmation
        );
    }

    public static void main(String[] args) throws IOException, ParseException {
        String todaysMessage = new DailyMessage().getTodaysMessage();
        System.out.println(todaysMessage);
        new TwilioSMSSender().sendMessage(todaysMessage);
    }

}
