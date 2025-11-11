import axios from "axios";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import {
  decreaseQuantity,
  increaseQuantity,
  removeFromCart,
  setFinalAmount,
} from "../redux/cartSlice";
import "./CartPage.css";

export default function CartPage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const cartItems = useSelector((state) => state.cart.items);
  const [promoCode, setPromoCode] = useState("");
  const [discount, setDiscount] = useState(0);
  const [promoMessage, setPromoMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const subtotal = cartItems.reduce(
    (total, item) => total + item.price * item.quantity,
    0
  );
  const total = subtotal - discount;

  // ✅ Backend call to validate promo
  const handleApplyPromo = async () => {
    if (!promoCode.trim()) {
      setPromoMessage("⚠️ Please enter a promo code.");
      return;
    }

    setLoading(true);
    setPromoMessage("");

    try {
      const response = await axios.get(
        `http://localhost:8080/validate/${promoCode}`
      );

      if (response.data.success) {
        const discountPercent = response.data.discount;
        const discountValue = (subtotal * discountPercent) / 100;

        setDiscount(discountValue);
        setPromoMessage(
          `✅ Promo applied! You saved ₹${discountValue.toFixed(
            2
          )} (${discountPercent}% off)`
        );
      } else {
        setDiscount(0);
        setPromoMessage("❌ Invalid promo code");
      }
    } catch (error) {
      console.error("Promo check failed:", error);
      setDiscount(0);
      setPromoMessage("❌ Invalid or expired promo code.");
    } finally {
      setLoading(false);
    }
  };

  const handleCheckout = () => {
    if (cartItems.length === 0) {
      alert("Your cart is empty!");
      return;
    }

    dispatch(setFinalAmount(total));
    navigate("/address");
  };

  if (cartItems.length === 0) {
    return (
      <div className="empty-cart">
        <h2>Your cart is empty 🛒</h2>
        <p>Add some items to see them here.</p>
      </div>
    );
  }

  return (
    <div className="cart-container">
      <div className="cart-products">
        <h2>Your Cart</h2>

        {cartItems.map((item) => (
          <div key={item.id} className="cart-item-card">
            <img src={item.image} alt={item.title} className="cart-image" />

            <div className="cart-details">
              <h3 className="cart-title">{item.title}</h3>
              <p className="cart-price">₹{item.price}</p>

              <div className="quantity-section">
                <button onClick={() => dispatch(decreaseQuantity(item.id))}>
                  -
                </button>
                <span>{item.quantity}</span>
                <button onClick={() => dispatch(increaseQuantity(item.id))}>
                  +
                </button>
                <button
                  onClick={() => dispatch(removeFromCart(item.id))}
                  className="delete-btn"
                >
                  Delete
                </button>
              </div>
            </div>
          </div>
        ))}

        <div className="subtotal-section">
          <h3>
            Subtotal ({cartItems.length} items):{" "}
            <span className="subtotal-amount">₹{subtotal.toFixed(2)}</span>
          </h3>
        </div>
      </div>

      <div className="cart-summary">
        <div className="summary-section">
          <label className="field-label" htmlFor="promo">
            Promo Code
          </label>
          <div className="promo-flex">
            <input
              id="promo"
              type="text"
              placeholder="Enter promo code"
              value={promoCode}
              onChange={(e) => setPromoCode(e.target.value)}
              className="promo-input"
              disabled={loading}
            />
            <button
              onClick={handleApplyPromo}
              className="apply-promo-btn"
              disabled={loading}
            >
              {loading ? "Checking..." : "Apply"}
            </button>
          </div>

          {/* ✅ Message below input */}
          {promoMessage && (
            <p
              style={{
                color: promoMessage.startsWith("✅") ? "green" : "red",
                marginTop: "6px",
                fontSize: "0.9rem",
              }}
            >
              {promoMessage}
            </p>
          )}
        </div>

        <div className="summary-total">
          <p>
            Subtotal: <span>₹{subtotal.toFixed(2)}</span>
          </p>
          <p>
            Discount: <span>₹{discount.toFixed(2)}</span>
          </p>
          <h3>
            Total: <span>₹{total.toFixed(2)}</span>
          </h3>
        </div>

        <button onClick={handleCheckout} className="checkout-btn">
          Proceed to Checkout
        </button>
      </div>
    </div>
  );
}
