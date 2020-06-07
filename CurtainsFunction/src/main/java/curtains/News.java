package curtains;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.apache.http.client.fluent.Request;

public class News {

    private static final String TODAYS_DATE_MINUS_TEN_YEARS = LocalDate.now()
        .minus(10, ChronoUnit.YEARS).toString();

    public String getTodaysNews() throws IOException {

        String todaysNewsPage = "https://newsapi.org/v2/top-headlines?" + "pageSize=1"
            + "&sources=bbc-news" + "&apiKey=" + Config.NEWSAPI_KEY;

        String todaysNewsJson = Request
            .Get(URI.create(todaysNewsPage))
            .execute()
            .returnContent().asString();

        JsonNode todaysNewsObj = Config.MAPPER.readTree(todaysNewsJson);

        if (todaysNewsObj.findValue("status").asText().equals("ok")) {
            JsonNode todaysTopArticle = todaysNewsObj.findValues("articles").get(0);
            return todaysTopArticle.findValue("title").asText() + ": " + todaysTopArticle
                .findValue("url").asText();
        } else {
            return "Error retrieving today's news";
        }
    }

    public String getHistoricNews() throws IOException {

        String historicNewsPage = "https://content.guardianapis.com/search?" + "section=world"
            + "&from-date=" + TODAYS_DATE_MINUS_TEN_YEARS + "&to-date="
            + TODAYS_DATE_MINUS_TEN_YEARS + "&use-date=published" + "&show-editors-picks=true"
            + "&page-size=1" + "&page=1" + "&api-key=" + Config.GUARDIAN_OPEN_PLATFORM_API_KEY;

        String historicNewsJson = Request
            .Get(URI.create(historicNewsPage))
            .execute()
            .returnContent().asString();

        JsonNode historicNewsObj = Config.MAPPER.readTree(historicNewsJson);

        if (historicNewsObj.findValue("status").asText().equals("ok")) {
            JsonNode historicTopArticle = historicNewsObj.findValues("results").get(0);
            return historicTopArticle.findValue("webTitle").asText() + ": " + historicTopArticle
                .findValue("webUrl").asText();
        } else {
            return "Error retrieving historic news";
        }
    }
}
