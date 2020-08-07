package com.hctec.candidates.services;

import com.hctec.candidates.domain.Interview;
import com.hctec.candidates.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    public Interview saveOrUpdateInterview(Interview interview){

        //Logic

       return interviewRepository.save(interview);
    }

}