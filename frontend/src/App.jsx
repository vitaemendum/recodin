import { useEffect, useState } from 'react';
import { Task } from './components/task';
import { apiService } from './services/apiService.js'; 
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);
  const [newTaskTitle, setNewTaskTitle] = useState('');
  const [emptyInputError, setEemptyInputError] = useState('');
  const [maxLengthError, setMaxLengthError] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
      apiService
        .getTasks()
        .then(data => {
          setTasks(data);
          setIsLoading(false);
        })
        .catch(error => {
          console.error('Error fetching todo items:', error.message);
          setIsLoading(false);
        });
  }, []);

  async function createTaskItem() {
    setMaxLengthError("");
    if (newTaskTitle.trim() === '') {
      setEemptyInputError('Task cannot be empty');
      return;
    }   

    if (newTaskTitle.length > 255) {
      setMaxLengthError('Task cannot be longer than 255 characters');
      return;
    }   

    try {
      const newTask = await apiService.createTask(newTaskTitle);
      setTasks(prevTasks => [...prevTasks, newTask]);
      setNewTaskTitle('');
      setEemptyInputError('');
    } catch (error) {
      console.error('Error creating new task item:', error.message);
    }
  }

  const handleTaskDeletion = (taskId) => {
    const updatedTasks = tasks.filter(task => task.id !== taskId);
    setTasks(updatedTasks);
  };

  return (
    <div className='card'>
      <h2 className='card-title'>TO-DO app</h2>
      <p className='card-description'>New task</p>
      <div className='card-input-section'>
        <input
          className='input-field'
          type="text"
          value={newTaskTitle}
          onChange={e => {
            setNewTaskTitle(e.target.value);
            setEemptyInputError('');
          }}
          placeholder="Input task..."
          maxLength={255}
        />
        <button 
          className="card-button"
          onClick={createTaskItem}
        >
            Add new task
        </button>        
      </div>
      {maxLengthError && <p className="error">{maxLengthError}</p>}
      {emptyInputError && <p className="error">{emptyInputError}</p>}
      <div className='card-tasks-section'>
        {isLoading ? (
            <p>Loading data...</p>
          ) : tasks.length > 0 ? (
            tasks.map(item => (
              <Task
                key={item.id}
                id={item.id}
                data={item}
                onTaskDeletion={handleTaskDeletion}
              />
            ))
          ) : (
            <p>No tasks available.</p>
        )}
      </div>
    </div>
  );
}

export default App;
