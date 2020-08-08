import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import Login from './components/auth/login.component';
import Register from './components/auth/register.component';
import Home from './components/home.component';
import Profile from './components/dash/profile.component';
import BoardUser from './components/dash/board-user.component';
import BoardModerator from './components/dash/board-moderator.component';
import BoardAdmin from './components/dash/board-admin.component';

import AddJob from './components/jobs/AddJob';
import Job from './components/jobs/Job';
import JobsList from './components/jobs/JobsList';

import './App.css';

import Header from './components/layout/Header';
import AddInterview from './components/Interview/AddInterview';

import { Provider } from 'react-redux';
import store from './redux/store';

// class App extends React.Component {
//   constructor(props) {
//     super(props);
//     this.logOut = this.logOut.bind(this);

//     this.state = {
//       showModeratorBoard: false,
//       showAdminBoard: false,
//       currentUser: undefined,
//     };
//   }

//   componentDidMount() {
//     const user = AuthService.getCurrentUser();

//     if (user) {
//       this.setState({
//         currentUser: user,
//         showModeratorBoard: user.roles.includes('ROLE_MODERATOR'),
//         showAdminBoard: user.roles.includes('ROLE_ADMIN'),
//       });
//     }
//   }

//   logOut() {
//     AuthService.logout();
//   }

//   render() {
//     const { currentUser, showModeratorBoard, showAdminBoard } = this.state;

// HOOKS

const App = () => {
  return (
    <Provider store={store}>
      <Router>
        <div>
          <Header />

          <div className="container mt-3">
            <Switch>
              <Route exact path={['/', '/jobs']} component={JobsList} />
              <Route exact path="/add" component={AddJob} />
              <Route path="/jobs/:id" component={Job} />
              <Route exact path="/login" component={Login} />
              <Route exact path="/register" component={Register} />
              <Route exact path="/profile" component={Profile} />
              <Route path="/user" component={BoardUser} />
              <Route path="/mod" component={BoardModerator} />
              <Route path="/admin" component={BoardAdmin} />
              <Route path="/addInterview" component={AddInterview} />
            </Switch>
          </div>
        </div>
      </Router>
    </Provider>
  );
  // }
};

export default App;
