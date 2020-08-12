import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class InterviewTask extends Component {
  render() {
    const { interview_task } = this.props;
    let priorityString;
    let priorityClass;

    if (interview_task.priority === 1) {
      priorityClass = 'bg-danger text-light';
      priorityString = 'HIGH';
    }

    if (interview_task.priority === 2) {
      priorityClass = 'bg-warning text-light';
      priorityString = 'MEDIUM';
    }

    if (interview_task.priority === 3) {
      priorityClass = 'bg-info text-light';
      priorityString = 'LOW';
    }
    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {interview_task.interviewSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{interview_task.summary}</h5>
          <p className="card-text text-truncate ">
            {interview_task.acceptanceCriteria}
          </p>
          <Link
            to={`/updateProjectTask/${interview_task.interviewIdentifier}/${interview_task.interviewSequence}`}
            className="btn btn-primary"
          >
            View / Update
          </Link>

          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}
