package com.airin.entities.npc;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by herasimau on 13/04/17.
 */
@Entity
public class Npc {

    @Id
    private Long id;
    private String name;
    private String count;
    private String respawnTime;
    private String respawnRandom;
    @ManyToOne
    @JoinColumn(name = "npc_params_id")
    private NpcParams npcParams;


    public Npc() {
    }




    public NpcParams getNpcParams() {
        return npcParams;
    }

    public void setNpcParams(NpcParams npcParams) {
        this.npcParams = npcParams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(String respawnTime) {
        this.respawnTime = respawnTime;
    }

    public String getRespawnRandom() {
        return respawnRandom;
    }

    public void setRespawnRandom(String respawnRandom) {
        this.respawnRandom = respawnRandom;
    }
}
