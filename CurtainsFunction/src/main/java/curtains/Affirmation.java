package curtains;

import java.io.IOException;
import java.net.URI;
import org.apache.http.client.fluent.Request;

/**
 * Affirmation contains the method getAffirmation which retrieves an affirmation from
 * https://www.affirmations.dev and returns this as a String.
 */
public class Affirmation {

    /**
     * getAffirmation retrieves an affirmation from https://www.affirmations.dev and returns this
     * as a String.
     * @return String. The affirmation returned from https://www.affirmations.dev.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
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
