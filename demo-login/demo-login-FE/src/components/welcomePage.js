import React from "react";
import './welcomePage.css';
import { Link, useParams } from "react-router-dom";

function WelcomePage() {
    const {id} = useParams();
    const loginUser = localStorage.getItem({id}.id);

    let userObj = JSON.parse(loginUser)
    console.log(userObj)

    return(
        <div className="welcome">
            <h2>Welcome: {userObj.username}</h2>

            <ul className="user-info">
                <li>Email: {userObj.email}</li>
                <li>Avatar: {userObj.avatar}</li>
            </ul>
            <div className="logout">
                <Link to="/login"><button>Logout</button></Link>
            </div>
        </div>
    )
}

export default WelcomePage;