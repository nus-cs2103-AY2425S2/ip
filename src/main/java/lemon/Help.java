package lemon;

import java.util.List;
import java.util.Random;

public class Help {
    public static String getHelpPage() {
        StringBuilder helpPage = new StringBuilder();
        helpPage.append("I'm here! What kind of help do you need?").append("\n");
        helpPage.append("- Technical information: type 'technical'").append("\n");
        helpPage.append("- Emotional support: type 'emotional'").append("\n");
        return helpPage.toString();
    }

    public static String getTechHelp() {
        StringBuilder techHelpPage = new StringBuilder();
        techHelpPage.append("To use the chatbot:").append("\n");
        techHelpPage.append("__________________________________________").append("\n");
        techHelpPage.append("1. Add tasks in the following format:").append("\n");
        techHelpPage.append(" *todo {task description}  ").append("\n");
        techHelpPage.append(" eg.todo math assignment").append("\n");
        techHelpPage.append("\n");
        techHelpPage.append(" *deadline {task description} /by {deadline in YYYY-MM-DD} ").append("\n");
        techHelpPage.append(" eg.deadline math assignment /by 22-02-2025").append("\n");
        techHelpPage.append("\n");
        techHelpPage.append(" *event {task description} /from {time} /to {time}").append("\n");
        techHelpPage.append(" eg.event career fair /from 18 feb /to 20 fed").append("\n");
        techHelpPage.append("\n");
        techHelpPage.append("2. Mark/Unmark/Delete tasks in the following format:").append("\n");
        techHelpPage.append(" *mark/unmark/delete {task index} ").append("\n");
        techHelpPage.append(" eg.delete 3").append("\n");
        techHelpPage.append("\n");
        techHelpPage.append("3. type 'list' to list all the tasks").append("\n");
        techHelpPage.append("\n");
        techHelpPage.append("4. type 'bye' to exit the chatbot").append("\n");
        return techHelpPage.toString();
    }

    public static String getEmoHelp() {
        List<String> encouragingMessages = List.of("You have done a great job! Remember to have a good rest before moving on! \uD83C\uDF89",
                "You can do this! You are stronger than you think! \uD83D\uDCAA",
                "Don't give up! You are almost there! \uD83C\uDFC6",
                "In all the shabby fading, please shine forever \uD83C\uDF1F",
                "live laugh love ❤️");
        Random random = new Random();
        return encouragingMessages.get(random.nextInt(encouragingMessages.size()));
    }

}
