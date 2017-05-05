package com.airin.entities.npc;


import com.airin.entities.spawn.SpawnGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by herasimau on 13/04/17.
 */
@Entity
public class Npc implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long gameId;
    @Column(unique=true)
    private String name;
    private String count;
    private String respawnTime;
    private String respawnRandom;
    @ManyToOne
    @JoinColumn(name = "npc_params_id")
    private NpcParams npcParams;
    @ManyToMany(mappedBy = "npcList", cascade = CascadeType.ALL)
    private Set<SpawnGroup> spawnGroups;


    public Npc() {
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Set<SpawnGroup> getSpawnGroups() {
        return spawnGroups;
    }

    public void setSpawnGroups(Set<SpawnGroup> spawnGroups) {
        this.spawnGroups = spawnGroups;
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
