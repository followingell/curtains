package curtains;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.apache.http.client.fluent.Request;

/**
 * News contains the methods getTodaysNews and getHistoricNews to retrieve stories from today and a
 * the same date adjusted by a lookback period.
 */
public class News {

    private String lookbackDate;

    /**
     * setLookbackDate adjusts today's date to a historic date in time.
     * @param lookbackAmount long. The amount of ChronoUnits to look back in time. Must be > 0.
     * @param lookbackUnit ChronoUnit. The date periods unit denomination to adjust by.
     */
    public void setLookbackDate(long lookbackAmount, ChronoUnit lookbackUnit) {
        if (lookbackAmount > 0) {
            this.lookbackDate = LocalDate.now().minus(lookbackAmount, lookbackUnit).toString();
        }
    }

    public String getLookbackDate() {
        return lookbackDate;
    }

    /**
     * jsonNodeStatusOk is a helper method which checks that a JsonNode received data correctly.
     * @param node JsonNode. The node to be checked.
     * @return boolean. Indicates whether JsonNode received data correctly.
     */
    public static boolean jsonNodeStatusOk(JsonNode node) {
        return node.findValue("status").asText().equals("ok");
    }

    /**
     * getTodaysNews retrieves news the top headlines from https://newsapi.org/
     * @param source String. The news source to retrieve headlines from for more see: https://newsapi.org/docs/endpoints/sources
     * @return String. A String representation of the headline and the url for the news story.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public String getTodaysNews(String source) throws IOException {

        String todaysNewsPage = "https://newsapi.org/v2/top-headlines?" + "pageSize=1"
            + "&sources=" + source + "&apiKey=" + Config.NEWSAPI_API_KEY;

        String todaysNewsJson = Request
            .Get(URI.create(todaysNewsPage))
            .execute()
            .returnContent().asString();

        JsonNode todaysNewsObj = Config.MAPPER.readTree(todaysNewsJson);

        if (jsonNodeStatusOk(todaysNewsObj)) {
            JsonNode todaysTopArticle = todaysNewsObj.findValues("articles").get(0);
            return todaysTopArticle.findValue("title").asText() + ": " + todaysTopArticle.findValue("url").asText();
        } else {
            return "Error retrieving today's news";
        }
    }

    /**
     * getHistoricNews retrieves historic editor's picks from https://open-platform.theguardian.com/
     * @return String. A String representation of the headline and the url for the news story.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public String getHistoricNews() throws IOException {

        setLookbackDate(10, ChronoUnit.YEARS);

        String historicNewsPage = "https://content.guardianapis.com/search?" + "section=world"
            + "&from-date=" + getLookbackDate() + "&to-date=" + getLookbackDate()
            + "&use-date=published" + "&show-editors-picks=true" + "&page-size=1" + "&page=1"
            + "&api-key=" + Config.GUARDIAN_OPEN_PLATFORM_API_KEY;

        String historicNewsJson = Request
            .Get(URI.create(historicNewsPage))
            .execute()
            .returnContent().asString();

        JsonNode historicNewsObj = Config.MAPPER.readTree(historicNewsJson);

        if (jsonNodeStatusOk(historicNewsObj)) {
            JsonNode historicTopArticle = historicNewsObj.findValues("results").get(0);
            return historicTopArticle.findValue("webTitle").asText() + ": " + historicTopArticle.findValue("webUrl").asText();
        } else {
            return "Error retrieving historic news";
        }
    }
}
