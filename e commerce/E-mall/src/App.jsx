import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import AddressPage from "./components/addressPage";
import CartPage from "./components/CartPage";
import Header from './components/header';
import Homepage from './components/homepage';
import OrderList from "./components/orderList";
import Product from './components/product';

function App() {
  return (
    <Router>
      <Header />
      <main style={{ padding: '20px', textAlign: 'center' }}>
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/product" element={<Product/>} />
          <Route path="/cart" element={<CartPage/>} />
          <Route path="/address" element={<AddressPage/>} />
          <Route path="/orders" element={<OrderList />} />

        </Routes>
      </main>
    </Router>
  );
}

export default App;
