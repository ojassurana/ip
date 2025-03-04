package mahaveer;

import mahaveer.task.Task;
import mahaveer.task.Deadline;
import mahaveer.task.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class is responsible for loading and saving tasks to a file.
 * It provides methods to add, delete, mark, and unmark tasks.
 */
public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Constructs a new Storage instance.
     * <p>
     * This constructor ensures the storage file and its directory exist.
     * If the file is created for the first time, it prints a confirmation message.
     * </p>
     */
    public Storage() {
        File file = new File(FILE_PATH);
        try {
            file.getParentFile().mkdirs(); // Ensure the directory exists
            if (file.createNewFile()) {
                System.out.println("Storage file created: " + FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return a list of tasks loaded from the file; returns an empty list if the file does not exist or an error occurs.
     */
    public List<Task> loadTasks() {
        List<Task> taskList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line a line from the file representing a task in a specific format.
     * @return a Task object if parsing is successful; otherwise, returns null.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(", ");
        if (parts.length < 3) return null;
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;
        switch (type) {
        case "todo":
            task = new Task(description);
            break;
        case "deadline":
            if (parts.length < 4) return null;
            task = new Deadline(description, parts[3]);
            break;
        case "event":
            if (parts.length < 5) return null;
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            return null;
        }
        task.setDone(isDone);
        return task;
    }

    /**
     * Saves the provided list of tasks to the storage file.
     *
     * @param taskList the list of tasks to be saved.
     */
    private void saveTasks(List<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Adds a new todo task to the storage.
     *
     * @param description the description of the todo task.
     */
    public void addTodo(String description) {
        List<Task> taskList = loadTasks();
        taskList.add(new Task(description));
        saveTasks(taskList);
    }

    /**
     * Adds a new deadline task to the storage.
     *
     * @param description the task description.
     * @param by          the deadline time.
     */
    public void addDeadline(String description, String by) {
        List<Task> taskList = loadTasks();
        taskList.add(new Deadline(description, by));
        saveTasks(taskList);
    }

    /**
     * Adds a new event task to the storage.
     *
     * @param description the task description.
     * @param from        the event start time.
     * @param to          the event end time.
     */
    public void addEvent(String description, String from, String to) {
        List<Task> taskList = loadTasks();
        taskList.add(new Event(description, from, to));
        saveTasks(taskList);
    }

    /**
     * Deletes a task at the specified index from the storage.
     *
     * @param index the index of the task to be deleted.
     */
    public void deleteTask(int index) {
        List<Task> taskList = loadTasks();
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
            saveTasks(taskList);
        } else {
            System.out.println("Task index out of bounds.");
        }
    }

    /**
     * Marks the task at the specified index as done in the storage.
     *
     * @param index the index of the task to mark as done.
     */
    public void markTask(int index) {
        try {
            List<String> lines = new ArrayList<>();
            int currentIndex = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(", ");
                    if (currentIndex == index && parts.length >= 2) {
                        parts[1] = "1"; // Update second column to "1"
                        line = String.join(", ", parts);
                    }
                    lines.add(line);
                    currentIndex++;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating task file: " + e.getMessage());
        }
    }

    /**
     * Unmarks the task at the specified index in the storage.
     *
     * @param index the index of the task to unmark.
     */
    public void unmarkTask(int index) {
        try {
            List<String> lines = new ArrayList<>();
            int currentIndex = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(", ");
                    if (currentIndex == index && parts.length >= 2) {
                        parts[1] = "0"; // Update second column to "0"
                        line = String.join(", ", parts);
                    }
                    lines.add(line);
                    currentIndex++;
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating task file: " + e.getMessage());
        }
    }
}
