import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import classnames from 'classnames';
import { addInterviewTask } from '../../../redux/actions/backlogActions';
import PropTypes from 'prop-types';

class AddInterviewTask extends Component {
  constructor(props) {
    super(props);
    const { id } = this.props.match.params;

    this.state = {
      summary: '',
      acceptanceCriteria: '',
      status: '',
      priority: 0,
      dueDate: '',
      interviewIdentifier: id,
      errors: {},
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  // on change
  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  //on submit
  onSubmit(e) {
    e.preventDefault();

    const newTask = {
      summary: this.state.summary,
      acceptanceCriteria: this.state.acceptanceCriteria,
      status: this.state.status,
      priority: this.state.priority,
      dueDate: this.state.dueDate,
    };

    this.props.addInterviewTask(
      this.state.interviewIdentifier,
      newTask,
      this.props.history
    );
  }

  render() {
    const { id } = this.props.match.params;

    return (
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link to={`/interviewBoard/${id}`} className="btn btn-light">
                Back to Interview Board
              </Link>
              <h4 className="display-4 text-center">Add Interview Task</h4>
              <p className="lead text-center">
                Interview Name + Interview Code
              </p>
              <form>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    name="summary"
                    placeholder="Interview Task summary"
                  />
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Acceptance Criteria"
                    name="acceptanceCriteria"
                  />
                </div>
                <h6>Due Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="dueDate"
                  />
                </div>
                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="priority"
                  >
                    <option value={0}>Select Priority</option>
                    <option value={1}>High</option>
                    <option value={2}>Medium</option>
                    <option value={3}>Low</option>
                  </select>
                </div>

                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="status"
                  >
                    <option value="">Select Status</option>
                    <option value="PROSPECT">PROSPECT</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="DONE">DONE</option>
                  </select>
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  errors: state.errors,
});

AddInterviewTask.propTypes = {
  addInterviewTask: PropTypes.func.isRequired,
};

export default connect(mapStateToProps, { addInterviewTask })(AddInterviewTask);
