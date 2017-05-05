package com.airin.entities.npc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by herasimau on 03/05/17.
 */
@Entity
@Table(name = "npc_params")
public class NpcParams implements Serializable {
    @Id
    private int id;
    private String name;
    private String value;
    @OneToMany(mappedBy = "npcParams", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Npc> npcList;

    public NpcParams(){

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNpcList(List<Npc> npcList) {
        this.npcList = npcList;
    }

    public NpcParams(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Npc> getNpcList() {
        return npcList;
    }

    public void setBooks(List<Npc> npcList) {
        this.npcList = npcList;
    }


}