import TodoItem from './item'

export default function TodoList({ todos, onUpdate, onDelete }) {


    return (
        <ul className="todo-list">
            {todos.map((todo, index) => (
                <TodoItem
                    key={index}
                    todo={todo}
                    index={index}
                    onUpdate={onUpdate}
                    onDelete={onDelete}
                />
            ))}
        </ul>
    )
}
