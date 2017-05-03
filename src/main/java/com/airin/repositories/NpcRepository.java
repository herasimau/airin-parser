package com.airin.repositories;

import com.airin.entities.npc.Npc;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by herasimau on 03/05/17.
 */
public interface NpcRepository extends JpaRepository<Npc, Long> {

    Npc findByName(String name);
}
