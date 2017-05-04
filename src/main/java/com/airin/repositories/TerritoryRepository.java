package com.airin.repositories;

import com.airin.entities.spawn.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by herasimau on 04/05/17.
 */
public interface TerritoryRepository extends JpaRepository<Territory, Long> {
}
