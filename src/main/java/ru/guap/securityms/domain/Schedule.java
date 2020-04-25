package ru.guap.securityms.domain;

import javax.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "subject_type_id")
    private SubjectType subjectType;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "audience_id")
    private Audience audience;

    private String dayOfWeek;
    private Boolean upperWeek;
    private Boolean lowerWeek;
    private Short lesson;

    public String getSubjectName() {
        return subject.getName();
    }

    public String getSubjectTypeName() {
        return subjectType.getType();
    }

    public String getProfessorName() {
        return professor.getShortName();
    }

    public String getProfessorDegree() {
        return professor.getDegree();
    }

    public String getGroupNumber() {
        return group.getNumber();
    }

    public String getAudienceNumber() {
        return audience.getNumber();
    }

    public Integer getAudienceId() {
        return audience.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean getUpperWeek() {
        return upperWeek;
    }

    public void setUpperWeek(Boolean upperWeek) {
        this.upperWeek = upperWeek;
    }

    public Boolean getLowerWeek() {
        return lowerWeek;
    }

    public void setLowerWeek(Boolean lowerWeek) {
        this.lowerWeek = lowerWeek;
    }

    public Short getLesson() {
        return lesson;
    }

    public void setLesson(Short lesson) {
        this.lesson = lesson;
    }
}
