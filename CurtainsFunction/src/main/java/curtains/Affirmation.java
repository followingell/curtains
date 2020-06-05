package curtains;

import java.io.IOException;
import java.net.URI;
import org.apache.http.client.fluent.Request;

public class Affirmation {

    public String getAffirmation() throws IOException {

        String affirmationJson = Request
            .Get(URI.create("https://www.affirmations.dev"))
            .execute()
            .returnContent().asString();

        return Config.MAPPER
            .readTree(affirmationJson)
            .get("affirmation")
            .asText();
    }

}
