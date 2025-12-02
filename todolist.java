<%@ page import="java.util.*" %>
<%
    // Create task list in session if not exists
    List<String> tasks = (List<String>) session.getAttribute("tasks");
    if (tasks == null) {
        tasks = new ArrayList<>();
        session.setAttribute("tasks", tasks);
    }

    // Handle Add Task
    if (request.getParameter("add") != null) {
        String task = request.getParameter("task");
        if (task != null && !task.trim().isEmpty()) {
            tasks.add(task);
        }
    }

    // Handle Delete Task
    if (request.getParameter("delete") != null) {
        int index = Integer.parseInt(request.getParameter("delete"));
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Java To-Do List Web App</title>
    <style>
        body { font-family: Arial; background: #f4f4f4; padding: 40px; }
        .container { background: white; padding: 20px; width: 350px;
                     margin: auto; border-radius: 10px; box-shadow: 0 0 10px #aaa; }
        input, button { width: 100%; padding: 10px; margin: 5px 0; }
        ul { padding: 0; list-style: none; }
        li { background: #eee; padding: 10px; margin: 8px 0;
             border-radius: 5px; display: flex; justify-content: space-between; }
        .del-btn { background: red; color: white; border: none; padding: 5px 10px;
                   border-radius: 5px; cursor: pointer; }
    </style>
</head>
<body>

<div class="container">
    <h2>Java To-Do List</h2>

    <!-- Add Task Form -->
    <form method="post">
        <input type="text" name="task" placeholder="Enter task..." required>
        <button type="submit" name="add">Add Task</button>
    </form>

    <ul>
        <%
            for (int i = 0; i < tasks.size(); i++) {
        %>
            <li>
                <%= tasks.get(i) %>
                <form method="post" style="display:inline;">
                    <button class="del-btn" name="delete" value="<%= i %>">Delete</button>
                </form>
            </li>
        <%
            }
        %>
    </ul>
</div>

</body>
</html>
