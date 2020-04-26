package ru.guap.securityms.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Audience {

    @Id
    @GeneratedValue
    private Integer id;
    private String number;
    private Integer principalId;
    private Short building;
    private Short floor;

    @OneToMany(mappedBy = "audience", fetch = FetchType.EAGER)
    private List<Schedule> schedule;

    public Short getBuilding() {
        return building;
    }

    public void setBuilding(Short building) {
        this.building = building;
    }

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public Integer getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Integer principal) {
        this.principalId = principal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}
