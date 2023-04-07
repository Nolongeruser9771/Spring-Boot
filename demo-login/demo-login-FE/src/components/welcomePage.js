import React from "react";
import './welcomePage.css';
import { Link } from "react-router-dom";

function WelcomePage({loginUser}) {
    return(
        <div className="welcome">
            <h2>Welcome: {loginUser.username}</h2>

            <ul className="user-info">
                <li>Email: {loginUser.email}</li>
                <li>Avatar: {loginUser.avatar}</li>
            </ul>
            <div className="logout">
                <Link to="/login"><button>Logout</button></Link>
            </div>
        </div>
    )
}

export default WelcomePage;