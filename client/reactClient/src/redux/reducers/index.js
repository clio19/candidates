import { combineReducers } from 'redux';
import errorReducer from './errorReducer';
import interviewReducer from './interviewReducer';
import backlogReducer from './backlogReducer';

export default combineReducers({
  errors: errorReducer,
  interview: interviewReducer,
  backlog: backlogReducer,
});
