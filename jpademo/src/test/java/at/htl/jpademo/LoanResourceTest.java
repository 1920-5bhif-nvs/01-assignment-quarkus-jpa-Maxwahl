package at.htl.jpademo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LoanResourceTest {
    //just some tests
    //should only be run when not compiling for docker, otherwise would always fail because of network name of datasource
    /*
    @Test
    public void testLoansSize() {
        given()
                .when().get("/loans")
                .then()
                .statusCode(200)
                .body("size()", is(2));
    }
    @Test
    public void testPersonsName() {
        given()
                .when().get("/persons/1")
                .then()
                .statusCode(200)
                .body("name", is("Meier"));
    }
    @Test void testLoanExemplars(){
        given()
                .when().get("/loans/1")
                .then()
                .statusCode(200)
                .body("exemplars.weariness",hasItems("undamaged", "used"));
    }*/

}