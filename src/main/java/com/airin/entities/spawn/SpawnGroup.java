package com.airin.entities.spawn;

import com.airin.entities.npc.Npc;

import javax.persistence.*;
import java.util.List;

/**
 * Created by herasimau on 03/05/17.
 */
@Entity
@Table(name = "spawn_group")
public class SpawnGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String aiName;
    private String territoryName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Territory> territories;
    @ManyToMany(mappedBy = "spawnGroups", cascade = CascadeType.ALL)
    private List<Npc> npcList;

    public SpawnGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }


    public String getAiName() {
        return aiName;
    }

    public void setAiName(String aiName) {
        this.aiName = aiName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }

    public List<Npc> getNpcList() {
        return npcList;
    }

    public void setNpcList(List<Npc> npcList) {
        this.npcList = npcList;
    }
}
