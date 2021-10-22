package net.yorksolutions.realestate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.realestate.model.RealEstate;
import net.yorksolutions.realestate.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
//here we map location with methods

//here we are using java like language to get and request data rather than sql
@RequestMapping("/realestate") //this is similar to an express router path
@RestController
public class RealEstateController {
    @Autowired //this means it will be a global
    RealEstateRepository realEstateRepository;

    // someone else created this to turn a generic java object into a json formatted string
    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/all") //this is relative to base path - get method
    String getAllRealEstate() throws JsonProcessingException {
        return objectMapper.writeValueAsString(realEstateRepository.findAll()); //like a select star of all entries
    }

    // ?param1=param1_value
    // getByRowAmount?rows=10
    @GetMapping("/getByRowAmount")
    String getRealEstateByRows(@RequestParam("rows") String rows) throws JsonProcessingException {
        //cast type tp list
        List<RealEstate> realEstateList = (List<RealEstate>) realEstateRepository.findAll();

        // Java'esk way of getting to the top N elements
        realEstateList = realEstateList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());

        return objectMapper.writeValueAsString(realEstateList);
    }

    @PostMapping("/add")
    String postRealestate(@RequestBody String body) {
        try {
            RealEstate realEstate = objectMapper.readValue(body, RealEstate.class);
            realEstateRepository.save(realEstate);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/search")
    String searchRealEstateByFname(@RequestParam("fname") String fname) throws JsonProcessingException {
        List<RealEstate> realEstateList = (List<RealEstate>) realEstateRepository.findAll();
        realEstateList = realEstateList
                .stream()
                .filter(r -> Objects.equals(r.getFname(), fname))
                .collect(Collectors.toList());

        return objectMapper.writeValueAsString(realEstateList);
    }
}
