// src/redux/cartSlice.js
import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  items: [], // Each item => { id, title, price, image, quantity }
  totalAmount: 0,
  finalAmount: 0, // ✅ added for checkout summary
};

const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addToCart: (state, action) => {
      const item = action.payload;
      const existing = state.items.find((i) => i.id === item.id);

      if (existing) {
        existing.quantity += 1;
      } else {
        state.items.push({ ...item, quantity: 1 });
      }

      state.totalAmount = state.items.reduce(
        (sum, i) => sum + i.price * i.quantity,
        0
      );
    },

    removeFromCart: (state, action) => {
      const id = action.payload;
      state.items = state.items.filter((i) => i.id !== id);
      state.totalAmount = state.items.reduce(
        (sum, i) => sum + i.price * i.quantity,
        0
      );
    },

    increaseQuantity: (state, action) => {
      const id = action.payload;
      const item = state.items.find((i) => i.id === id);
      if (item) item.quantity += 1;
      state.totalAmount = state.items.reduce(
        (sum, i) => sum + i.price * i.quantity,
        0
      );
    },

    decreaseQuantity: (state, action) => {
      const id = action.payload;
      const item = state.items.find((i) => i.id === id);
      if (item && item.quantity > 1) item.quantity -= 1;
      state.totalAmount = state.items.reduce(
        (sum, i) => sum + i.price * i.quantity,
        0
      );
    },

    clearCart: (state) => {
      state.items = [];
      state.totalAmount = 0;
    },

    // ✅ new reducer to store final checkout total
    setFinalAmount: (state, action) => {
      state.finalAmount = action.payload;
    },
  },
});

export const {
  addToCart,
  removeFromCart,
  increaseQuantity,
  decreaseQuantity,
  clearCart,
  setFinalAmount,
} = cartSlice.actions;

export default cartSlice.reducer;
