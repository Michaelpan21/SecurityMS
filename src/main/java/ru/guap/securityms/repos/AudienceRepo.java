package ru.guap.securityms.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.guap.securityms.domain.Audience;


public interface AudienceRepo extends CrudRepository<Audience, Long> {

    @Modifying
    @Query(value = "update Audience a set a.principalId = ?1 where a.id = ?2")
    Audience updatePrincipalId(Long principalId, Long id);

    Audience findByNumber(String number);
}
