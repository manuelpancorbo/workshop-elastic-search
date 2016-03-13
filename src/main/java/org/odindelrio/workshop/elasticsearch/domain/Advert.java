package org.odindelrio.workshop.elasticsearch.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Advert
{
    private String id;
    private String title;
    private String body;
    private Double latitude;
    private Double longitude;
    private String zipCode;

    @JsonCreator
    public Advert(
        @JsonProperty("id") String id,
        @JsonProperty("title") String title,
        @JsonProperty("body") String body,
        @JsonProperty("latitude") double latitude,
        @JsonProperty("longitude") double longitude,
        @JsonProperty("zipCode") String zipCode
    ) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
