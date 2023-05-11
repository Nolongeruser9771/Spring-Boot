import ProductItem from "./ProductItem";

export default function ProductList({ itemList, deleteHandle, increaseAmount, decreaseAmount }) {
    return (
        <>
            <div className="product-list">
                {
                    itemList.map((item) => <ProductItem key={item.id}
                        itemList={itemList}
                        item={item}
                        deleteHandle={deleteHandle}
                        decreaseAmount={decreaseAmount}
                        increaseAmount={increaseAmount} />)
                }
            </div>
        </>
    );
}