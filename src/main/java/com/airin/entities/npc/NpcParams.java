package com.airin.entities.npc;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by herasimau on 03/05/17.
 */
@Entity
@Table(name = "npc_params")
public class NpcParams {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "npcParams", cascade = CascadeType.ALL)
    private List<Npc> npcList;

    public NpcParams(){

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