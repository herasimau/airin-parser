package com.airin.entities.spawn;

import com.airin.entities.npc.Npc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by herasimau on 03/05/17.
 */
@Entity
@Table(name = "spawn_group")
public class SpawnGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String aiName;
    private String territoryName;

    @ManyToMany(targetEntity = Territory.class, fetch = FetchType.EAGER)
    @JoinTable(name = "spawn_group_territory", joinColumns = { @JoinColumn(name = "spawn_group_id") },
            inverseJoinColumns = { @JoinColumn(name = "territory_id") })
    private Set<Territory> territories;
    @ManyToMany(targetEntity = Npc.class, fetch = FetchType.EAGER)
    @JoinTable(name = "spawn_group_npc", joinColumns = { @JoinColumn(name = "spawn_group_id") },
            inverseJoinColumns = { @JoinColumn(name = "npc_id") })
    private Set<Npc> npcList;

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

    public Set<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(Set<Territory> territories) {
        this.territories = territories;
    }

    public Set<Npc> getNpcList() {
        return npcList;
    }

    public void setNpcList(Set<Npc> npcList) {
        this.npcList = npcList;
    }
}
