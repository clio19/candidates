import axios from 'axios';

import {
  GET_ERRORS,
  GET_BACKLOG,
  GET_INTERVIEW_TASK,
  DELETE_INTERVIEW_TASK,
} from './types';

//Fix bug with priority in Spring Boot Server, needs to check null first
export const addInterviewTask = (backlog_id, interview_task, history) => async (
  dispatch
) => {
  try {
    await axios.post(`/api/backlog/${backlog_id}`, interview_task);
    history.push(`/interviewBoard/${backlog_id}`);

    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getBacklog = (backlog_id) => async (dispatch) => {
  try {
    const res = await axios.get(`/api/backlog/${backlog_id}`);
    dispatch({
      type: GET_BACKLOG,
      payload: res.data,
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getInterviewTask = (backlog_id, pt_id, history) => async (
  dispatch
) => {
  try {
    const res = await axios.get(`/api/backlog/${backlog_id}/${pt_id}`);
    dispatch({
      type: GET_INTERVIEW_TASK,
      payload: res.data,
    });
  } catch (err) {
    history.push('/dashboard');
  }
};
