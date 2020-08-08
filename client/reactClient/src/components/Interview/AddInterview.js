import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { createInterview } from '../../redux/actions/interviewActions';

import classnames from 'classnames';

class AddInterview extends Component {
  constructor() {
    super();

    this.state = {
      interviewName: '',
      interviewIdentifier: '',
      description: '',
      start_date: '',
      end_date: '',
      errors: {},
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newInterview = {
      interviewName: this.state.interviewName,
      interviewIdentifier: this.state.interviewIdentifier,
      description: this.state.description,
      start_date: this.state.start_date,
      end_date: this.state.end_date,
    };

    //    console.log(newInterview);
    this.props.createInterview(newInterview, this.props.history);
  }

  //life cycle methods change to hook
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  render() {
    const { errors } = this.state;

    return (
      <div>
        {
          //check name attribute input fields
          //create constructor
          //set state
          //set value on input fields
          //create onChange function
          //set onChange on each input field
          //bind on constructor
          //check state change in the react extension
          <div className="interview">
            <div className="container">
              <div className="row">
                <div className="col-md-8 m-auto">
                  <h5 className="display-4 text-center">Create Interview</h5>
                  <hr />
                  <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                      <input
                        type="text"
                        className={classnames('form-control form-control-lg', {
                          'is-invalid': errors.interviewName,
                        })}
                        placeholder="Interviewer Person Name"
                        name="interviewName"
                        value={this.state.interviewName}
                        onChange={this.onChange}
                      />
                      {errors.interviewName && (
                        <div className="invalid-feedback">
                          {errors.interviewName}
                        </div>
                      )}
                    </div>
                    <div className="form-group">
                      <input
                        type="text"
                        className={classnames('form-control form-control-lg', {
                          'is-invalid': errors.interviewIdentifier,
                        })}
                        placeholder="Job Interview "
                        name="interviewIdentifier"
                        value={this.state.interviewIdentifier}
                        onChange={this.onChange}
                      />
                      {errors.interviewIdentifier && (
                        <div className="invalid-feedback">
                          {errors.interviewIdentifier}
                        </div>
                      )}
                    </div>
                    <div className="form-group">
                      <textarea
                        className={classnames('form-control form-control-lg', {
                          'is-invalid': errors.description,
                        })}
                        placeholder="Interview Description"
                        name="description"
                        value={this.state.description}
                        onChange={this.onChange}
                      />
                      {errors.description && (
                        <div className="invalid-feedback">
                          {errors.description}
                        </div>
                      )}
                    </div>
                    <h6>Start Process Date</h6>
                    <div className="form-group">
                      <input
                        type="date"
                        className="form-control form-control-lg"
                        name="start_date"
                        value={this.state.start_date}
                        onChange={this.onChange}
                      />
                    </div>
                    <h6>Estimated End Date</h6>
                    <div className="form-group">
                      <input
                        type="date"
                        className="form-control form-control-lg"
                        name="end_date"
                        value={this.state.end_date}
                        onChange={this.onChange}
                      />
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
        }
      </div>
    );
  }
}

AddInterview.propTypes = {
  createInterview: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  errors: state.errors,
});

export default connect(mapStateToProps, { createInterview })(AddInterview);
