package curtains;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.apache.http.client.fluent.Request;

public class News {

    private String lookbackDate;

    public void setLookbackDate(long lookbackAmount, ChronoUnit lookbackUnit) {
        if (lookbackAmount > 0) {
            this.lookbackDate = LocalDate.now().minus(lookbackAmount, lookbackUnit).toString();
        } else {
            System.err.println("lookbackAmount must be <= 0.");
        }
    }

    public String getLookbackDate() {
        return lookbackDate;
    }

    public static boolean jsonNodeStatusOk(JsonNode node) {
        return node.findValue("status").asText().equals("ok");
    }

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
