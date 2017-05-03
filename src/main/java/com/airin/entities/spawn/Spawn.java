package com.airin.entities.spawn;


import java.util.List;

/**
 * Created by herasimau on 03/05/17.
 */
public class Spawn {

    List<Group> groups;



    public Spawn(List<Group> groups) {
        this.groups = groups;
    }



    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
