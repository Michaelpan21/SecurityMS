package ru.guap.securityms.domain;

import javax.persistence.*;
import java.time.LocalTime;

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

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private String dayOfWeek;
    private Boolean upperWeek;
    private Boolean lowerWeek;

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

    public Short getLessonNumber() {
        return lesson.getNumber();
    }

    public LocalTime getStartTime() {
        return lesson.getStartTime();
    }

    public LocalTime getEndTime() {
        return lesson.getEndTime();
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

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
