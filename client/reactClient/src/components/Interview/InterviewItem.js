import React, { Component } from 'react';

import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import { deleteInterview } from '../../redux/actions/interviewActions';

class InterviewItem extends Component {
  onDeleteClick = (id) => {
    this.props.deleteInterview(id);
  };

  render() {
    const { interview } = this.props;

    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-2">
              <span className="mx-auto">{interview.interviewIdentifier}</span>
            </div>
            <div className="col-lg-6 col-md-4 col-8">
              <h3>{interview.interviewName}</h3>
              <p>{interview.description}</p>
            </div>
            <div className="col-md-4 d-none d-lg-block">
              <ul className="list-group">
                <a href="#">
                  <li className="list-group-item board">
                    <i className="fa fa-flag-checkered pr-1">
                      {' '}
                      Interview Board{' '}
                    </i>
                  </li>
                </a>
                <Link to={`/updateInterview/${interview.interviewIdentifier}`}>
                  <li className="list-group-item update">
                    <i className="fa fa-edit pr-1"> Update Interview Info</i>
                  </li>
                </Link>

                <li
                  className="list-group-item delete"
                  onClick={this.onDeleteClick.bind(
                    this,
                    interview.interviewIdentifier
                  )}
                >
                  <i className="fa fa-minus-circle pr-1"> Delete Interview</i>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

InterviewItem.propTypes = {
  deleteInterview: PropTypes.func.isRequired,
};

export default connect(null, { deleteInterview })(InterviewItem);
