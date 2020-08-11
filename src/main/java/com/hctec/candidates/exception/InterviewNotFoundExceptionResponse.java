package com.hctec.candidates.exception;

public class InterviewNotFoundExceptionResponse {

    private String InterviewNotFound;

    public InterviewNotFoundExceptionResponse(String interviewNotFound) {
        InterviewNotFound = interviewNotFound;
    }

    public String getInterviewNotFound() {
        return InterviewNotFound;
    }

    public void setInterviewNotFound(String interviewNotFound) {
        InterviewNotFound = interviewNotFound;
    }
}
