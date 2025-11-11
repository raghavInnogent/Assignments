import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

export default function Header() {
  const navigate = useNavigate();

  const cartItems = useSelector((state) => state.cart.items);
  const cartCount = cartItems.reduce((total, item) => total + item.quantity, 0);

  return (
    <header style={styles.header}>
      <h1 style={styles.title} onClick={() => navigate("/")}>
        🛒 E-MALL
      </h1>

      <div style={styles.icons}>

        {/* ✅ NEW — Orders Icon */}
        <span
          style={styles.icon}
          title="My Orders"
          onClick={() => navigate("/orders")}
        >
          📦
        </span>

        <span
          style={styles.icon}
          title="Notifications"
          onClick={() => alert("Notifications clicked!")}
        >
          🔔
        </span>

        {/* ✅ Cart Icon with Dynamic Blue Badge */}
        <div
          style={styles.cartContainer}
          title="Cart"
          onClick={() => navigate("/cart")}
        >
          <span style={styles.icon}>🛒</span>
          {cartCount > 0 && <span style={styles.badge}>{cartCount}</span>}
        </div>

        <span
          style={styles.icon}
          title="Profile"
          onClick={() => alert("Profile clicked!")}
        >
          👤
        </span>
      </div>
    </header>
  );
}

const styles = {
  header: {
    backgroundColor: "#000",
    padding: "16px 32px",
    color: "white",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    boxShadow: "0 2px 6px rgba(0,0,0,0.3)",
    position: "sticky",
    top: 0,
    zIndex: 1000,
  },
  title: {
    margin: 0,
    fontSize: "1.8rem",
    letterSpacing: "1px",
    fontWeight: "600",
    display: "flex",
    alignItems: "center",
    gap: "8px",
    cursor: "pointer",
  },
  icons: {
    display: "flex",
    alignItems: "center",
    gap: "20px",
    fontSize: "1.5rem",
    cursor: "pointer",
  },
  icon: {
    transition: "transform 0.2s",
  },
  cartContainer: {
    position: "relative",
    display: "inline-block",
  },
  badge: {
    position: "absolute",
    top: "-6px",
    right: "-10px",
    backgroundColor: "#007bff",
    color: "white",
    borderRadius: "50%",
    width: "18px",
    height: "18px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    fontSize: "0.75rem",
    fontWeight: "bold",
    boxShadow: "0 0 6px rgba(0, 123, 255, 0.6)",
  },
};
