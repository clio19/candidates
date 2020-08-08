import { combineReducers } from 'redux';
import errorReducer from './errorReducer';
import interviewReducer from './interviewReducer';

export default combineReducers({
  errors: errorReducer,
  interview: interviewReducer,
});
