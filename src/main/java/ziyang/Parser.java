package ziyang;

import java.util.LinkedList;
import java.util.Scanner;

public class Parser {

    public String nextinput;
    public Scanner sc = new Scanner(System.in);
    public LinkedList<Task> items;

    public Parser(LinkedList<Task> items) {
        this.items = items;
    }

    public void getInput() {
        this.nextinput = this.sc.nextLine();
    }

    public boolean isEnd() {
        return this.nextinput.equals("bye");
    }

    public String getResponse(String input) {
        if (input.equals("list") || input.equals("l")) {
            String msg = "Here are the tasks in your list:\n";
            int i = 0;
            for (Task item : items) {
                msg += i + "." + item.toString() + "\n";
                i++;
            }
            return msg;
        } else if (input.startsWith("mark") || input.startsWith("m ")) {
            String j = input.split(" ")[1];
            items.get(Integer.parseInt(j)).mark();
            return "Nice! I've marked this task as done:\n " + items.get(Integer.parseInt(j)).toString();
        } else if (input.startsWith("delete") || input.startsWith("d ")) {
            String j = input.split(" ")[1];
            items.remove(Integer.parseInt(j));
            return "Nice! I've marked this task as deleted";
        } else if (input.startsWith("todo") || input.startsWith("t ")) {
            String j = input.split(" ", 2)[1];
            items.add(new todoTask(j));
            return "added: " + j;
        } else if (input.startsWith("deadline") || input.startsWith("d ")) {
            String j = input.split("/")[0].split(" ", 2)[1];
            String deadline = input.split("/")[1].split(" ", 2)[1];
            items.add(new deadlineTask(j, deadline));
            return "added: " + j;
        } else if (input.startsWith("event") || input.startsWith("e ")) {
            String j = input.split("/")[0].split(" ", 2)[1];
            String start = input.split("/")[1].split(" ", 2)[1];
            String end = input.split("/")[2].split(" ", 2)[1];
            items.add(new eventTask(j, start, end));
            return "added: " + j;
        } else {
            return "I don't understand what you want to do. Please try again.";
        }
    }

    public String evaluate() {
      return this.getResponse(this.nextinput);
    }
}
