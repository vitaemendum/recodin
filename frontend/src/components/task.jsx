import { useState } from "react";
import { apiService } from "../services/apiService";
import DeleteIcon from "../assets/x-circle.svg"
import "./task.css"

export const Task = (props) => {
  const [task, setTask] = useState(props.data);

  async function taskIsCompleted() {
    try {
      const updatedTask = await apiService.toggleTaskCompletion(props.data.id);
      setTask(updatedTask);
    } catch (error) {
      console.error("Error toggling task completion:", error.message);
    }
  }

  async function deleteTask() {
    try {
      await apiService.deleteTask(props.data.id);
      props.onTaskDeletion(props.data.id);
    } catch (error) {
      console.error("Error deleting task:", error.message);
    }
  }

  return (
    <div className={`task ${task.completed ? 'completed' : ''}`}>
      <label className="checkbox-label">
        <input
          type="checkbox"
          checked={task.completed}
          onChange={taskIsCompleted}
          className="checkbox-input"
        />
        <span className="custom-checkbox"></span>
      </label>
      {task.completed ? (

        <span className="completed">{task.title}</span>
      ) : (
        <span className="task-title">{task.title}</span>
      )}
      <div className="delete-icon" onClick={deleteTask}>
        <div className="delete-icon-wrapper">
          <img src={DeleteIcon} alt="Delete" />
        </div>
      </div>  
    </div>
  );
};

export default Task;
