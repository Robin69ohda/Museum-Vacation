package org.example;

public class Location {
    private final String county;
    private final Integer sirutaCode;
    public Location(String county, Integer sirutaCode) {
        this.county = county;
        this.sirutaCode = sirutaCode;
    }
    public String getCounty() {
        return county;
    }
    public Integer getSirutaCode() {
        return sirutaCode;
    }
}
