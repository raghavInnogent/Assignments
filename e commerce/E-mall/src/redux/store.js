// src/redux/store.js
import { configureStore } from "@reduxjs/toolkit";
import cartReducer from "./cartSlice";

// --- Load persisted state from localStorage ---
const loadState = () => {
  try {
    const serializedState = localStorage.getItem("cartState");
    if (serializedState === null) return undefined; // nothing saved yet
    return { cart: JSON.parse(serializedState) }; // load only cart slice
  } catch (err) {
    console.error("Failed to load state:", err);
    return undefined;
  }
};

// --- Save state to localStorage ---
const saveState = (state) => {
  try {
    const serializedState = JSON.stringify(state.cart);
    localStorage.setItem("cartState", serializedState);
  } catch (err) {
    console.error("Failed to save state:", err);
  }
};

// --- Initialize store with persisted data (if any) ---
const persistedState = loadState();

const store = configureStore({
  reducer: {
    cart: cartReducer,
  },
  preloadedState: persistedState,
});

// --- Subscribe to store changes to keep localStorage updated ---
store.subscribe(() => {
  saveState(store.getState());
});

export default store;
