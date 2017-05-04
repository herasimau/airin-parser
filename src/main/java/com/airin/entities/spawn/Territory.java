package com.airin.entities.spawn;

import javax.persistence.*;
import java.util.HashMap;

/**
 * Created by herasimau on 03/05/17.
 */
@Entity
public class Territory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String minZ;
    private String maxZ;
    private HashMap<String,String> coordinates;
    private Boolean isBanned;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private SpawnGroup group;

    public Territory() {
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public SpawnGroup getGroup() {
        return group;
    }

    public void setGroup(SpawnGroup group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
