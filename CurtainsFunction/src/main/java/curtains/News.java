package curtains;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.net.URI;
import org.apache.http.client.fluent.Request;

public class News {

    public String getNews() throws IOException {

        // today's news
        String todaysNewsPage = "https://newsapi.org/v2/top-headlines?" + "pageSize=1&"
            + "sources=bbc-news&" + "apiKey=" + Config.NEWSAPI_KEY;

        String todaysNewsJson = Request
            .Get(URI.create(todaysNewsPage))
            .execute()
            .returnContent().asString();

        JsonNode todaysNewsObj = Config.MAPPER.readTree(todaysNewsJson);

        // historical news
        // https://content.guardianapis.com/search?q=12%20years%20a%20slave&format=json&tag=film/film,tone/reviews&from-date=2010-01-01&show-tags=contributor&show-fields=starRating,headline,thumbnail,short-url&order-by=relevance&api-key=test
        String historicalNewsPage = "https://content.guardianapis.com/search?" + "pageSize=1&"
            + "api-key=" + Config.GUARDIAN_OPEN_PLATFORM_API_KEY;

        if (todaysNewsObj.findValue("status").asText().equals("ok")) {

            JsonNode topArticle = todaysNewsObj.findValues("articles").get(0);
            return topArticle.findValue("title").asText() + ": " + topArticle.findValue("url").asText();

        } else {
            return "Error retrieving news";
        }
    }
}
