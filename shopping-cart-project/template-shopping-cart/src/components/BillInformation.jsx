import { useEffect, useState } from "react";

export default function BillInformation({ itemList }) {
    const [subTotal, setSubTotal] = useState(0);
    useEffect(() => {
        let tempt = 0;
        {   
            itemList.map(item => {
                tempt = tempt + (item.course.price * item.count);
            })
        }
        setSubTotal(tempt);
    }, [itemList]);

    return (
        <>
            <div className="bill">
                <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
                    <span className="text-black-50">Tạm tính:</span>
                    <span className="text-primary" id="sub-total-money">{subTotal.toLocaleString("en-US")} VND</span>
                </div>
                <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
                    <span className="text-black-50">VAT (10%):</span>
                    <span className="text-primary" id="vat-money">{(subTotal*0.1).toLocaleString("en-US")} VND</span>
                </div>
                <div className="border mb-2 p-3 fs-5 fw-normal d-flex justify-content-between align-items-center">
                    <span className="text-black-50">Thành tiền:</span>
                    <span className="text-primary" id="total-money">{Math.round(subTotal*1.1).toLocaleString("en-US")} VND</span>
                </div>
            </div>
        </>
    );
}