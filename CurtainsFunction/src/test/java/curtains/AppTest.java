package curtains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class AppTest {

  @Test
  public void successfulResponse() {

    App app = new App();
    GatewayResponse result = (GatewayResponse) app.handleRequest(null, null);
    assertEquals(result.getStatusCode(), 200);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getBody();
    assertNotNull(content);
    assertTrue(content.contains("\"message\""));
    assertTrue(content.contains("\"hello world\""));
    assertTrue(content.contains("\"location\""));
  }

  @Test
  public void successfulDateFormatting() {

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
        .appendPattern("EEEE ")
        .appendText(ChronoField.DAY_OF_MONTH, ordinalNumbers)
        .appendPattern(" MMMM")
        .toFormatter();

    LocalDate date = LocalDate.of(2020, Month.JUNE, 3);

    assertEquals(date.format(dateFormatter), "Wednesday 3rd June");
  }
}
