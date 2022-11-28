package swa.boundry;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
public class TeamResourceTest {

    @Inject
    TeamResource teamResource;

    @BeforeAll
    public void init() {

    }

    @Test
    void testAddTeamMember() {

    }

    @Test
    void testConvertToDTO() {

    }

    @Test
    void testConvertToDTO2() {

    }

    @Test
    void testCreateTeam() {

    }

    @Test
    void testDeleteTeam() {

    }

    @Test
    void testDeleteTeamMember() {

    }

    @Test
    void testGetTeamById() {

    }

    @Test
    void testGetTeams() {

    }

}
