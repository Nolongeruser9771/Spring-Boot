import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";
import axios from "axios";
const API_URL = "http://localhost:8080"

let userList = [];

function UserList() {
  const [totalPages, setTotalPages] = useState();
  const [filterUsers, setFilterUsers] = useState([]);
  const [searchValue, setSearchValue] = useState("");
  const [searchInput, setSearchInput] = useState("");
  const [messageDisplay, setMessageDisplay] = useState("none");

  //display intial data
  useEffect(() => {
    //display user page 1
    const getUsersByPage = async () => {
      const res = await axios.get(API_URL + `/users?page=&size=&search=&sort=`)
      const data = await res.data;
      console.log(data)

      setTotalPages(data.totalPages)
      setFilterUsers(data.content);
    }
    getUsersByPage()
  }, [])

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

  //Paginate for sort list searched by name or email (After press "Enter")
  const fetchPage = async (currentPage, searchValue) => {
    const res = await axios.get(API_URL + `/users?page=${currentPage}&size=&search=${searchValue}&sort=`)
    const data = await res.data;
    console.log(searchValue)
    console.log(data)

    setTotalPages(data.totalPages)
    setFilterUsers(data.content)

    //"No user found" message
    setMessageDisplay(data.totalElements === 0 ? "block" : "none");
    return data.content;
  }

  const handlePageClick = async (page) => {
    let currentPage = page.selected;
    console.log(page.selected)

    const pageLoad = await fetchPage(currentPage, searchValue);

    setFilterUsers(pageLoad);
  }

  useEffect(() => {
    fetchPage(0, searchValue)
  }, [searchValue])

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
                onChange={e => setSearchInput(e.target.value)}
                onKeyDown={e => e.key === "Enter" ? setSearchValue(searchInput) : null}
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
                      <td width={220}>
                        <Link to={"/users/" + user.id} className="btn btn-success">Detail</Link>
                        <Link to={`/users/update/${user.id}`}><button className="btn btn-success">Update</button></Link>
                        <button className="btn btn-danger" onClick={() => deleteUser(user.id)}>Delete</button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>

              <p style={{ display: messageDisplay }} className="message">No User founded</p>
            </div>
          </div>
        </div>
      </div>

      <ReactPaginate
        previousLabel={'Previous'}
        nextLabel={'Next'}
        breakLabel={'...'}
        pageCount={totalPages}
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
