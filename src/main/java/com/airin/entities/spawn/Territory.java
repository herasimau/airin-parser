package com.airin.entities.spawn;

import java.util.HashMap;

/**
 * Created by herasimau on 03/05/17.
 */
public class Territory {


    private String name;
    private String minZ;
    private String maxZ;
    private HashMap<String,String> coordinates;

    public Territory() {
    }

    public Territory(String name, String minZ, String maxZ) {
        this.name = name;
        this.minZ = minZ;
        this.maxZ = maxZ;
    }

    public HashMap<String, String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(HashMap<String, String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinZ() {
        return minZ;
    }

    public void setMinZ(String minZ) {
        this.minZ = minZ;
    }

    public String getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(String maxZ) {
        this.maxZ = maxZ;
    }
}
