package com.airin.parsers;

import com.airin.Utils;
import com.airin.entities.npc.Npc;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.airin.repositories.NpcRepository;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by herasimau on 03/05/17.
 */
@Service
public class NpcParser {

    @Autowired
    private NpcRepository npcRepository;

    @Value(value = "classpath:/npc.txt")
    private Resource npcData;

    //@PostConstruct
    public void parse(){
        try {
            String npcContent = IOUtils.toString(npcData.getInputStream(), "UTF-8");
            String[] contentSplit = npcContent.trim().split("npc_begin");
            List<Npc> npcListTemp = new ArrayList<>();
            for (int i = 1; i < contentSplit.length; i++) {
                Npc npc = new Npc();
                String[] params = contentSplit[i].trim().split("\\t");
                npc.setName(params[2].replaceAll("\\[","").replaceAll("]",""));
                npc.setGameId(Long.parseLong(params[1]));
                //npcHashMap.put(npc.getName(),npc);
                npcListTemp.add(npc);
                if(npcRepository.findByName(npc.getName())==null){
                    npcRepository.save(npc);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Npc parseNpc(String[] npcParams) {
        Npc npc = npcRepository.findByName(Utils.replaceBrackets(npcParams[0]));
        for (int l = 1; l < npcParams.length; l++) {
            String[] pair = npcParams[l].split("=");
            if (pair[0].equalsIgnoreCase("respawn_rand")) {
                npc.setRespawnRandom(pair[1]);
            } else if (pair[0].equalsIgnoreCase("respawn")) {
                npc.setRespawnTime(pair[1]);
            } else if (pair[0].equalsIgnoreCase("total")) {
                npc.setCount(pair[1]);
            }

        }
        return npc;
    }

}
