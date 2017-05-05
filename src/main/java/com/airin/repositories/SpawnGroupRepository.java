package com.airin.repositories;

import com.airin.entities.spawn.SpawnGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by herasimau on 04/05/17.
 */
public interface SpawnGroupRepository extends CrudRepository<SpawnGroup, Long> {
}
