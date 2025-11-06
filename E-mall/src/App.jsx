import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import "./App.css";
import Header from './components/header';
import Homepage from './components/homepage';
import Product from './components/product';

function App() {
  return (
    <Router>
      <Header />
      <main style={{ padding: '20px', textAlign: 'center' }}>
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/product" element={<Product/>} />
        </Routes>
      </main>
    </Router>
  );
}

export default App;
