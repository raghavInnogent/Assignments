import { useNavigate } from "react-router-dom";
import "./card.css";

export default function Card({product, title, price, image, onDetails, onAddToCart }) {
  const navigate = useNavigate();

  const handleDetails = () => {
    navigate("/product", { state: { product } });

    if (onDetails) onDetails();
  };

  const handleAddToCart = () => {
    if (onAddToCart) onAddToCart(product);
  };

  return (
    <div className="card">
      <img src={image} alt={title} />
      <div className="info">
        <h3>{title}</h3>
        <p>₹ {price}</p>

        <div className="button-group">
          <button className="details-btn" onClick={handleDetails}>
            View Details
          </button>
          <button className="add-btn" onClick={handleAddToCart}>
            Add to Cart 🛒
          </button>
        </div>
      </div>
    </div>
  );
}
