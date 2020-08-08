import React from 'react';
import { Link } from 'react-router-dom';

const CreateInterviewButton = () => {
  return (
    <React.Fragment>
      <Link to="/addInterview" className="btn btn-lg btn-info">
        Make Interview
      </Link>
    </React.Fragment>
  );
};

export default CreateInterviewButton;
