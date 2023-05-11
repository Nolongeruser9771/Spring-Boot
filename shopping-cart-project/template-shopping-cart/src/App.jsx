import { useState } from 'react'
import './App.css'
import ShoppingCart from './components/ShoppingCart'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <ShoppingCart/>
    </>
  )
}

export default App
