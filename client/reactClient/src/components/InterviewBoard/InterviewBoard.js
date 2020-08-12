import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class Interview extends Component {
  render() {
    const { id } = this.props.match.params;

    return (
      <div className="container">
        <Link to={`/addInterviewTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Interview Task</i>
        </Link>
        <br />
        <hr />
        {
          // <!-- Backlog STARTS HERE -->
        }
        <div className="container">
          <div className="row">
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-secondary text-white">
                  <h3>PERSPECT</h3>
                </div>
              </div>
              {
                // <!-- SAMPLE INTERVIEW TASK STARTS HERE -->
              }
              <div className="card mb-1 bg-light">
                <div className="card-header text-primary">
                  ID: interviewSequence -- Priority: priorityString
                </div>
                <div className="card-body bg-light">
                  <h5 className="card-title">interview_task.summary</h5>
                  <p className="card-text text-truncate ">
                    interview_task.acceptanceCriteria
                  </p>
                  <a href="" className="btn btn-primary">
                    View / Update
                  </a>

                  <button className="btn btn-danger ml-4">Delete</button>
                </div>
              </div>

              {
                // <!-- SAMPLE INTERVIEW TASK ENDS HERE -->
              }
            </div>
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-primary text-white">
                  <h3>In Progress</h3>
                </div>
              </div>
              {
                //  <!-- SAMPLE INTERVIEW TASK STARTS HERE -->
                //         <!-- SAMPLE INTERVIEW TASK ENDS HERE -->
              }
            </div>
            <div className="col-md-4">
              <div className="card text-center mb-2">
                <div className="card-header bg-success text-white">
                  <h3>ACCEPTED</h3>
                </div>
              </div>
              {
                // <!-- SAMPLE INTERVIEW TASK STARTS HERE -->
                // <!-- SAMPLE INTERVIEW TASK ENDS HERE -->
              }
            </div>
          </div>
        </div>
      </div>
    );
  }
}
