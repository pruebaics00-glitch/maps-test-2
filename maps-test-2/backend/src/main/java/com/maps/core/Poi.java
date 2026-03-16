package com.maps.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Poi {
    private long id;
    private String name;
    private String description;
    private String geojson; // GeoJSON representation of the geometry

    public Poi() {
    }

    public Poi(long id, String name, String description, String geojson) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.geojson = geojson;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public String getGeojson() {
        return geojson;
    }

    @ColumnName("geojson")
    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }
}
