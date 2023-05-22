import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
// Use useForm hook from react-hook-form to create a form and handle validation
import { useForm } from "react-hook-form";
import axios from 'axios';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup'

const API_URL = "http://localhost:8080/users"; // TODO:change to user api url
function UserCreate() {
  const [addressList, setAddressList] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    // TODO: call api to get province list
    // url: https://provinces.open-api.vn/api/p/
    const getProvinces = async () => {
      try {
        const res = await fetch("https://provinces.open-api.vn/api/p/");
        const provinceList = await res.json();
        setAddressList(provinceList);
      } catch (error) {
        console.log(error)
      }
    }
    getProvinces();
  }, []);

  //Khai baso validation cho từng trường
  const schema = yup.object({
    name: yup.string().required("Name is required"),
    email: yup.string()
      .matches(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/, "Not valid email")
      .required("Email is required"),
    //TODO: Khai báo thêm các trường khác
    phone: yup.string(),
    address: yup.string(),
    password: yup.string()
      .matches(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/, "Not valid password")
      .required("Password is required")
  }).required();

  // Sử dụng: useForm hook
  // register là function dùng để gán cho các input field để hook-form có thể theo dõi sự thay đổi của input field
  // dùng register thì không cần viết các sự kiện onchange cho các input field
  // handleSubmit: function khi submit form
  // errors: thuộc tính trong formState object, dùng để chứa các validation errors nếu có
  const { register, handleSubmit, formState: { errors } } = useForm({
    //truyền schema vào
    resolver: yupResolver(schema),
    mode: "all"
  });

  const onSubmit = async data => {
    // TODO: write code to submit form. Ex: axios.post(API_URL, data)
    try {
      let result = await axios.post(API_URL + "/create-user", data);
      console.log(result);
      alert("User created successfully!")
      //back to userList view
      setTimeout(() => {
        // navigate("/users"), 1000
        navigate(`/users/${result.data.id}`), 1000
      })
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <>
      <div className="container mt-5 mb-5">
        <h2 className="text-center text-uppercase mb-3">Create user</h2>

        <div className="row justify-content-center" align="left">
          <div className="col-md-6">
            <form onSubmit={handleSubmit(onSubmit)}>
              <div className="bg-light p-4">
                <div className="mb-3">
                  <label className="col-form-label">Name</label>
                  <input
                    type="text"
                    id="name"
                    className="form-control"
                    {...register("name")}
                  />
                  <p className="text-danger">{errors.name?.message}</p>
                </div>
                <div className="mb-3">
                  <label className="col-form-label">Email</label>
                  <input
                    type="text"
                    id="email"
                    className="form-control"
                    {...register("email")}
                  // TODO: sử dụng register để đăng kí thuộc tính để theo dõi trong form

                  />
                  <p className="text-danger">{errors.email?.message}</p>
                </div>
                <div className="mb-3">
                  <label className="col-form-label">Phone</label>
                  <input
                    type="text"
                    id="phone"
                    className="form-control"
                    {...register("phone")}
                  // TODO: sử dụng register để đăng kí thuộc tính để theo dõi trong form

                  />
                  <p className="text-danger">{errors.phone?.message}</p>
                </div>
                <div className="mb-3">
                  <label className="col-form-label">Address</label>
                  <select
                    className="form-select"
                    id="address"
                    {...register("address")}
                  // TODO: sử dụng register để đăng kí thuộc tính để theo dõi trong form

                  >
                    <option hidden value="">
                      Choose province
                    </option>
                    {addressList.map((item) => (
                      <option key={item.code} value={item.name}>
                        {item.name}
                      </option>
                    ))}
                  </select>
                  <p className="text-danger">{errors.address?.message}</p>
                </div>
                <div className="mb-3">
                  <label className="col-form-label">Password</label>
                  <input
                    type="password"
                    id="password"
                    className="form-control"
                    {...register("password")}
                  // TODO: sử dụng register để đăng kí thuộc tính để theo dõi trong form

                  />
                  <p className="text-danger">{errors.password?.message}</p>
                </div>
                <div className="mb-3">
                  <label className="col-form-label">Avatar</label>
                  <input
                    type="file"
                    id="avatar"
                    className="form-control"
                    {...register("file")}
                  // TODO: sử dụng register để đăng kí thuộc tính để theo dõi trong form

                  />
                  <p className="text-danger">{errors.avatar?.message}</p>
                </div>
              </div>
              <div className="text-center mt-3">
                <button
                  className="btn btn-secondary btn-back"
                  type="button"
                  onClick={() => navigate("/users")}
                >
                  Back
                </button>
                <button className="btn btn-success" type="submit" id="btn-save">
                  Create User
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
}

export default UserCreate;
