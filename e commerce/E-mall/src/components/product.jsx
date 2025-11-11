import { useLocation } from "react-router-dom";
import "./product.css";

export default function ProductDetails() {
  const location = useLocation();
  const product = location.state?.product;

  if (!product) return <p>No product data available!</p>;

  return (
    <div className="product-container">
      <div className="product-main">
        <div className="product-image-section">
          <img src={product.image} alt={product.title} className="product-image" />
        </div>

        <div className="product-info-section">
          <h1 className="product-title">{product.title}</h1>

          <p><strong>ID:</strong> {product.id}</p>
          {product.category && <p><strong>Category:</strong> {product.category}</p>}
          <p className="product-price"><strong>Price:</strong> ₹ {product.price}</p>

          {product.rating && (
            <p className="product-rating">
              <strong>Rating:</strong> ⭐ {product.rating.rate} ({product.rating.count} reviews)
            </p>
          )}

          {product.description && (
            <>
              <h3>Description</h3>
              <p className="product-description">{product.description}</p>
            </>
          )}

          <button className="add-to-cart-btn">Add to Cart 🛒</button>
        </div>
      </div>
    </div>
  );
}
