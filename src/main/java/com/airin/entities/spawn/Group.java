package com.airin.entities.spawn;

import com.airin.entities.npc.Npc;

import java.util.List;

/**
 * Created by herasimau on 03/05/17.
 */
public class Group {

    String name;
    String aiName;

    List<Territory> territories;
    List<Npc> npcList;

    public Group() {
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

    public Group(List<Territory> territories, List<Npc> npcList) {
        this.territories = territories;
        this.npcList = npcList;
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
