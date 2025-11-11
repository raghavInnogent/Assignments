import { useNavigate } from "react-router-dom";
import "./card.css";

export default function Card({ product, title, price, image, onDetails, onAddToCart }) {
  const navigate = useNavigate();

  // ✅ Navigate to product details page
  const handleDetails = () => {
    navigate("/product", { state: { product } });
    if (onDetails) onDetails(product);
  };

  // ✅ Trigger Add to Cart callback (connected to Redux)
  const handleAddToCart = () => {
    if (onAddToCart) onAddToCart(product);
  };

  return (
    <div className="card">
      <img src={image} alt={title} className="card-image" />

      <div className="info">
        <h3 className="card-title">{title}</h3>
        <p className="card-price">₹ {price}</p>

        <div className="button-group">
          <button className="details-btn" onClick={handleDetails}>
            View Details
          </button>
          <button className="add-btn" onClick={handleAddToCart}>
            🛒 Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
}
