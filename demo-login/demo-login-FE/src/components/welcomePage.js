import React from "react";
import './welcomePage.css';

function WelcomePage({loginUser}) {
    return(
        <div className="welcome">
            <h2>Welcome: {loginUser.username}</h2>

            <ul className="user-info">
                <li>Email: {loginUser.email}</li>
                <li>Avatar: {loginUser.avatar}</li>
            </ul>
            <div className="logout">
                <button>Logout</button>
            </div>
        </div>
    )
}

export default WelcomePage;