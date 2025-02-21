package grass;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
* handles storing tasks in text file
* 
* @author gn07
* 
*/
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * <p>loads tasks from saved text file</p>
     * @return tasks in the text file as ArrayList<Task>
     * @since 1.0
     */
    public ArrayList<Task> loadFromTxt() throws GrassException {
        ArrayList<Task> inputList = new ArrayList<Task>();

        try {
            File f = new File(this.filePath);
            assert f != null :"File could not open.";
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] lineArray = line.split(" \\| ");

                assert lineArray.length > 1 : "Invalid file format.";
                assert lineArray[0].equals("T") || lineArray[0].equals("D") || lineArray[0].equals("E") : "Invalid task type.";

                if (lineArray[0].equals("T")) {
                    inputList.add(new Todo(lineArray[2]));
                    if (lineArray[1].equals("1")) {
                        inputList.get(inputList.size() - 1).markAsDone();
                    }
                }
                else if (lineArray[0].equals("D")) {
                    inputList.add(new Deadline(lineArray[2], lineArray[3]));
                    if (lineArray[1].equals("1")) {
                        inputList.get(inputList.size() - 1).markAsDone();
                    }
                }
                else if (lineArray[0].equals("E")) {
                    inputList.add(new Event(lineArray[2], lineArray[3], lineArray[4]));
                    if (lineArray[1].equals("1")) {
                        inputList.get(inputList.size() - 1).markAsDone();
                    }
                }
            }
            return inputList;
        }
        catch (FileNotFoundException e) {
            throw new GrassException("File not found.");
        }
    
    }

    /**
     * <p>writes tasks to designated save text file</p>
     * @param inputList task list to be saved
     * @since 1.0
     */
    public void writeToTxt(ArrayList<Task> inputList) throws GrassException{
        try {
            File f = new File(this.filePath);
            FileWriter fw = new FileWriter(f);
            for (Task t : inputList) {
                if (t instanceof Todo) {
                    fw.write("T | " + (t.isDone ? "1" : "0") + " | " + t.description + "\n");
                }
                else if (t instanceof Deadline) {
                    fw.write("D | " + (t.isDone ? "1" : "0") + " | " + t.description + " | " + ((Deadline) t).by + "\n");
                }
                else if (t instanceof Event) {
                    fw.write("E | " + (t.isDone ? "1" : "0") + " | " + t.description + " | " + ((Event) t).from + " | " + ((Event) t).to + "\n");
                }
            }
            fw.close();
        }
        catch (IOException e) {
            throw new GrassException("A storage error occurred.");
        }
        return;
    }
}