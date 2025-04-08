import java.util.*;

class Task {
    String title;
    boolean isDone;

    Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    void markDone() {
        isDone = true;
    }

    public String toString() {
        return (isDone ? "[✔] " : "[ ] ") + title;
    }
}

class Project {
    String name;
    List<Task> tasks = new ArrayList<>();

    Project(String name) {
        this.name = name;
    }

    void addTask(String taskName) {
        tasks.add(new Task(taskName));
    }

    void listTasks() {
        System.out.println("Tasks for project: " + name);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    void markTaskDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markDone();
        }
    }
}

public class ProjectApp {
    static Scanner scanner = new Scanner(System.in);
    static List<Project> projects = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n📌 Project Manager Menu");
            System.out.println("1. Create new project");
            System.out.println("2. Add task to project");
            System.out.println("3. List projects");
            System.out.println("4. View tasks in a project");
            System.out.println("5. Mark task as done");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> createProject();
                case 2 -> addTaskToProject();
                case 3 -> listProjects();
                case 4 -> viewProjectTasks();
                case 5 -> markTaskDone();
                case 0 -> {
                    System.out.println("Exiting... Goodbye! 👋");
                    return;
                }
                default -> System.out.println("Invalid choice ❌");
            }
        }
    }

    static void createProject() {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        projects.add(new Project(name));
        System.out.println("✅ Project created!");
    }

    static void addTaskToProject() {
        listProjects();
        System.out.print("Choose project number: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline
        if (isValidIndex(index, projects)) {
            System.out.print("Enter task description: ");
            String task = scanner.nextLine();
            projects.get(index).addTask(task);
            System.out.println("📝 Task added!");
        }
    }

    static void listProjects() {
        if (projects.isEmpty()) {
            System.out.println("No projects found 🚫");
        } else {
            System.out.println("📁 Projects:");
            for (int i = 0; i < projects.size(); i++) {
                System.out.println((i + 1) + ". " + projects.get(i).name);
            }
        }
    }

    static void viewProjectTasks() {
        listProjects();
        System.out.print("Choose project number: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (isValidIndex(index, projects)) {
            projects.get(index).listTasks();
        }
    }

    static void markTaskDone() {
        listProjects();
        System.out.print("Choose project number: ");
        int pIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        if (isValidIndex(pIndex, projects)) {
            projects.get(pIndex).listTasks();
            System.out.print("Choose task number to mark as done: ");
            int tIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            projects.get(pIndex).markTaskDone(tIndex);
            System.out.println("✅ Task marked as done!");
        }
    }

    static boolean isValidIndex(int index, List<?> list) {
        if (index < 0 || index >= list.size()) {
            System.out.println("Invalid selection ❗");
            return false;
        }
        return true;
    }
}
