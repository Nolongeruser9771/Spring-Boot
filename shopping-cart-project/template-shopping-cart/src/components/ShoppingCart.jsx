import { useEffect, useState } from "react";
import BillInformation from "./BillInformation";
import ProductList from "./ProductList";
import axios from 'axios';
const URL = "http://localhost:8080/api/v1/cartItems";

export default function ShoppingCart() {
    const [productList, setProductList] = useState([]);

    //get all items
    useEffect(() => {
        const getItemList = async () => {
            try {
                let itemList = await (await axios(URL)).data;
                setProductList(itemList);
            } catch (error) {
                console.log(error);
            }
        }
        getItemList();
    }, []);

    //delete handle
    const deleteHandle = async (id) => {
        if (!confirm("Are you sure to delete this item?")) {
            return;
        }
        try {
            let res = await fetch(URL + "/" + id, { method: "DELETE" });
            if (res.status === 200) {
                alert("item id " + id + " deleted successfully!");
                let updateItemList = productList.filter(item => item.id !== id);
                setProductList(updateItemList);
            } else {
                alert("error occured when deleting...")
            }
        } catch (error) {
            console.log(error);
        }
    }
    
    //increase amount
    const increaseAmount = async (id) => {
        try {
            const res = await fetch(`${URL}/${id}/increment`, { method: "PUT" });
            if (res.status === 200) {
                let updatedItems = productList.map(prod => {
                    if(prod.id === id){
                        return {...prod, count: prod.count+1};
                    } else {
                        return prod;
                    }
                })
                setProductList(updatedItems);
            }
        } catch (error) {
            console.log(error)
        }
    }

    //decrease amount
    const decreaseAmount = async (id) => {
        //valid count:
        let updatedItem = productList.filter(prod => prod.id===id)[0];
        if(updatedItem.count<=1){
            alert("Count should not less than 1")
            return;
        }
        try {
            const res = await fetch(`${URL}/${id}/decrement`, { method: "PUT" });
            if (res.status === 200) {
                let updatedItems = productList.map(prod => {
                    if(prod.id === id){
                        return {...prod, count: prod.count-1};
                    } else {
                        return prod;
                    }
                })
                setProductList(updatedItems);
            }
        } catch (error) {
            console.log(error)
        }
    }
    return (
        <>
            <div className="shopping-cart-container mt-5">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="mb-4">
                                <h2>Shopping Cart</h2>
                            </div>
                        </div>
                    </div>

                    <p className={productList.length !== 0 ? "hidden" : "fst-italic message"}>Không có sản phẩm trong giỏ hàng</p>
                    <div className="row shopping-cart">
                        <div className="col-md-8">
                            <ProductList itemList={productList}
                                deleteHandle={deleteHandle}
                                increaseAmount={increaseAmount}
                                decreaseAmount={decreaseAmount} />
                        </div>
                        <div className="col-md-4">
                            <BillInformation itemList={productList} />
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}