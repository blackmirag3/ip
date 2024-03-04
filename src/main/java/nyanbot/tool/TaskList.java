package nyanbot.tool;

import java.util.ArrayList;
import nyanbot.task.Task;
import nyanbot.task.Todo;
import nyanbot.task.Deadline;
import nyanbot.task.Event;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        this.tasks.add(task);
        UI.printAddTaskSuccess(task);
    }

    public void importTask(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> exportTask() {
        return this.tasks;
    }

    public void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.remove(index);
            UI.printDeleteSuccess();
            UI.printTasks(this.tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            UI.printNullError();
        } catch (NumberFormatException e) {
            UI.printDeleteUsage();
        }
    }

    public void markTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.get(index).markAsDone();
            UI.printMarkSuccess();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            UI.printNullError();
        } catch (NumberFormatException e) {
            UI.printMarkUsage();
        }
    }

    public void unmarkTask(String input) {
        try {
            int index = Integer.parseInt(input) - 1;
            this.tasks.get(index).markAsUndone();
            UI.printUnmarkSuccess();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            UI.printNullError();
        } catch (NumberFormatException e) {
            UI.printUnmarkUsage();
        }
    }

    public void addTodo(String description) {
        try {
            Todo todo = new Todo(description);
            this.addTask(todo);
        } catch (StringIndexOutOfBoundsException e) {
            UI.printMissingDescription();
            UI.printTodoUsage();
        }
    }

    public void addDeadline(String input) {
        try {
            String[] splitInputs = input.split("//", 2);
            String description = splitInputs[0];
            String date = splitInputs[1];
            Deadline deadline = new Deadline(description, date);
            addTask(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            UI.printMissingDescription();
            UI.printDeadlineUsage();
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.printMissingDate();
            UI.printDeadlineUsage();
        }
    }

    public void addEvent(String input) {
        try {
            String[] splitInputs = input.split("//", 3);
            String description = splitInputs[0];
            String start = splitInputs[1];
            String end = splitInputs[2];
            Event event = new Event(description, start, end);
            addTask(event);
        } catch (StringIndexOutOfBoundsException e) {
            UI.printMissingDescription();
            UI.printEventUsage();
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.printMissingStartEnd();
            UI.printEventUsage();
        }
    }
}
