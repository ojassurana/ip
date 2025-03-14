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
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (parentDir.mkdirs()) {
                    System.out.println("Storage directory created: " + parentDir.getAbsolutePath());
                } else {
                    System.out.println("Failed to create storage directory: " + parentDir.getAbsolutePath());
                }
            }

            if (file.createNewFile()) {
                System.out.println("Storage file created: " + FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }


    /**
     * Updates the task completion status in the file.
     *
     * @param index   the index of the task to update
     * @param status  the new status ("1" for marked, "0" for unmarked)
     */
    private void updateTaskStatus(int index, String status) {
        try {
            List<String> lines = new ArrayList<>();
            int currentIndex = 0;
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(", ");
                    if (currentIndex == index && parts.length >= 2) {
                        parts[1] = status; // Update second column to new status
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
     * Marks the task at the specified index as done in the storage.
     *
     * @param index the index of the task to mark as done.
     */
    public void markTask(int index) {
        updateTaskStatus(index, "1");
    }

    /**
     * Unmarks the task at the specified index in the storage.
     *
     * @param index the index of the task to unmark.
     */
    public void unmarkTask(int index) {
        updateTaskStatus(index, "0");
    }
}
