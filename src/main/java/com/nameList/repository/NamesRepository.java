package com.nameList.repository;

import com.nameList.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NamesRepository extends JpaRepository<Name, UUID> {

    boolean existsByNameIgnoreCase(String name);
}
