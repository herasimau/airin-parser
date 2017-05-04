package com.airin.entities.spawn;


import java.util.List;

/**
 * Created by herasimau on 03/05/17.
 */
public class Spawn {

    List<SpawnGroup> groups;



    public Spawn(List<SpawnGroup> groups) {
        this.groups = groups;
    }



    public List<SpawnGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<SpawnGroup> groups) {
        this.groups = groups;
    }
}
