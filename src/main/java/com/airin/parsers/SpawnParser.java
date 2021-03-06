package com.airin.parsers;

import com.airin.Utils;
import com.airin.entities.npc.Npc;
import com.airin.entities.spawn.Spawn;
import com.airin.entities.spawn.SpawnGroup;
import com.airin.entities.spawn.Territory;
import com.airin.repositories.NpcRepository;
import com.airin.repositories.SpawnGroupRepository;
import com.airin.repositories.TerritoryRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * Created by herasimau on 03/05/17.
 */
@Service
public class SpawnParser {

    @Autowired
    NpcRepository npcRepository;

    @Autowired
    TerritoryRepository territoryRepository;

    @Autowired
    SpawnGroupRepository spawnGroupRepository;

    @Autowired
    NpcParser npcParser;

    //@PostConstruct
    public Set<SpawnGroup> parse(MultipartFile file) {
        HashMap<String, SpawnGroup> groupHashMap = new HashMap<>();
        List<Npc> globalListNpc = new ArrayList<>();
        try {
            String content = IOUtils.toString(file.getInputStream(), "UTF-8");
            String[] contentGroup = content.trim().split("npcmaker_ex_begin");
            //Цикл для групп
            for (int i = 1; i < contentGroup.length; i++) {
                SpawnGroup group = new SpawnGroup();
                Set<Npc> npcList = new HashSet<>();
                String[] params = contentGroup[i].trim().split("\\t");

                if (params[1].split("=")[0].equalsIgnoreCase("banned_territory")) {
                    group.setName(Utils.replaceBrackets(params[2].split("=")[1]));
                    String[] bannedTerr = Utils.replaceBrackets(params[1])
                            .replaceAll("\\{", "").replaceAll("}", "").split(";");

                    for (int j = 0; j < bannedTerr.length; j++) {
                        group.setTerritoryName(bannedTerr[j]);

                        group.setAiName(Utils.replaceBrackets(params[3].split("=")[1]));
                        String[] npcEx = contentGroup[i].trim().split("npc_ex_begin");
                        for (int k = 1; k < npcEx.length; k++) {
                            String[] npcParams = npcEx[k].trim().split("\\t");
                            Npc npc = npcParser.parseNpc(npcParams);
                           // npcRepository.save(npc);
                            npcList.add(npc);
                            globalListNpc.add(npc);

                        }
                        group.setNpcList(npcList);
                        groupHashMap.put(group.getTerritoryName(), group);
                    }
                    if (Utils.replaceBrackets(params[2].split("=")[1]) != null) {

                        SpawnGroup mainGroup = new SpawnGroup();
                        Set<Npc> npcListMainGroup = new HashSet<>();

                        mainGroup.setName(Utils.replaceBrackets(params[2].split("=")[1]));
                        mainGroup.setTerritoryName(Utils.replaceBrackets(params[0]));

                        mainGroup.setAiName(Utils.replaceBrackets(params[3].split("=")[1]));
                        String[] npcExMain = contentGroup[i].trim().split("npc_ex_begin");
                        for (int y = 1; y < npcExMain.length; y++) {

                            String[] npcParams = npcExMain[y].trim().split("\\t");
                            Npc npc = npcParser.parseNpc(npcParams);
                          //  npcRepository.save(npc);
                            npcListMainGroup.add(npc);
                            globalListNpc.add(npc);

                        }
                        group.setNpcList(npcListMainGroup);
                        groupHashMap.put(mainGroup.getTerritoryName(), group);

                    }
                } else {
                    group.setName(Utils.replaceBrackets(params[1].split("=")[1]));
                    group.setTerritoryName(Utils.replaceBrackets(params[0]));

                    group.setAiName(Utils.replaceBrackets(params[2].split("=")[1]));
                    String[] npcEx = contentGroup[i].trim().split("npc_ex_begin");
                    for (int j = 1; j < npcEx.length; j++) {

                        String[] npcParams = npcEx[j].trim().split("\\t");
                        Npc npc = npcParser.parseNpc(npcParams);
                       // npcRepository.save(npc);
                        npcList.add(npc);
                        globalListNpc.add(npc);

                    }
                    group.setNpcList(npcList);
                    groupHashMap.put(group.getTerritoryName(), group);
                }


            }

            //Цикл для территорий
            String[] contentTerritory = content.trim().split("territory_begin");

            for (int i = 1; i < contentTerritory.length; i++) {
                Set<Territory> territoryList = new HashSet<>();
                HashMap<String, String> coord = new HashMap<>();
                String[] params = contentTerritory[i].trim().split("\\t");

                Territory territory = new Territory();

                territory.setName(Utils.replaceBrackets(params[0]));
                if (Utils.replaceBrackets(params[0]).endsWith("b")) {
                    territory.setBanned(true);
                } else {
                    territory.setBanned(false);
                }

                String[] coordinates = params[1].replaceAll("\\{", "").replaceAll("}", "").split(";");
                territory.setMinZ(coordinates[2]);
                territory.setMaxZ(coordinates[3]);

                for (int j = 0; j < coordinates.length; j++) {
                    if (j + 1 < coordinates.length - 1) {
                        String x = coordinates[j];
                        String y = coordinates[j + 1];
                        coord.put(x, y);
                        if (j + 3 < coordinates.length - 1) {
                            j += 3;
                        }
                    }
                }
                territory.setCoordinates(coord);
                territoryList.add(territory);
               // territoryRepository.save(territory);

                if (groupHashMap.get(territory.getName()) != null) {
                    if (groupHashMap.get(territory.getName()).getTerritories() != null) {
                        Set<Territory> temp = groupHashMap.get(territory.getName()).getTerritories();
                        temp.add(territory);
                        groupHashMap.get(territory.getName()).setTerritories(temp);
                    } else {
                        groupHashMap.get(territory.getName()).setTerritories(territoryList);
                    }
                }


            }
            Set<SpawnGroup> result = new HashSet<>();
            groupHashMap.forEach((k, v)
                    -> {
                result.add(v);
            });
            updateDatabase(groupHashMap);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Async
    public void updateDatabase(HashMap<String,SpawnGroup> groupHashMap) throws InterruptedException {
        groupHashMap.forEach((k, v)
                -> {
            spawnGroupRepository.save(v);
        });

    }

}
