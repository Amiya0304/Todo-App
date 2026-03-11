const API_URL = "http://localhost:8080/api/todo";

async function loadTodos() {

    const response = await fetch(API_URL);
    const todos = await response.json();

    const list = document.getElementById("todoList");

    list.innerHTML = "";

    todos.forEach(todo => {

        const li = document.createElement("li");

        const status = todo.completed
            ? "&#10004; Completed"
            : "&#9203; Pending";

        li.innerHTML = `
            <span class="${todo.completed ? 'completed' : ''}">
                ${todo.name}
            </span>

            <span class="status">
                ${status}
            </span>

            <div>
                <button onclick="editTodo(${todo.id}, '${todo.name}', ${todo.completed})">
                    Update
                </button>

                <button onclick="deleteTodo(${todo.id})">
                    Delete
                </button>
            </div>
        `;

        list.appendChild(li);
    });
}

async function addTodo() {

    const name = document.getElementById("todoName").value;
    const completed = document.getElementById("todoCompleted").checked;

    await fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name,
            completed: completed
        })
    });

    document.getElementById("todoName").value = "";
    document.getElementById("todoCompleted").checked = false;

    loadTodos();
}

async function deleteTodo(id) {

    await fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    });

    loadTodos();
}

async function editTodo(id, currentName, currentStatus) {

    const newName = prompt("Update task name:", currentName);

    if (newName === null) {
        return;
    }

    const newCompleted = confirm("Mark task as completed?");

    await fetch(`${API_URL}/${id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: newName,
            completed: newCompleted
        })
    });

    loadTodos();
}

loadTodos();