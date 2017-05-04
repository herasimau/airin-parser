package com.airin.entities.npc;


import com.airin.entities.spawn.SpawnGroup;

import javax.persistence.*;
import java.util.List;

/**
 * Created by herasimau on 13/04/17.
 */
@Entity
public class Npc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long gameId;
    private String name;
    private String count;
    private String respawnTime;
    private String respawnRandom;
    @ManyToOne
    @JoinColumn(name = "npc_params_id")
    private NpcParams npcParams;
    @ManyToMany(targetEntity = SpawnGroup.class, cascade = {CascadeType.ALL})
    @JoinTable(name = "npc_spawn_group", joinColumns = { @JoinColumn(name = "npc_id") },
            inverseJoinColumns = { @JoinColumn(name = "spawn_group_id") })
    private List<SpawnGroup> spawnGroups;


    public Npc() {
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public List<SpawnGroup> getSpawnGroups() {
        return spawnGroups;
    }

    public void setSpawnGroups(List<SpawnGroup> spawnGroups) {
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
