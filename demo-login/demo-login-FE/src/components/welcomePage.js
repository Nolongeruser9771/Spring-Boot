import React from "react";
import './welcomePage.css';

function WelcomePage({loginUser}) {
    return(
        <div style={{display: display}} className="welcome">
            <h2>Welcome: {loginUser}</h2>

            <ul className="user-info">
                <li>Email: {loginUser}</li>
                <li>Avatar: {loginUser}</li>
            </ul>
            <div className="logout">
                <button>Logout</button>
            </div>
        </div>
    )
}

export default WelcomePage;