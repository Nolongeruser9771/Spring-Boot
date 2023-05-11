import { useEffect, useState } from "react";
const URL = "http://localhost:8080/api/v1/cartItems";

export default function ProductItem({ item, deleteHandle, increaseAmount, decreaseAmount }) {
    const [thumbnailURL, setThumbnailURL] = useState();

    useEffect(() => {
        const fetchThumbnail = async () => {
            const res = await fetch("http://localhost:8080/api/v1/thumbnail/" + item.id);
            const imageBlob = await res.blob();
            const imageURL = window.URL.createObjectURL(imageBlob);
            setThumbnailURL(imageURL);
        };
        fetchThumbnail();
    },[item])

    return (
        <>
            <div className="product-item d-flex border mb-4">
                <div className="image">
                    <img src={thumbnailURL} alt="sản phẩm 1" />
                </div>
                <div className="info d-flex flex-column justify-content-between px-4 py-3 flex-grow-1">
                    <div>
                        <div className="d-flex justify-content-between align-items-center">
                            <h2 className="text-dark fs-5 fw-normal">
                                {item.course.name}
                            </h2>
                            <h2 className="text-danger fs-5 fw-normal">
                                {item.course.price.toLocaleString("en-US")} VND
                            </h2>
                        </div>
                        <div className="text-black-50">
                            <div className="d-inline-block me-3">
                                <button className="border py-2 px-3 d-inline-block fw-bold bg-light" onClick={() => decreaseAmount(item.id)}>-</button>
                                <span className="py-2 px-3 d-inline-block fw-bold">{item.count}</span>
                                <button className="border py-2 px-3 d-inline-block fw-bold bg-light" onClick={() => increaseAmount(item.id)}>+</button>
                            </div>
                        </div>
                    </div>
                    <div>
                        <button className="text-primary border-0 bg-transparent fw-light"
                            onClick={() => deleteHandle(item.id)}>
                            <span><i className="fa-solid fa-trash-can"></i></span>
                            Xóa
                        </button>
                    </div>
                </div>
            </div>
        </>
    );
}