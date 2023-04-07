import './App.css';
import React from 'react';
import Login from './components/login';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import WelcomePage from './components/welcomePage';

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path='/login' element={<Login/>}></Route>
            <Route path='/user-page' element={<WelcomePage/>}></Route>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
