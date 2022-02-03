package com.example.nord;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LinkRepository extends CrudRepository<LinkEntity, Integer> {
    Optional<LinkEntity> findLinkEntityByUuid(UUID uuid);

    @Query(nativeQuery = true, value = "SELECT * FROM LINK")
    List<LinkEntity> getAll();

//        @Query(nativeQuery = true, value = "SELECT * FROM CODE c WHERE c.RESTRICTED = FALSE ORDER BY DATE DESC LIMIT 10")
//        List<LinkEntity> findAll();
}
