package com.hctec.candidates.services;

import com.hctec.candidates.exception.InterviewNotFoundException;
import com.hctec.candidates.models.Backlog;
import com.hctec.candidates.models.Interview;
import com.hctec.candidates.models.InterviewTask;
import com.hctec.candidates.repository.BacklogRepository;
import com.hctec.candidates.repository.InterviewRepository;
import com.hctec.candidates.repository.InterviewTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private InterviewTaskRepository interviewTaskRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    public InterviewTask addInterviewTask(String interviewIdentifier, InterviewTask interviewTask) {
       try {
            //InterviewsTasks to be added to a specific interview, interview != null, BL exists
            Backlog backlog = backlogRepository.findByInterviewIdentifier(interviewIdentifier);
            //set the bl to it
            System.out.println("backlog -->> " + backlog);
            interviewTask.setBacklog(backlog);
            //we want our interview sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
            Integer BacklogSequence = backlog.getITSequence();
            // Update the BL SEQUENCE
            BacklogSequence++;

            backlog.setITSequence(BacklogSequence);
            //Add Sequence to Interview Task
            interviewTask.setInterviewSequence(interviewIdentifier+"-"+BacklogSequence);
            interviewTask.setInterviewIdentifer(interviewIdentifier);

            //INITIAL priority when priority null
//        if(interviewTask.getPriority()==0||interviewTask.getPriority()==null){
//            interviewTask.setPriority(3);
//        }
            //INITIAL status when status is null
            if(interviewTask.getStatus()==""|| interviewTask.getStatus()==null){
                interviewTask.setStatus("PROSPECT");
            }

        //Fix bug with priority in Spring Boot Server, needs to check null first
        if(interviewTask.getPriority()==null||interviewTask.getPriority()==0){ //In the future we need interviewTask.getPriority()== 0 to handle the form
            interviewTask.setPriority(3);
        }
            return interviewTaskRepository.save(interviewTask);

        } catch (Exception e) {
            throw new InterviewNotFoundException(" Interview Not Found");
        }
    }

    public Iterable<InterviewTask> findBacklogById(String id) {
        Interview interview = interviewRepository.findByInterviewIdentifier(id);

        if (interview == null) {
            throw new InterviewNotFoundException("Interview with ID: '"+id+"' does not exist");
        }


     return interviewTaskRepository.findByInterviewIdentifierOrderByPriority(id);
    }

    public InterviewTask findITByInterviewSequence(String backlog_id, String it_id) {
        //make sure we are searching on the right backlog
//        return interviewTaskRepository.findByInterviewSequence(it_id);
        //make sure we are searching on an existing backlog
        Backlog backlog = backlogRepository.findByInterviewIdentifier(backlog_id);
        if(backlog==null){
            throw new InterviewNotFoundException("Interview with ID: '"+backlog_id+"' does not exist");
        }

        //make sure that our task exists
        InterviewTask interviewTask = interviewTaskRepository.findByInterviewSequence(it_id);

        if(interviewTask == null){
            throw new InterviewNotFoundException("Interview Task '"+it_id+"' not found");
        }

        //make sure that the backlog/interview id in the path corresponds to the right interview
        if(!interviewTask.getInterviewIdentifier().equals(backlog_id)){
            throw new InterviewNotFoundException("Interview Task '"+it_id+"' does not exist in interview: '"+backlog_id);
        }


        return interviewTask;
    }

    public InterviewTask updateByInterviewSequence(InterviewTask updatedTask, String backlog_id, String pt_id){
        InterviewTask interviewTask = findITByInterviewSequence(backlog_id, pt_id);

        interviewTask = updatedTask;

        return interviewTaskRepository.save(interviewTask);
    }


    public void deleteITByInterviewSequence(String backlog_id, String pt_id){
        InterviewTask interviewTask = findITByInterviewSequence(backlog_id, pt_id);
        interviewTaskRepository.delete(interviewTask);
    }


}

