import React, { Component } from 'react';
import { getInterview, createInterview } from '../../actions/interviewActions';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import classnames from 'classnames';

class UpdateInterview extends Component {
  //set state
  constructor() {
    super();

    this.state = {
      id: '',
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

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
    const {
      id,
      interviewName,
      interviewIdentifier,
      description,
      start_date,
      end_date,
    } = nextProps.interview;

    this.setState({
      id,
      interviewName,
      interviewIdentifier,
      description,
      start_date,
      end_date,
    });
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getInterview(id, this.props.history);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();

    const updateInterview = {
      id: this.state.id,
      interviewName: this.state.interviewName,
      interviewIdentifier: this.state.interviewIdentifier,
      description: this.state.description,
      start_date: this.state.start_date,
      end_date: this.state.end_date,
    };

    this.props.createInterview(updateInterview, this.props.history);
  }

  render() {
    const { errors } = this.state;
    return (
      <div className="interview">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">Update Interview form</h5>
              <hr />
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames('form-control form-control-lg', {
                      'is-invalid': errors.interviewName,
                    })}
                    placeholder="Interview Name"
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
                    className="form-control form-control-lg"
                    placeholder="Unique Interview ID"
                    name="interviewIdentifier"
                    value={this.state.interviewIdentifier}
                    onChange={this.onChange}
                    disabled
                  />
                </div>
                <div className="form-group">
                  <textarea
                    className={classnames('form-control form-control-lg', {
                      'is-invalid': errors.description,
                    })}
                    placeholder="Interview Description"
                    name="description"
                    onChange={this.onChange}
                    value={this.state.description}
                  />
                  {errors.description && (
                    <div className="invalid-feedback">{errors.description}</div>
                  )}
                </div>
                <h6>Start Date</h6>
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
    );
  }
}

UpdateInterview.propTypes = {
  getInterview: PropTypes.func.isRequired,
  createInterview: PropTypes.func.isRequired,
  interview: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  interview: state.interview.interview,
  errors: state.errors,
});

export default connect(mapStateToProps, { getInterview, createInterview })(
  UpdateInterview
);
