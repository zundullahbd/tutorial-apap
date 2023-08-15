import React from "react";
import listItems from "../../items.json";
import List from "../../components/List/index";
import "./index.css";
import { Fab, Badge } from '@mui/material'; // Import Fab and Badge components
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart'; // Import ShoppingCartIcon
import ViewStreamIcon from '@mui/icons-material/ViewStream'; // Import ViewStreamIcon

export default class Home extends React.Component {
    render() {
        return (
            <div className="container-fluid">
                <h1 className="text-center mt-3 mb-0">Plini Commerce</h1>
                <div style={{ position: "fixed", top: 25, right: 25 }}>
                    <Fab variant="extended" onClick={this.handleToggle}>
                        {this.state.cartHidden ? (
                            <ViewStreamIcon />
                        ) : (
                            <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                                <ShoppingCartIcon />
                            </Badge>
                        )}
                    </Fab>

                </div>
                <div>
                    <p className="text-center text-secondary text-sm font-italic">
                        (this is a <strong>class-based</strong> application)
                    </p>
                    <p className="text-center text-primary">
                        Your Balance: <b>{this.state.balance}</b>
                    </p>
                    <div className="container pt-3">
                        <div className="row mt-3">
                            {this.state.cartHidden ? (
                                <div className="col-sm">
                                    <List title="My Cart" items={this.state.cartItems} onItemClick={() => {}} />
                                </div>
                            ) : (
                                <div className="col-sm">
                                    <List
                                        title="List Items"
                                        items={this.state.shopItems}
                                        onItemClick={this.handleAddItemToCart}
                                        isShopList={true}
                                    />
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
    constructor(props) {
        super(props);
        this.state = {
            shopItems: listItems,
            cartItems: [],
            cartHidden: false,
            balance: 120,
        };
    }

    handleAddItemToCart = (item) => {
        const newItems = [...this.state.cartItems];
        const newItem = {...item};
        const targetInd = newItems.findIndex((it) => it.id === newItem.id);
        if (targetInd < 0) {
            newItem.inCart = true;
            newItems.push(newItem);
            this.updateShopItem(newItem, true)
        }
        this.setState({ cartItems: newItems })
    };

    updateShopItem = (item, inCart) => {
        const tempShopItems = this.state.shopItems;
        const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
        tempShopItems[targetInd].inCart = inCart;
        this.setState({ shopItems: tempShopItems });
    }

    handleToggle = () => {
        const cartHidden = this.state.cartHidden;
        this.setState({ cartHidden: !cartHidden})
    };
}