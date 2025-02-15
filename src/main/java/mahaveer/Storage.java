package mahaveer;

import mahaveer.task.Task;
import mahaveer.task.Deadline;
import mahaveer.task.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

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

    public void addTodo(String description) {
        List<Task> taskList = loadTasks();
        taskList.add(new Task(description));
        saveTasks(taskList);
    }

    public void addDeadline(String description, String by) {
        List<Task> taskList = loadTasks();
        taskList.add(new Deadline(description, by));
        saveTasks(taskList);
    }

    public void addEvent(String description, String from, String to) {
        List<Task> taskList = loadTasks();
        taskList.add(new Event(description, from, to));
        saveTasks(taskList);
    }

    public void deleteTask(int index) {
        List<Task> taskList = loadTasks();
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
            saveTasks(taskList);
        } else {
            System.out.println("Task index out of bounds.");
        }
    }

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
