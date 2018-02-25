import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RestAssuredTest {
    @Test
    public void errorDecoder() {
        Response resp = given()
                .baseUri("http://realty-searcher-02-sas.test.vertis.yandex.net:1035/1.0/")
                .header("Accept", "application/json")
                .header("'x-authorization", "Vertis 02470ea1-2f88-49c5-9292-3479c2b7da1d 0bf525f1-5488-4890-af67-959736723b71")
                .header("authorization", "OAuth AQAAAADupJrFAAAKt-qgVS5WN0uVoUYrgnyyifA")
                .header("'x-vertis-platform", "android/d2")
                .when().get("errorsDescription?code=29");
        String title_lk = resp.jsonPath().get("response.descriptions.title_lk[0]");
        assertThat(title_lk).isEqualTo("Недостоверная цена");
    }
}
