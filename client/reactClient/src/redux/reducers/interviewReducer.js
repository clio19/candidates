import { GET_INTERVIEWS, GET_INTERVIEW } from '../actions/types';

const initialState = {
  interviews: [],
  interview: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_INTERVIEWS:
      return {
        ...state,
        interviews: action.payload,
      };

    case GET_INTERVIEW:
      return {
        ...state,
        interview: action.payload,
      };
    default:
      return state;
  }
}
