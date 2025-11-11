// src/pages/OrderList.jsx
import axios from "axios";
import { useEffect, useRef, useState } from "react";
import "./OrderList.css";

export default function OrderList() {
  const [orders, setOrders] = useState([]);
  const [openOrderId, setOpenOrderId] = useState(null);
  const [dropdownDirection, setDropdownDirection] = useState({}); // store direction per orderId

  useEffect(() => {
    axios
      .get("http://localhost:8080/getAllOrders")
      .then((res) => {
        setOrders(res.data);
        console.log(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  const dropdownRefs = useRef({});

  const toggleDropdown = (id) => {
    if (openOrderId === id) {
      setOpenOrderId(null);
    } else {
      // measure available space before showing
      const rect = dropdownRefs.current[id]?.getBoundingClientRect();
      const spaceBelow = window.innerHeight - rect.bottom;
      const spaceAbove = rect.top;
      const direction = spaceBelow < 200 && spaceAbove > spaceBelow ? "up" : "down";
      setDropdownDirection((prev) => ({ ...prev, [id]: direction }));
      setOpenOrderId(id);
    }
  };

  return (
    <div className="order-container">
      <h2 className="order-title">📦 My Orders</h2>

      <div className="order-list">
        {orders.length > 0 ? (
          orders.map((order) => (
            <div
              key={order.id}
              className="order-card"
              ref={(el) => (dropdownRefs.current[order.id] = el)}
            >
              <div className="order-row">
                <div>
                  <strong>Order ID:</strong> {order.id}
                </div>
                <div className={`order-status ${order.status.toLowerCase()}`}>
                  {order.status}
                </div>
              </div>

              <div className="order-row">
                <div>
                  <strong>Order Date:</strong> {order.orderDate}
                </div>
                <div>
                  <strong>Delivery Date:</strong> {order.deliveryDate}
                </div>
              </div>

              <div className="order-row">
                <div>
                  <strong>Total Amount:</strong> ₹{order.totalAmount}
                </div>

                <div className="dropdown-wrapper">
                  <button
                    className="dropdown-button"
                    onClick={() => toggleDropdown(order.id)}
                    aria-expanded={openOrderId === order.id}
                    aria-controls={`items-${order.id}`}
                  >
                    {openOrderId === order.id ? "Hide Items ▲" : "Show Items ▼"}
                  </button>

                  {openOrderId === order.id && (
                    <ul
                      id={`items-${order.id}`}
                      className={`item-list ${
                        dropdownDirection[order.id] === "up"
                          ? "open-up"
                          : "open-down"
                      }`}
                    >
                      {order.products?.map((item, index) => (
                        <li key={index} className="item">
                          🛍️ {item.product.title} — <strong>x{item.quantity}</strong>
                        </li>
                      ))}
                    </ul>
                  )}
                </div>
              </div>

              <div className="order-address">
                <h4>Shipping Address:</h4>
                <p>
                  {order.address?.streetAddress}, {order.address?.city}, {order.address?.state} -{" "}
                  {order.address?.pincode}
                </p>
                <p>📞 {order.address?.phoneNumber}</p>
              </div>
            </div>
          ))
        ) : (
          <p className="order-empty">No orders found.</p>
        )}
      </div>
    </div>
  );
}
