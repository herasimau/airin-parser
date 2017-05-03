package com.airin.parsers;

import com.airin.entities.npc.Npc;
import com.airin.entities.spawn.Group;
import com.airin.entities.spawn.Territory;
import com.airin.repositories.NpcRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by herasimau on 03/05/17.
 */
@Service
public class SpawnParser {
    @Value(value = "classpath:/spawnData.txt")
    private Resource spawnData;

    @Autowired
    NpcRepository npcRepository;

    @PostConstruct
    public void onInit(){
        List<Group> groupList = new ArrayList<>();
        List<Npc> npcList = new ArrayList<>();
        List<Territory> territoryList = new ArrayList<>();

        try {
            String content = IOUtils.toString(spawnData.getInputStream(), "UTF-8");
            String[] contentTerritory = content.trim().split("territory_begin");

            for (int i = 1; i < contentTerritory.length; i++) {
                Group group = new Group();
                Territory territory = new Territory();
                HashMap<String,String> coord = new HashMap<>();
                String[] params = contentTerritory[i].trim().split("\\t");
                territory.setName(params[0].replaceAll("\\[","").replaceAll("]",""));
                String[] coordinates = params[1].replaceAll("\\{","").replaceAll("}","").split(";");
                territory.setMinZ(coordinates[2]);
                territory.setMaxZ(coordinates[3]);

                for (int j = 0; j < coordinates.length; j++) {
                    if(j+1<coordinates.length-1){
                        String x = coordinates[j];
                        String y = coordinates[j+1];
                        coord.put(x,y);
                        if(j+3<coordinates.length-1){
                            j+=3;
                        }
                    }
                }

                String[] npcArray = contentTerritory[i].trim().split("npcmaker_ex_begin");
                territory.setCoordinates(coord);
                territoryList.add(territory);
                group.setName(territory.getName());
                group.setAiName(npcArray[1].trim().split("\\t")[2].split("=")[1].replaceAll("\\[","").replaceAll("]",""));
                groupList.add(group);
                String[] npcEx = contentTerritory[i].trim().split("npc_ex_begin");
                for (int j = 1; j < npcEx.length ; j++) {
                    Npc npc = new Npc();
                    String[] npcParams = npcEx[j].trim().split("\\t");
                    npc.setName(npcParams[0].replaceAll("\\[","").replaceAll("]",""));
                    npc.setId(npcRepository.findByName(npc.getName()).getId());
                    for (int k = 1; k < npcParams.length; k++) {
                        String[] pair = npcParams[k].split("=");
                        if(pair[0].equalsIgnoreCase("respawn_rand")){
                            npc.setRespawnRandom(pair[1]);
                        }
                        else if(pair[0].equalsIgnoreCase("respawn")){
                            npc.setRespawnTime(pair[1]);
                        }
                        else if(pair[0].equalsIgnoreCase("total")){
                            npc.setCount(pair[1]);
                        }

                    }
                    npcList.add(npc);
                }


            }



//            for (int i = 1; i < contentNpc.length; i++) {
//                Npc npc = new Npc();
//                String[] params = contentNpc[i].trim().split("\\t");
//                npc.setName(params[0].replaceAll("\\[","").replaceAll("]",""));
//                npc.setId(npcRepository.findByName(npc.getName()).getId());
//
//                npcList.add(npc);
//
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
