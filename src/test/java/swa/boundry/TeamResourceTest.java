package swa.boundry;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
public class TeamResourceTest {

    @Inject
    TeamResource teamResource;

    @BeforeAll
    public void init() {
        teamResource.createTeam("Barcelona", "Team");
    }

    @Test
    void testCreateTeam() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().post("/Teams/Real Madrid?category=Team}")
                .then()
                .statusCode(200)
                .body(is("Team has been added to Team successfully"));
    }

    @Test
    void testDeleteTeam() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().delete("/Teams/0")
                .then()
                .statusCode(200)
                .body(is("Team has been removed successfully"));
    }

    @Test
    void testUpdateTeam() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().put("/Teams/0?name=Real Madrid&category=Team")
                .then()
                .statusCode(200)
                .body(is("Team has been updated successfully"));
    }

    @Test
    void testGetTeamById() {
        given()
                .when().get("/Teams/{id}", 0)
                .then()
                .statusCode(200);
    }

    @Test
    void testGetTeams() {
        given()
                .when().get("/Teams")
                .then()
                .statusCode(200);
    }

}
