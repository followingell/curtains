package curtains;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import curtains.Wttr.WttrResult;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

// currently only tests NOT relying on AWS environment variables are included (see README.md)
public class CurtainsTest {

    @Test
    public void wttrResultGettersNotNull() {
        Wttr.WttrResult testWttrResult = new WttrResult("+15°C", "shower in vicinity", "☁️", "67%",
            "0.0mm", "↖13km/h", "9:29 PM");
        assertNotNull(testWttrResult.getTemperature());
        assertNotNull(testWttrResult.getWeather());
        assertNotNull(testWttrResult.getWeatherEmoji());
        assertNotNull(testWttrResult.getHumidity());
        assertNotNull(testWttrResult.getPrecipitationMM());
        assertNotNull(testWttrResult.getWind());
        assertNotNull(testWttrResult.getSunset());
    }

    @Test
    public void newsValidSetLookbackDate() {
        News testNews = new News();
        testNews.setLookbackDate(1, ChronoUnit.DAYS);
        assertEquals(testNews.getLookbackDate(), LocalDate.now().minus(1, ChronoUnit.DAYS).toString());
    }

    @Test
    public void newsInvalidSetLookbackDate() {
        News testNews = new News();
        testNews.setLookbackDate(1, ChronoUnit.DAYS);
        assertEquals(testNews.getLookbackDate(), LocalDate.now().minus(1, ChronoUnit.DAYS).toString());
        testNews.setLookbackDate(-10, ChronoUnit.DAYS);
        assertEquals(testNews.getLookbackDate(), LocalDate.now().minus(1, ChronoUnit.DAYS).toString());
    }

    @Test
    public void newsJsonNodeStatusOkTrue() throws JsonProcessingException {
        String json = "{ \"status\" : \"ok\" } ";
        JsonNode jsonNode = Config.MAPPER.readTree(json);
        assertTrue(News.jsonNodeStatusOk(jsonNode));
    }

    @Test
    public void newsJsonNodeStatusOkFalse() throws JsonProcessingException {
        String json = "{ \"status\" : \"notok\" } ";
        JsonNode testJsonNode = Config.MAPPER.readTree(json);
        assertFalse(News.jsonNodeStatusOk(testJsonNode));
    }

    @Test
    public void affirmationGetAffirmationSuccess() throws IOException {
        String testString = "";
        assertEquals("", testString);
        testString = new Affirmation().getAffirmation();
        assertTrue(testString.length() > 1);
    }

    @Test
    public void dailyMessageFetchAndFormatTodaysDateCorrect() {
        DailyMessage testDailyMessage = new DailyMessage();

        String testTodaysDate = testDailyMessage.fetchAndFormatTodaysDate();

        testTodaysDate = testTodaysDate.replace(",", "");
        String[] testTodaysDateSplit = testTodaysDate.split(" ");
        assertEquals(3, testTodaysDateSplit.length);

        // day of week (in english) always >= 6 & <= 9 if you include the comma
        assertTrue(testTodaysDateSplit[0].length() >= 6 && testTodaysDateSplit[0].length() <= 9);
        // ordinal date always >= 3 && <= 4
        assertTrue(testTodaysDateSplit[1].length() >= 3 && testTodaysDateSplit[1].length() <= 4);
        // month (in english) always >= 3 && <= 9
        assertTrue(testTodaysDateSplit[2].length() >= 3 && testTodaysDateSplit[2].length() <= 9);
    }
}
