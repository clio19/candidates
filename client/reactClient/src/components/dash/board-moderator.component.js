import React, { Component } from 'react';

import UserService from '../../services/user.service';

import JobsList from '../jobs/JobsList';

export default class BoardModerator extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: '',
    };
  }

  componentDidMount() {
    UserService.getModeratorBoard().then(
      (response) => {
        this.setState({
          content: response.data,
        });
      },
      (error) => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString(),
        });
      }
    );
  }

  render() {
    return (
      <>
        <h3>{this.state.content}</h3>
        <JobsList />
      </>
    );
  }
}
