const API_URL = '/api/tasks';

export const apiService = {
    
    async getTasks() {
        try {
          const response = await fetch(API_URL);
          if (!response.ok) {
            throw new Error('Failed to fetch tasks.');
          }
          const tasks = await response.json();
          return tasks;
        } catch (error) {
          throw new Error(`Error fetching tasks: ${error.message}`);
        }
    },
      
    async createTask(newTaskTitle) {
        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                title: newTaskTitle,
                }),
            });

            if (!response.ok) {
                throw new Error('Failed to create task item.');
            }

            const newTask = await response.json();
            return newTask;
        } catch (error) {
            throw new Error(`Error creating new task item: ${error.message}`);
        }
    },

    async toggleTaskCompletion(taskId) {
        try {
            const response = await fetch(`${API_URL}/${taskId}/toggle`, {
                method: 'PATCH',
                headers: {
                'Content-Type': 'application/json',
                },
            });

            if (!response.ok) {
                throw new Error('Failed to toggle task completion.');
            }

            const completedTask = await response.json();
            return completedTask;
        } catch (error) {
            throw new Error(`Error toggling task completion: ${error.message}`);
        }
    },

    async deleteTask(taskId) {
        try {
            const response = await fetch(`${API_URL}/${taskId}`, {
                method: 'DELETE',
            });

            if (!response.ok) {
                throw new Error('Failed to delete task.');
            }
        } catch (error) {
            throw new Error(`Error deleting task: ${error.message}`);
        }
    },
};
