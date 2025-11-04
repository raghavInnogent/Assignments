import { useEffect, useRef, useState } from "react";

export default function Item({ todo, index, onUpdate, onDelete }) {
  const [editing, setEditing] = useState(false);
  const [text, setText] = useState(todo.text);
  const inputEl = useRef();

  useEffect(() => {
    if (editing && inputEl.current) {
      inputEl.current.focus();
    }
  }, [editing]);

  const handleSave = () => {
    const newText = text.trim();
    if (newText === "") return;
    onUpdate(index, newText);
    setEditing(false);
  };

  const handleCancel = () => {
    setText(todo.text);
    setEditing(false);
  };

  return (
    <li className="todo-item">
      <div className="todo-main">
        {editing ? (
          <input
            ref={inputEl}
            value={text}
            onChange={(e) => setText(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter") handleSave();
              if (e.key === "Escape") handleCancel();
            }}
          />
        ) : (
          <span>{todo.text}</span>
        )}
      </div>

      <div className="todo-buttons">
        {editing ? (
          <>
            <button className="save-btn" onClick={handleSave}>Save</button>
            <button className="cancel-btn" onClick={handleCancel}>Cancel</button>
          </>
        ) : (
          <>
            <button className="edit-btn" onClick={() => setEditing(true)}>Edit</button>
            <button className="delete-btn" onClick={() => onDelete(index)}>Delete</button>
          </>
        )}
      </div>
    </li>
  );
}
