package com.airin.service;

import com.airin.entities.spawn.SpawnGroup;
import com.airin.parsers.SpawnParser;
import com.airin.xml.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Set;

/**
 * Created by herasimau on 06/05/17.
 */
@Service
public class ParserService {
    @Autowired
    SpawnParser spawnParser;

    @Autowired
    Writer xmlWriter;

    public MultipartFile parseSpawnFile(MultipartFile file){

        try {
            MultipartFile result = xmlWriter.writeSpawnGroups(spawnParser.parse(file), file.getOriginalFilename());
            return result;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    return null;
    }
}
