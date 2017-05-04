package com.airin.parsers;

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
            List<Npc> npcList = new ArrayList<>();
            String content = IOUtils.toString(npcData.getInputStream(), "UTF-8");
            String[] contentSplit = content.trim().split("npc_begin");

            for (int i = 1; i < contentSplit.length; i++) {
                Npc npc = new Npc();
                String[] params = contentSplit[i].trim().split("\\t");
                npc.setName(params[2].replaceAll("\\[","").replaceAll("]",""));
                npc.setGameId(Long.parseLong(params[1]));
                npcList.add(npc);
                if(i%100 == 0) {
                    npcRepository.save(npcList);
                    npcList.clear();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
