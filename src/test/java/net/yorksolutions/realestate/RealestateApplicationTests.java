package net.yorksolutions.realestate;

import net.yorksolutions.realestate.controller.RealEstateController;
import net.yorksolutions.realestate.model.RealEstate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RealestateApplicationTests {
    @Autowired
    private RealEstateController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void realEstateControllerLoads() {
        assertThat(controller).isNotNull();

    }

    @Test
    void realEstateGetAll() {
        RealEstate[] realEstates = restTemplate.getForObject("http://localhost:" + port + "/realestate/all",
                RealEstate[].class);
        //first is where we are looking, second is what we want to find there
        assertThat(realEstates).isNotNull();
        assertThat(realEstates).isNotEmpty();
    }

    @Test
    void realEstateGetByRowAmount() {
        RealEstate[] realEstates = restTemplate.getForObject("http://localhost:" + port + "/realestate/getByRowAmount?rows=2",
                RealEstate[].class);
        assertThat(realEstates).isNotNull();
        assertThat(realEstates).isNotEmpty();
//      assertThat(realEstates.length == 2);
        assertThat(realEstates).hasSize(2);
    }

    @Test
    void searchTest() {
        RealEstate[] realEstates = restTemplate.getForObject("http://localhost:" + port + "/realestate/search?fname=Madison",
                RealEstate[].class);
        assertThat(realEstates).isNotNull();

        RealEstate realEstate = new RealEstate();
        realEstate.setFname("Madison");
        //here we create an object with fname madison and get response created back
        String response = restTemplate.postForObject("http://localhost:" + port + "/realestate/add", realEstate, String.class);
        assertThat(response).isEqualTo("success");

        realEstates = restTemplate.getForObject("http://localhost:" + port + "/realestate/search?fname=Madison",
                RealEstate[].class);
        assertThat(realEstates).isNotNull();
        assertThat(realEstates).hasSizeGreaterThan(0);
        for (RealEstate realEstate1 : realEstates) {
            assertThat(realEstate1.getFname()).isEqualTo("Madison");
        }
    }

}
