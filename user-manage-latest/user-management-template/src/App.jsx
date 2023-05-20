import "./App.css";
import UserCreate from "./components/user/UserCreate";
import UserDetail from "./components/user/UserDetail";
import UserList from "./components/user/UserList";
import ResourceNotFound from "./components/ResourceNotFound";
import { Routes, Route, BrowserRouter } from "react-router-dom";

function App() {
  // /users
  // /users/create
  // /users/{id}
  return (
    <>
      <Routes>
        <Route path="/users">
          <Route index element={<UserList />}></Route>
          <Route path="create" element={<UserCreate />}></Route>
          <Route path=":userId" element={<UserDetail />}></Route>
        </Route>
        <Route path="*" element={<ResourceNotFound />}></Route>
      </Routes>
    </>
  );
}

export default App;
