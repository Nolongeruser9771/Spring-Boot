import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";
import axios from "axios";
const API_URL = "http://localhost:8080"

let userList = [];

function UserList() {
  const [totalUser, setTotalUsers] = useState();
  const [filterUsers, setFilterUsers] = useState([]);
  const [searchValue, setSearchValue] = useState("");

  //data after search
  const filterUser = async () => {
    if (searchValue === "") {
      setFilterUsers(userList);
      return;
    }
    try {
      const res = await fetch(API_URL + `/users/search?name=${searchValue}&email=`);
      const filterList = await res.json();
      setFilterUsers(filterList);

      if (filterList.length == 0) {
        const result = await fetch(API_URL + `/users/search?name=&email=${searchValue}`);
        const doubleFilter = await result.json();
        setFilterUsers(doubleFilter);
      }
    } catch (error) {
      console.log(error);
    }
  }

  const deleteUser = async (id) => {
    try {
      const res = await fetch(API_URL + `/users/${id}`, { method: "DELETE" })
      if (res.status === 200) {
        let filterList = filterUsers.filter(user => user.id !== id);
        setFilterUsers(filterList);
      } else {
        alert("Error occurred when deleting user...")
      }
    } catch (error) {
      console.log(error);
    }
  }

  //Pagination
  useEffect(() => {
    const getAllUsers = async () => {
      const res = await axios.get(API_URL + "/users/get-all")
      const data = await res.data.length;
      console.log(data)
      setTotalUsers(data);
    }
    const getUsersByPage = async () => {
      const res = await fetch(API_URL + `/users?page1&size=4&sort=id,asc`)
      const data = await res.json();
      setFilterUsers(data);
    }
    getAllUsers();
    getUsersByPage()
  }, [])

  const fetchPage = async (currentPage) => {
    const res = await fetch(API_URL + `/users?page=${currentPage}&size=4&sort=id,asc`)
    const data = await res.json();
    console.log(data)
    return data;
  }

  const handlePageClick = async (page) => {
    let currentPage = page.selected;
    console.log(page.selected)

    const pageLoad = await fetchPage(currentPage);

    setFilterUsers(pageLoad);
  }

  return (
    <>
      <div className="container mt-5 mb-5">
        <h2 className="text-center text-uppercase">User list</h2>

        <div className="row justify-content-center">
          <div className="col-md-10">
            <div className="d-flex justify-content-between align-items-center mt-5 mb-4">
              <Link to="/users/create" className="btn btn-warning">Create user</Link>
              <input
                type="text"
                id="search"
                className="form-control w-50"
                placeholder="Search user by name or email"
                onKeyDown={e => e.key === "Enter" ? filterUser() : null}
                onChange={e => setSearchValue(e.target.value)}
              />
            </div>

            <div className="bg-light p-4">
              <table className="table table-hover">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th></th>
                  </tr>
                </thead>

                <tbody>
                  {filterUsers.map((user) => (
                    <tr key={user.id}>
                      <td>{user.id}</td>
                      <td>{user.name}</td>
                      <td>{user.email}</td>
                      <td>{user.phone}</td>
                      <td>{user.address}</td>
                      <td>
                        <Link to={"/users/" + user.id} className="btn btn-success">Detail</Link>
                        <button className="btn btn-danger" onClick={() => deleteUser(user.id)}>Delete</button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>

              <p className="message d-none"></p>
            </div>
          </div>
        </div>
      </div>

      <ReactPaginate
        previousLabel={'Previous'}
        nextLabel={'Next'}
        breakLabel={'...'}
        pageCount={totalUser == 0 ? 1 : (totalUser / 4)}
        marginPagesDisplayed={3}
        pageRangeDisplayed={3}
        onPageChange={handlePageClick}
        containerClassName="pagination justify-content-center"
        pageClassName="page-item"
        pageLinkClassName="page-link"
        previousClassName="page-item"
        previousLinkClassName="page-link"
        nextClassName="page-item"
        nextLinkClassName="page-link"
        breakClassName="page-item"
        breakLinkClassName="page-link"
        activeClassName="active"
      />
    </>
  );
}

export default UserList
