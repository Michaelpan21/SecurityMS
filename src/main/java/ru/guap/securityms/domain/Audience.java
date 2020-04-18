package ru.guap.securityms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Audience {

    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private Long principalId;

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principal) {
        this.principalId = principal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
