package com.boratsinc.Model;

import java.util.Date;

/**
 * Created by baseb on 10/10/2017.
 */

public class RatSighting {
    private int key;
    private Date created;
    private String locationType;
    private int zip;
    private String city;
    private Borough borough;
    private int latitude, longitude;

    public RatSighting(int key) {
        this(key, new Date(), "", 0, "", null, 0, 0);
    }
    public RatSighting(int key, Date date, String loc, int zip, String city, Borough bor, int lat, int longi) {
        this.key = key;
        created = date;
        locationType = loc;
        this.zip = zip;
        this.city = city;
        borough = bor;
        latitude = lat;
        longitude = longi;
    }

    public int getKey() {
        return key;
    }

    public Date getDateCreated() {
        return created;
    }

    public String getLocationType() {
        return locationType;
    }

    public int getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public Borough getBorough() {
        return borough;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public String toString() {
        return "" + key + ", " + created + ", " + zip + ", " + city + ", " + borough + ", " + latitude + ", " + longitude;
    }
}
