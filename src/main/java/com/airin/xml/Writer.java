package com.airin.xml;

import com.airin.entities.spawn.SpawnGroup;
import com.airin.repositories.SpawnGroupRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;


/**
 * Created by herasimau on 05/05/17.
 */
@Service
public class Writer {
    @Autowired
    SpawnGroupRepository spawnGroupRepository;


    public MultipartFile writeSpawnGroups(Iterable<SpawnGroup> spawnGroups, String fileName) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder =  docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("list");
        doc.appendChild(rootElement);

        Element spawnElement = doc.createElement("spawn");
        rootElement.appendChild(spawnElement);

        Attr spawnName = doc.createAttribute("name");
        spawnName.setValue(fileName.split("\\.")[0]);
        spawnElement.setAttributeNode(spawnName);

        spawnGroups.forEach(spawnGroup -> {

                Element groupElement = doc.createElement("group");
                spawnElement.appendChild(groupElement);

                Attr name = doc.createAttribute("name");
                name.setValue(spawnGroup.getName());
                groupElement.setAttributeNode(name);

                Element territoriesElement = doc.createElement("territories");
                groupElement.appendChild(territoriesElement);

                spawnGroup.getTerritories().forEach(territory -> {
                    String terrName = "territory";
                    if(territory.getBanned()){
                        terrName = "banned_territory";
                    }
                    Element territoryElement = doc.createElement(terrName);
                    territoriesElement.appendChild(territoryElement);

                    Attr territoryName = doc.createAttribute("name");
                    territoryName.setValue(territory.getName());
                    territoryElement.setAttributeNode(territoryName);

                    Attr territoryMinZ = doc.createAttribute("minZ");
                    territoryMinZ.setValue(territory.getMinZ());
                    territoryElement.setAttributeNode(territoryMinZ);

                    Attr territoryMaxZ = doc.createAttribute("maxZ");
                    territoryMaxZ.setValue(territory.getMaxZ());
                    territoryElement.setAttributeNode(territoryMaxZ);





                    territory.getCoordinates().forEach((x,y)-> {
                        Element nodeElement = doc.createElement("node");
                        territoryElement.appendChild(nodeElement);

                        Attr coordinatesX = doc.createAttribute("x");
                        coordinatesX.setValue(x);
                        nodeElement.setAttributeNode(coordinatesX);

                        Attr coordinatesY = doc.createAttribute("y");
                        coordinatesY.setValue(y);
                        nodeElement.setAttributeNode(coordinatesY);

                    });

                });

                spawnGroup.getNpcList().forEach(npc -> {
                    Element npcElement = doc.createElement("npc");
                    groupElement.appendChild(npcElement);

                    Comment comment = doc.createComment(npc.getName());
                    npcElement.appendChild(comment);

                    Attr npcGameId = doc.createAttribute("id");
                    npcGameId.setValue(npc.getGameId().toString());
                    npcElement.setAttributeNode(npcGameId);


                    Attr npcRespawnTime = doc.createAttribute("respawnTime");
                    npcRespawnTime.setValue(npc.getRespawnTime());
                    npcElement.setAttributeNode(npcRespawnTime);

                    Attr npcCount = doc.createAttribute("count");
                    npcCount.setValue(npc.getCount());
                    npcElement.setAttributeNode(npcCount);

                    Attr npcRespawnRandom = doc.createAttribute("respawnRandom");
                    npcRespawnRandom.setValue(npc.getRespawnRandom());
                    npcElement.setAttributeNode(npcRespawnRandom);


                });

        } );

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        String xmlFileName = fileName.split("\\.")[0]+".xml";
        File  xmlResult = new File(xmlFileName);
        StreamResult result = new StreamResult(xmlResult);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);


        try {
            FileInputStream input = new FileInputStream(xmlResult);
            MultipartFile multipartFile = new MockMultipartFile("file",
                    xmlFileName, "text/plain", IOUtils.toByteArray(input));
            return multipartFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    return null;
    }
}
