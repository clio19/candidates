package com.hctec.candidates.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class InterviewTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String interviewSequence;
    @NotBlank(message = "Please include a interview summary")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;
    //ManyToOne with Backlog

    @Column(updatable = false)
    private String interviewIdentifer;
    private Date create_At;
    private Date update_At;

    public InterviewTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterviewSequence() {
        return interviewSequence;
    }

    public void setInterviewSequence(String interviewSequence) {
        this.interviewSequence = interviewSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getInterviewIdentifer() {
        return interviewIdentifer;
    }

    public void setInterviewIdentifer(String interviewIdentifer) {
        this.interviewIdentifer = interviewIdentifer;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }
    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }

    @Override
    public String toString() {
        return "InterviewTask{" +
                "id=" + id +
                ", interviewSequence='" + interviewSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", interviewIdentifer='" + interviewIdentifer + '\'' +
                ", create_At=" + create_At +
                ", update_At=" + update_At +
                '}';
    }

}
