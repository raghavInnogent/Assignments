// src/components/AddressPage.jsx
import axios from "axios";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { clearCart } from "../redux/cartSlice";
import "./AddressPage.css";

export default function AddressPage() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const finalAmount = useSelector((state) => state.cart.finalAmount);
  const cartItems = useSelector((state) => state.cart.items);
  const promoCode = useSelector((state) => state.cart.appliedPromoCode);

  const [formData, setFormData] = useState({
    phone: "",
    street: "",
    city: "",
    state: "",
    pincode: "",
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handlePlaceOrder = async (e) => {
    e.preventDefault();

    const emptyField = Object.values(formData).some((v) => !v.trim());
    if (emptyField) {
      alert("Please fill in all the fields before placing the order.");
      return;
    }

    setLoading(true);

    try {
      const productsList = cartItems.map((item) => ({
        product: {
          id: item.id,
          title: item.title,
          description: item.description || "",
          price: item.price,
          category: item.category || "",
          image: item.image || "",
          ratingRate: item.rating?.rate || 0,
          ratingCount: item.rating?.count || 0
        },
        quantity: item.quantity
      }));

      const orderPayload = {
        products: productsList,
        
        deliveryDate: new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toISOString(),
        
        totalAmount: Math.round(finalAmount),
        
        status: "PENDING",
        
        address: {
          phoneNumber: formData.phone,
          streetAddress: formData.street,
          city: formData.city,
          state: formData.state,
          pincode: formData.pincode
        },
        
        promoCode: promoCode ? {
          id: promoCode.id,
          code: promoCode.code,
          discountPercentage: promoCode.discountPercentage,
          expiryDate: promoCode.expiryDate
        } : null
      };

      const response = await axios.post(
        'http://localhost:8080/createOrder',
        orderPayload,
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );

if (response.status === 200) {
        alert(
          `🎉 Order placed successfully!`);
        dispatch(clearCart());
        navigate("/");
      }
    } catch (error) {
      console.error("Order placement error:", error);
      const errorMessage = error.response?.data?.message || "Failed to place order. Please try again.";
      alert(`❌ Error: ${errorMessage}`);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="address-page">
      <div className="address-form-container">
        <h2 className="address-title">🏠 Delivery Address</h2>

        <form className="address-form" onSubmit={handlePlaceOrder}>
          <div className="form-row-horizontal">
            <label>Phone Number:</label>
            <input
              type="tel"
              name="phone"
              value={formData.phone}
              onChange={handleChange}
              placeholder="9876543210"
              disabled={loading}
            />
          </div>

          <div className="form-row-horizontal">
            <label>Street Address:</label>
            <input
              type="text"
              name="street"
              value={formData.street}
              onChange={handleChange}
              placeholder="Flat no, Building, Street..."
              disabled={loading}
            />
          </div>

          <div className="form-row-horizontal">
            <label>City:</label>
            <input
              type="text"
              name="city"
              value={formData.city}
              onChange={handleChange}
              placeholder="New Delhi"
              disabled={loading}
            />
          </div>

          <div className="form-row-horizontal">
            <label>State:</label>
            <input
              type="text"
              name="state"
              value={formData.state}
              onChange={handleChange}
              placeholder="Delhi"
              disabled={loading}
            />
          </div>

          <div className="form-row-horizontal">
            <label>Pincode:</label>
            <input
              type="text"
              name="pincode"
              value={formData.pincode}
              onChange={handleChange}
              placeholder="110001"
              disabled={loading}
            />
          </div>

        </form>
      </div>

      <div className="order-summary">
        <h2>🧾 Order Summary</h2>
        <div className="summary-box">
          <p>
            <strong>Total Amount:</strong>
          </p>
          <h3 className="amount">₹{finalAmount.toFixed(2)}</h3>
        </div>

        <button 
          onClick={handlePlaceOrder} 
          className="place-order-btn"
          disabled={loading}
        >
          {loading ? "Placing Order..." : "Place Order"}
        </button>
      </div>
    </div>
  );
}