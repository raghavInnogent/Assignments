import { useEffect, useState } from "react";
import Form from "./components/form";
import List from "./components/list";

const STORAGE_KEY = "todos_v1";

export default function App() {
  const [todos, setTodos] = useState(() => {
    const saved = localStorage.getItem(STORAGE_KEY);
    return saved ? JSON.parse(saved) : [];
  });

  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(todos));
  }, [todos]);

  const addTodo = (text) => {
    if (!text.trim()) return;
    setTodos((prev) => [...prev, { text: text.trim() }]);
  };

  const updateTodo = (index, newText) => {
    setTodos((prev) =>
      prev.map((t, i) => (i === index ? { ...t, text: newText } : t))
    );
  };

  const deleteTodo = (index) => {
    setTodos((prev) => prev.filter((_, i) => i !== index));
  };

  return (
    <div className="app-root">
      <header>
        <h1>React To-Do List</h1>
      </header>

      <main>
        <Form onAdd={addTodo} />
        <List todos={todos} onUpdate={updateTodo} onDelete={deleteTodo} />
      </main>
    </div>
  );
}
