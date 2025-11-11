import axios from "axios";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux"; // ✅ Redux hook
import { addToCart } from "../redux/cartSlice"; // ✅ Action from cartSlice
import Card from "./card";
import "./homepage.css";

export default function Homepage() {
  const [products, setProducts] = useState([]);
  const [items, setItems] = useState([]);
  const [selectedValue, setSelectedValue] = useState("all");
  const dispatch = useDispatch(); 
  useEffect(() => {
    axios
      .get("http://localhost:8080/getAllProducts")
      .then((res) => {
        setProducts(res.data);
        setItems(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  // ✅ Filter handler
  const handleCategory = (e) => {
    const category = e.target.value;
    setSelectedValue(category);

    if (category === "all") {
      setItems(products);
    } else {
      setItems(products.filter((i) => i.category === category));
    }
  };

  // ✅ Add to Cart handler using Redux
  const handleAddToCart = (product) => {
    dispatch(addToCart(product));
  };

  return (
    <div className="home-container">
      <h2 className="home-title">
        {selectedValue === "all" ? "ALL PRODUCTS" : selectedValue.toUpperCase()}
      </h2>

      <h3>Select an option:</h3>

      <select
        value={selectedValue}
        onChange={handleCategory}
        className="dropdown"
      >
        <option value="all">ALL</option>
        <option value="men's clothing">Men's clothing</option>
        <option value="jewelery">Jewelery</option>
        <option value="electronics">Electronics</option>
        <option value="women's clothing">Women's clothing</option>
      </select>

      <div className="grid-container">
        {items.map((p) => (
          <Card
            key={p.id}
            product={p}
            title={p.title}
            price={p.price}
            image={p.image}
            onAddToCart={() => handleAddToCart(p)} // ✅ Pass callback to Card
          />
        ))}
      </div>
    </div>
  );
}
