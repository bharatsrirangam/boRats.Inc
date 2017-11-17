package com.boratsinc.Model;

import java.util.Arrays;
import java.util.List;



public class RatSighting {
    private String key;
    private String incident_address;
    private String created;
    private String location_type;
    private String incident_zip;
    private String city;
    private String borough;
    private String lat, lon;
    // --Commented out by Inspection (11/16/2017 10:33 PM):private String date_num; //YYYYMMDD??????


// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public String getDate_num() {
//        return date_num;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setDate_num(String date_num) {
//        this.date_num = date_num;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public static final List<String> legalBoroughs = Arrays.asList("Manhattan", "Staten Island", "Queens", "Brooklyn", "Bronx");

    public RatSighting() {

    }

    public RatSighting(String address, String date, String loc, String zip, String city, String bor, String lat, String longi) {
        this.incident_address = address;
        created = date;
        location_type = loc;
        this.incident_zip = zip;
        this.city = city;
        borough = bor;
        this.lat = lat;
        lon = longi;
    }

    public RatSighting(String key, String address, String date, String loc, String zip, String city, String bor, String lat, String longi) {
        this(address, date, loc, zip, city, bor, lat, longi);
        this.key = key;
    }

    public String getKey() {
        return key;   
    }
    
// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setKey(String key) {
//        this.key = key;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getIncident_address() {
        return incident_address;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setIncident_address(String incident_address) {
//        this.incident_address = incident_address;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getCreated() {
        return created;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setCreated(String created) {
//        this.created = created;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getLocation_type() {
        return location_type;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setLocation_type(String location_type) {
//        this.location_type = location_type;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getIncident_zip() {
        return incident_zip;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setIncident_zip(String incident_zip) {
//        this.incident_zip = incident_zip;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getCity() {
        return city;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setCity(String city) {
//        this.city = city;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getBorough() {
        return borough;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setBorough(String borough) {
//        this.borough = borough;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getLat() {
        return lat;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setLat(String lat) {
//        this.lat = lat;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getLon() {
        return lon;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setLon(String lon) {
//        this.lon = lon;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String toString() {
        return "KEY: " + key + "\nADDRESS: " + incident_address + "\nCREATED: " + created + "\nZIP: " + incident_zip + "\nCITY: " + city + "\nBOROUGH: " + borough + "\nLAT: " + lat + "\nLON: " + lon;
    }
}
