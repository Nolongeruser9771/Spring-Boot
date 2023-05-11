import { Routes, Route } from 'react-router-dom'
import './App.css'
import ShoppingCart from './components/ShoppingCart'
import NotFound from './components/NotFound';

function App() {

  return (
    <>
    <Routes>
      <Route path="shopping-cart" element={<ShoppingCart/>}></Route>
      <Route path="/*" element={<NotFound/>}></Route>
    </Routes>
    </>
  )
}

export default App
