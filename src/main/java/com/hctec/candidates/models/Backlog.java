package com.hctec.candidates.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ITSequence = 0;
    private String interviewIdentifier;

    //OneToOne with interview
    @OneToOne(fetch = FetchType.EAGER) // Lazzy not load until is explicitly requested
    @JoinColumn(name="interview_id",nullable = false)
    @JsonIgnore
    private Interview interview;

    //OneToMany interviewtasks
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "backlog")
    private List<InterviewTask> interviewTasks = new ArrayList<>();

    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getITSequence() {
        return ITSequence;
    }

    public void setITSequence(Integer ITSequence) {
        this.ITSequence = ITSequence;
    }

    public String getInterviewIdentifier() {
        return interviewIdentifier;
    }

    public void setInterviewIdentifier(String interviewIdentifier) {
        this.interviewIdentifier = interviewIdentifier;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public List<InterviewTask> getInterviewTasks() {
        return interviewTasks;
    }

    public void setInterviewTasks(List<InterviewTask> interviewTasks) {
        this.interviewTasks = interviewTasks;
    }

}
