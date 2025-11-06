import axios from "axios";
import { useEffect, useState } from "react";
import Card from "./card";
import "./homepage.css";

export default function Homepage() {
  const [products, setProducts] = useState([]);
  const [items, setItems] = useState([]);
  const [selectedValue, setSelectedValue] = useState("all"); // use "all" for default

  useEffect(() => {
    axios
      .get("https://fakestoreapi.com/products")
      .then((res) => {
        setProducts(res.data);
        setItems(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  const handleCategory = (e) => {
    const category = e.target.value;

    if (category === "all") {
      setItems(products);
      setSelectedValue("all"); // Dropdown and heading sync
    } else {
      setItems(products.filter((i) => i.category === category));
      setSelectedValue(category); // Match the option value
    }
  };

  return (
    <div className="home-container">
      <h2 className="home-title">
        {selectedValue === "all" ? "ALL PRODUCTS" : selectedValue.toUpperCase()}
      </h2>

      <h3>Select an option:</h3>

      <select value={selectedValue} onChange={handleCategory} className="dropdown">
        <option value="all">ALL</option>
        <option value="men's clothing">Men's clothing</option>
        <option value="jewelery">Jewelery</option>
        <option value="electronics">Electronics</option>
        <option value="women's clothing">Women's clothing</option>
      </select>

      <div className="grid-container">
        {items.map((p) => (
          <Card
            product={p}
            key={p.id}
            title={p.title}
            price={p.price}
            image={p.image}
          />
        ))}
      </div>
    </div>
  );
}
