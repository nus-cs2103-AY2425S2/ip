package universe;

import java.time.LocalDateTime;
import java.util.ArrayList;

import universe.chores.Chore;

/**
 * Represents a list of Chores to be completed.
 *
 * @author yuqing-tham
 */
public class Checklist {
    private static ArrayList<Chore> chores = new ArrayList<>(100);

    /**
     * Returns the size of the checklist chores.
     */
    public int getSize() {
        return chores.size();
    }

    /**
     * Returns the checklist chores.
     */
    public static ArrayList<Chore> getChores() {
        return chores;
    }

    /**
     * Adds a chore to the checklist.
     *
     * @param chore The Chore to be added to the list.
     */
    public void addChore(Chore chore) {
        chores.add(chore);
        System.out.println(chore.toString() + " added to Checklist!");
        this.printChoreCount();
    }

    /**
     * Removes a chore from the checklist.
     *
     * @param choreNumber The number of the Chore to be removed.
     */
    public void removeChore(int choreNumber) {
        int index = choreNumber - 1;
        assert index >= 0;
        Chore chore = chores.get(index);
        chores.remove(index);
        System.out.println("Got it, this chore is removed: \n" + chore.toString());
        this.printChoreCount();
    }

    /**
     * Prints the number of chores in the checklist.
     */
    public void printChoreCount() {
        int choreCount = chores.size();
        System.out.println("You now have " + choreCount + " chore(s) in the checklist.\n");
    }

    /**
     * Prints the contents of the checklist when user inputs the "list" command.
     */
    public void printChecklist() {
        int j = 1; // a counter to serve as index for the list
        for (Chore c : chores) { // for every element in the checklist
            System.out.printf("%d %s%n", j, c.toString());
            j++;
        }
        this.printChoreCount();
    }

    /**
     * Locates the corresponding chore in the checklist and asks the Chore to mark itself as done.
     *
     * @param choreNumber ChoreNumber - 1 is the corresponding index in the list chores.
     */
    public void checkAsDone(int choreNumber) {
        int index = choreNumber - 1;
        assert index >= 0;
        Chore chore = chores.get(index);
        chore.markAsDone();
    }

    /**
     * Locates the corresponding chore in the checklist and asks the Chore to mark itself as not done.
     *
     * @param choreNumber ChoreNumber - 1 is the corresponding index in the list chores.
     */
    public void uncheckAsDone(int choreNumber) {
        int index = choreNumber - 1;
        assert index >= 0;
        Chore chore = chores.get(index);
        chore.markAsNotDone();
    }

    /**
     * Filters the chores in the checklist by date.
     *
     * @param date The date to filter in String form.
     * @param formattedDate The date to filter in LocalDateTime form.
     */
    public void filterByDate(String date, LocalDateTime formattedDate) {
        System.out.println("Chores on " + date + ": ");
        boolean isFound = false;

        for (Chore c : chores) {
            if (c.isChoreWithTime() && c.getDateTime().toLocalDate().equals(formattedDate.toLocalDate())) {
                isFound = true;
                System.out.println(c.toString());
            }
        }

        if (!isFound) {
            System.out.println("No deadlines or events on this date\n");
        }
    }

    /**
     * Finds the chores in the checklist that contains the keyword.
     *
     * @param keyword Keyword to be checked against the chore descriptions.
     */
    public void findByKeyword(String keyword) {
        System.out.println("The matching Chores in the Checklist are:");

        boolean isFound = false;
        for (Chore c : chores) {
            if (c.getChoreDescription().contains(keyword)) {
                isFound = true;
                System.out.println(c.toString());
            }
        }

        if (!isFound) {
            System.out.println("No Chores matching this description\n");
        }
    }

    /**
     * Removes all the chores from the checklist.
     */
    public void clearAllChores() {
        if (chores.isEmpty()) {
            System.out.println("Checklist is originally empty!");
        } else {
            chores.clear();
            System.out.println("All chores removed successfully from the Checklist!");
            this.printChoreCount();
        }
    }
}
