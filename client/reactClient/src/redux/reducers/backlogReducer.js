import {
  GET_BACKLOG,
  GET_INTERVIEW_TASK,
  DELETE_INTERVIEW_TASK,
} from '../actions/types';

const initialState = {
  interview_tasks: [],
  interview_task: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_BACKLOG:
      return {
        ...state,
        interview_tasks: action.payload,
      };
    case GET_INTERVIEW_TASK:
      return {
        ...state,
        interview_task: action.payload,
      };
    case DELETE_INTERVIEW_TASK:
      return {
        ...state,

        // TODO
      };

    default:
      return state;
  }
}
