package com.hctec.candidates.models;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String interviewIdentifier;

    //OneToOne with interview

    //OneToMany interviewtasks


    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getInterviewIdentifier() {
        return interviewIdentifier;
    }

    public void setInterviewIdentifier(String interviewIdentifier) {
        this.interviewIdentifier = interviewIdentifier;
    }
}
