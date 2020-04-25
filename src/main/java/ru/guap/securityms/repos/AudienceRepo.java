package ru.guap.securityms.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.guap.securityms.domain.Audience;

import java.util.List;


public interface AudienceRepo extends CrudRepository<Audience, Integer> {

    @Modifying
    @Query(value = "update Audience a set a.principalId = ?1 where a.id = ?2")
    Integer updatePrincipalId(Integer principalId, Integer id);

    Audience findByNumber(String number);

    List<Audience> findByBuildingAndFloorOrderByNumber(Short building, Short floor);
}
