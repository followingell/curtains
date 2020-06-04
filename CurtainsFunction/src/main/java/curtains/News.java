package curtains;

import java.io.IOException;

public class News {

    public String getNews() throws IOException {

        String newsPage = "http://newsapi.org/v2/top-headlines?" + "pageSize=1&"
            + "sources=bbc-news&" + "apiKey=" + Config.NEWSAPI_KEY;

        return "Test";
    }
}
