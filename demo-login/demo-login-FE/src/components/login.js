import React from 'react';
import { useState } from 'react';
import './login.css';
import WelcomePage from './welcomePage';

const API_URL = "http://localhost:8080/login";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loginUser, setloginUser] = useState();

    const postLogin = async() => {

        if(username === "" || password === "") {
            alert("Username and Password should not be empty")
            return;
        }

        const loginReq = {
            "username": username,
            "password": password
        }

        console.log(loginReq);

        try{
            let postResult = await fetch(API_URL, {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                method: "POST",
                body: JSON.stringify(loginReq)
            });
            //request success
            if(postResult.status===200) {
                let loginUser = await postResult.json().data;
                
                //Dieu huong toi /user-page va truyen props loginUser vao welcomePage
                //...enter code here
                
                return setloginUser(loginUser);
            
            } else {
                alert("Wrong password or username")
            }
        } catch(error) {
            console.log(error);
        }
    }
    return(
        <div className='login-form'>
            <h3>Login Form</h3>

            <label className='username'>Username</label>
            <input className='username'
                value={username}
                onChange={(e)=>setUsername(e.target.value)}>
            </input>

            <label className='password'>Password</label>
            <input className='password'
                type='password'
                value={password}
                onChange={(e)=>setPassword(e.target.value)}>
            </input>

            <div className='submit'>
                <button type='submit' onClick={postLogin}>Login</button>
            </div>
        </div>
    );
}

export default Login;