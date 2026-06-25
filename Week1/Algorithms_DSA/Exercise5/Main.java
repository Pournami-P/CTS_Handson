//Exercise 5: Task Management System
class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }
}

class TaskList {
    Task head = null;

    void add(Task t) {
        t.next = head;
        head = t;
    }

    Task search(int id) {
        Task curr = head;

        while (curr != null) {
            if (curr.taskId == id)
                return curr;
            curr = curr.next;
        }

        return null;
    }

    void traverse() {
        Task curr = head;

        while (curr != null) {
            System.out.println(curr.taskId + " " +
                    curr.taskName + " " +
                    curr.status);
            curr = curr.next;
        }
    }

    void delete(int id) {

        if (head == null) {
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            return;
        }

        Task curr = head;

        while (curr.next != null) {
            if (curr.next.taskId == id) {
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
    }
}

public class Main {

    public static void main(String[] args) {

        TaskList list = new TaskList();

        list.add(new Task(101, "Coding", "Pending"));
        list.add(new Task(102, "Testing", "Completed"));
        list.add(new Task(103, "Documentation", "Pending"));
        list.add(new Task(104, "Deployment", "In Progress"));

        System.out.println("Tasks:");
        list.traverse();

        Task t = list.search(102);

        if (t != null)
            System.out.println("\nTask Found: " + t.taskName);
        else
            System.out.println("\nTask Not Found");

        System.out.println("\nDeleting Task 103...");
        list.delete(103);

        System.out.println("\nTasks after deletion:");
        list.traverse();
    }
}