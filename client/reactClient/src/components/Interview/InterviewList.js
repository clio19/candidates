import React, { Component } from 'react';

import InterviewItem from './InterviewItem';
import CreateInterviewButton from './CreateInterviewButton';

import { connect } from 'react-redux';
import { getInterviews } from '../../redux/actions/interviewActions';
import PropTypes from 'prop-types';

class InterviewBoard extends Component {
  componentDidMount() {
    this.props.getInterviews();
  }

  render() {
    const { interviews } = this.props.interview;
    return (
      <div className="Interviews">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Interviews</h1>
              <br />
              <CreateInterviewButton />
              <br />
              <hr />
              {interviews.map((interview) => (
                <InterviewItem key={interview.id} interview={interview} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

InterviewBoard.propTypes = {
  interview: PropTypes.object.isRequired,
  getInterviews: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  interview: state.interview,
});

export default connect(mapStateToProps, { getInterviews })(InterviewBoard);
