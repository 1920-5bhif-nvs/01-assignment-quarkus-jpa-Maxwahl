package at.htl.jpademo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LoanResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/data")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}