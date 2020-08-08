import React, { useState, useEffect } from 'react';

import { Link } from 'react-router-dom';

import AuthService from '../../services/auth.service';

const Header = () => {
  const [showModeratorBoard, setShowModeratorBoard] = useState(false);
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      setShowModeratorBoard(user.roles.includes('ROLE_MODERATOR'));
      setShowAdminBoard(user.roles.includes('ROLE_ADMIN'));
    }
  }, []);

  const logOut = () => {
    AuthService.logout();
  };

  return (
    <nav className="navbar navbar-expand navbar-dark bg-dark">
      <a href="/jobs" className="navbar-brand">
        Candidates
      </a>
      <div className="navbar-nav mr-auto">
        {showModeratorBoard && (
          <li className="nav-item">
            <Link to={'/jobs'} className="nav-link">
              Jobs
            </Link>
          </li>
        )}
        {showModeratorBoard && (
          <li className="nav-item">
            <Link to={'/add'} className="nav-link">
              Add Job
            </Link>
          </li>
        )}
        {showModeratorBoard && (
          <li className="nav-item">
            <Link to={'/mod'} className="nav-link">
              Moderator Board
            </Link>
          </li>
        )}

        {showAdminBoard && (
          <li className="nav-item">
            <Link to={'/admin'} className="nav-link">
              Admin Board
            </Link>
          </li>
        )}

        {currentUser && (
          <li className="nav-item">
            <Link to={'/user'} className="nav-link">
              User Board
            </Link>
          </li>
        )}
      </div>

      {currentUser ? (
        <div className="navbar-nav ml-auto">
          <li className="nav-item">
            <Link to={'/profile'} className="nav-link">
              {currentUser.username}
            </Link>
          </li>
          <li className="nav-item">
            <a href="/login" className="nav-link" onClick={logOut}>
              LogOut
            </a>
          </li>
        </div>
      ) : (
        <div className="navbar-nav ml-auto">
          <li className="nav-item">
            <Link to={'/login'} className="nav-link">
              Login
            </Link>
          </li>

          <li className="nav-item">
            <Link to={'/register'} className="nav-link">
              Sign Up
            </Link>
          </li>
        </div>
      )}
    </nav>
  );
};

export default Header;
