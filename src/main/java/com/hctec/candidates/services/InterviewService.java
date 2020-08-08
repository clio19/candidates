package com.hctec.candidates.services;

import com.hctec.candidates.exception.InterviewIdException;
import com.hctec.candidates.models.Interview;
import com.hctec.candidates.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    public Interview saveOrUpdateInterview(Interview interview){

        try {
            interview.setInterviewIdentifier(interview.getInterviewIdentifier().toUpperCase());
            return interviewRepository.save(interview);
        } catch (Exception e) {
            throw  new InterviewIdException(" Interview ID '" + interview.getInterviewIdentifier().toUpperCase()+"' already exists");
        }

    }

    public Interview findInterviewByIdentifier(String interviewId){

        Interview interview = interviewRepository.findByInterviewIdentifier(interviewId.toUpperCase());

        if(interview == null){
            throw new InterviewIdException("Interview ID '"+interviewId+"' does not exist");
        }

        return interview;
    }

    public Iterable<Interview> findAllInterviews(){
        return interviewRepository.findAll();
    }

    public void deleteInterviewByIdentifier(String interviewid){
        Interview interview = interviewRepository.findByInterviewIdentifier(interviewid.toUpperCase());

        if(interview == null){
            throw  new  InterviewIdException("Cannot Interview with ID '"+interviewid+"'. This interview does not exist");
        }

        interviewRepository.delete(interview);
    }

}