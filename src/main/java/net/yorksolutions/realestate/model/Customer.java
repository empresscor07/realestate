package net.yorksolutions.realestate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//this is a java representation of or table row

@Entity
@JsonIgnoreProperties
public class Customer {
    @Id //this will be primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //gets generated value for primary key
    @JsonProperty
    Long id;

    @JsonProperty
    String fname;

    @JsonProperty
    String lname;

    @JsonProperty
    String city;

    @JsonProperty
    String state;

    @JsonProperty
    String email;

    @JsonProperty
    String phone;

    public Long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}