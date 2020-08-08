import axios from 'axios';
import { GET_ERRORS, GET_INTERVIEWS, GET_INTERVIEW } from './types';

export const createInterview = (interview, history) => async (dispatch) => {
  try {
    const res = await axios.post(
      'http://localhost:8080/api/interview',
      interview
    );
    history.push('/mod');
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getInterviews = () => async (dispatch) => {
  const res = await axios.get('http://localhost:8080/api/interview/all');
  dispatch({
    type: GET_INTERVIEWS,
    payload: res.data,
  });
};

export const getInterview = (id, history) => async (dispatch) => {
  const res = await axios.get(`http://localhost:8080/api/interview/${id}`);
  dispatch({
    type: GET_INTERVIEW,
    payload: res.data,
  });
};
