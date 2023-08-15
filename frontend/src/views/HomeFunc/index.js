import listItems from "../../items.json";
import List from "../../components/List/index";
import React, { useState } from "react";
import "./index.css";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Fab } from "@material-ui/core";
import ViewStreamIcon from '@mui/icons-material/ViewStream';

// Building a React component using the functional programming paradigm
function App() {
    // Btw, this is hooks. useState function returns an array
    // containing the state and a function to set the state -> [state, setState].
    // What you see below is array destructuring.
    // Let's say you have an array const arr = ["aaa", "bbb"], to access the item
    // you can use index arr[0] OR destructure it like below
    // const [varName, index1] = arr, variable varName is guaranteed to get the value of index 0 OR "aaa"
    // Here is the illustration for this situation
    // Below is the return value of useState
    const [shopItems, setShopItems] = useState(() => listItems);
    const [cartItems, setCartItems] = useState(() => []);
    const [cartHidden, setCartHidden] = useState(true);
    const [balance, setBalance] = useState(120);

    function updateShopItem(item, inCart) {
        const tempShopItems = [...shopItems];
        const targetIndex = tempShopItems.findIndex((it) => it.id === item.id);
        tempShopItems[targetIndex].inCart = inCart;
        setShopItems(tempShopItems);
    }

    function handleToggle() {
        setCartHidden(!cartHidden);
    }

    function handleAddItemToCart(item) {
        const newItems = [...cartItems];
        const newItem = {...item};
        const targetIndex = newItems.findIndex((it) => it.id === newItem.id);

        if (targetIndex < 0) {
            newItem.inCart = true;
            newItems.push(newItem);
            updateShopItem(newItem, true)
        }
        setCartItems(newItems);
    }

    return (
        <div className="container-fluid">
            <h1 className="text-center mt-3 mb-0">Mini Commerce</h1>
            <div style={{ position: "fixed", top: 25, right: 25 }}>
                <Fab variant="extended" onClick={handleToggle}>
                    {cartHidden ? (
                        <Badge color="secondary" badgeContent={cartItems.length}>
                            <ShoppingCartIcon />
                        </Badge>
                    ) : (
                        <ViewStreamIcon />
                    )}
                </Fab>
            </div>
            <p className="text-center text-secondary text-sm font-italic">
                (this is a <strong>function-based</strong> application)
            </p>
            <p className="text-center text-primary">
                Your Balance: <b>{balance}</b>
            </p>
            <div className="container pt-3">
                <div className="row mt-3">
                    {!cartHidden ? (
                        <div className="col-sm">
                            <List title="My Cart" items={cartItems} onItemClick={() => {}} />
                        </div>
                    ) : (
                        <div className="col-sm">
                            <List
                                title="List Items"
                                items={shopItems}
                                onItemClick={handleAddItemToCart}
                                isShopList={true}
                            />
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}
export default App;