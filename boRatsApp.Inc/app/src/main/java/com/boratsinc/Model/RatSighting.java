package com.boratsinc.Model;

import java.util.Date;

/**
 * Created by baseb on 10/10/2017.
 */

public class RatSighting {
    private String key;
    private String incident_address;
    private String created;
    private String location_type;
    private String incident_zip;
    private String city;
    private String borough;
    private String lat, lon;

    public RatSighting(int key) {

    }

    public RatSighting() {

    }

    public RatSighting(String key, String date, String loc, String zip, String city, String bor, String lat, String longi) {
        this.key = key;
        created = date;
        location_type = loc;
        this.incident_zip = zip;
        this.city = city;
        borough = bor;
        this.lat = lat;
        lon = longi;
    }

    public String getKey() {
        return key;   
    }
    
    public void setKey(String key) {
        this.key = key;    
    }
    
    public String getIncident_address() {
        return incident_address;
    }

    public void setIncident_address(String incident_address) {
        this.incident_address = incident_address;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public String getIncident_zip() {
        return incident_zip;
    }

    public void setIncident_zip(String incident_zip) {
        this.incident_zip = incident_zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String toString() {
        return "KEY: " + key + "\nADDRESS: " + incident_address + "\nCREATED: " + created + "\nZIP: " + incident_zip + "\nCITY: " + city + "\nBOROUGH: " + borough + "\nLAT: " + lat + "\nLON: " + lon;
    }
}
