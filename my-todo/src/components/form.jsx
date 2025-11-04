import { useState } from 'react'


export default function Form({ onAdd }) {
const [text, setText] = useState('')


const submit = (e) => {
e.preventDefault()
if (!text.trim()) return
onAdd(text)
setText('')
}


return (
<form className="todo-form" onSubmit={submit}>
<input
aria-label="Task input"
type="text"
placeholder="Add a new task..."
value={text}
onChange={(e) => setText(e.target.value)}
/>
<button type="submit">Add</button>
</form>
)
}