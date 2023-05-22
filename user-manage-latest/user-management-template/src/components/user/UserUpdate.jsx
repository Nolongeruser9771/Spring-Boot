import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
// Use useForm hook from react-hook-form to create a form and handle validation
import { useForm } from "react-hook-form";
import axios from 'axios';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup'

const API_URL = "http://localhost:8080/users"; // TODO:change to user api url
function UserUpdate() {
    const [addressList, setAddressList] = useState([]);
    const navigate = useNavigate();
    const { userId } = useParams();

    useEffect(() => {
        // TODO: call api to get province list
        // url: https://provinces.open-api.vn/api/p/
        const getUserInfo = async () => {
            try {
                const res = await axios.get(API_URL + `/${userId}`);
                const user = await res.data;
                setValue("name", user?.name)
                setValue("email", user?.email)
                setValue("phone", user?.phone)
                setValue("address", user?.address)
            } catch (error) {
                console.log(error)
            }
        }
        getUserInfo();

        //get Province list
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

    //Khai báo validation cho từng trường
    const schema = yup.object({
        name: yup.string().required("Name is required"),
        email: yup.string()
            .matches(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/, "Not valid email")
            .required("Email is required"),
        //TODO: Khai báo thêm các trường khác
        phone: yup.string(),
        address: yup.string()
    }).required();

    const { register, handleSubmit, setValue, formState: { errors } } = useForm({
        //truyền schema vào
        resolver: yupResolver(schema),
        mode: "all"
    });

    const onSubmit = async data => {
        // TODO: write code to submit form. Ex: axios.post(API_URL, data)
        try {
            let result = await axios.put(API_URL + `/update/${userId}`, data);
            if (result.status === 200) {
                console.log(result.data);
                alert("User updated successfully!")
                //back to userList view
                setTimeout(() => {
                    navigate(`/users/${userId}`), 1000
                })
            }

        } catch (error) {
            console.log(error)
        }
    }

    return (
        <>
            <div className="container mt-5 mb-5">
                <h2 className="text-center text-uppercase mb-3">Update user</h2>

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
                                    Update User
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
}

export default UserUpdate;
