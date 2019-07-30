package com.codecool.octogoods.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Location {

    private int locationId;
    private String city;
    private String country;
    private String address;

    @Embedded
    private Coordinate coordinate;
}
