import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
const API_URL = "http://localhost:8080"

export default function UserDetail() {
    const { userId } = useParams();

    const [user, setUser] = useState("");
    const [avatar, setAvatar] = useState();

    //load info
    useEffect(() => {
        const getUserById = async () => {
            try {
                const res = await axios.get(API_URL + `/users/${userId}`);
                if (res.status === 200) {
                    const foundedUser = res.data;
                    console.log(foundedUser)
                    setUser(foundedUser);
                }
            } catch (error) {
                console.log(error);
            }
        }
        getUserById();
    }, [])

    //load avatar and create URL
    useEffect(() => {
        const fetchAvatar = async () => {
            try {
                const res = await fetch(API_URL+"/files/"+ userId);
                const imageBlob = await res.blob();
                console.log(imageBlob)
                const imageURL = window.URL.createObjectURL(imageBlob);
                setAvatar(imageURL);
            } catch (error) {
                console.log(error)
            }
        };
        fetchAvatar();
    }, [])

    return (
        <div className="page-content page-container" id="page-content">
            <div className="padding">
                <div className="row container d-flex justify-content-center">
                    <div className="col-xl-6 col-md-12">
                        <div className="card user-card-full">
                            <div className="row m-l-0 m-r-0">
                                <div className="col-sm-4 bg-c-lite-green user-profile">
                                    <div className="card-block text-center text-white">
                                        <div className="m-b-25">
                                            <img width={100} height={100} src={avatar} className="img-radius" alt="User-Profile-Image" />
                                        </div>
                                        <h6 className="f-w-600">UserID: {user.id}</h6>
                                        <p>Employee</p>
                                        <i className=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
                                    </div>
                                </div>
                                <div className="col-sm-8">
                                    <div className="card-block">
                                        <div className="row">
                                            <div className="col-sm-6">
                                                <p className="m-b-10 f-w-600">Name</p>
                                                <h6 className="text-muted f-w-400">{user.name}</h6>
                                            </div>
                                            <div className="col-sm-6">
                                                <p className="m-b-10 f-w-600">Email</p>
                                                <h6 className="text-muted f-w-400">{user.email}</h6>
                                            </div>
                                        </div>

                                        <div className="row">
                                            <div className="col-sm-6">
                                                <p className="m-b-10 f-w-600">Phone</p>
                                                <h6 className="text-muted f-w-400">{user.phone}</h6>
                                            </div>
                                            <div className="col-sm-6">
                                                <p className="m-b-10 f-w-600">Address</p>
                                                <h6 className="text-muted f-w-400">{user.address}</h6>
                                            </div>
                                        </div>
                                        <ul className="social-link list-unstyled m-t-40 m-b-10">
                                            <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="facebook" data-abc="true"><i className="mdi mdi-facebook feather icon-facebook facebook" aria-hidden="true"></i></a></li>
                                            <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="twitter" data-abc="true"><i className="mdi mdi-twitter feather icon-twitter twitter" aria-hidden="true"></i></a></li>
                                            <li><a href="#!" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="instagram" data-abc="true"><i className="mdi mdi-instagram feather icon-instagram instagram" aria-hidden="true"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}