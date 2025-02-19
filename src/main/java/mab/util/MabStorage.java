package mab.util;

import java.io.File;
import java.io.FileWriter;
import java.time.format.DateTimeParseException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import mab.task.Task;
import mab.task.ToDos;
import mab.task.Deadlines;
import mab.task.Events;
import mab.MabException;

/**
 * Handles persistent storage of tasks in a file system.
 * Maintains task data in: {@value #FILE_PATH}
 */
public class MabStorage{
    private static final String FILE_PATH = "./data/mab_tasks.txt";
    private File f;

    /**
     * Initializes storage infrastructure, creating required directories/files.
     */
    public MabStorage(){
        f = new File(FILE_PATH);
        try{
            //create directory if it dosen't exist, create file if it dosen't exist
            if(!f.getParentFile().exists()){
                f.getParentFile().mkdirs();
            }
            if (!f.exists()){
                f.createNewFile();
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (java.io.IOException e){
            System.out.println("Error writing to file");
        }
    }

     /**
     * Persists current task state to storage file.
     * 
     * @param tasks List of tasks to save
     */
    public void write(ArrayList<Task> tasks){
        //write to file
        try{
            FileWriter fw = new FileWriter(f, false);
            for (Task t: tasks ) {
                String saveString = String.format("%s\n", t.getSaveString());
                fw.write(saveString);
            }
            fw.close();
        } catch (java.io.IOException e){
            System.out.println("Error writing to file");
        }
    }


    /**
     * Loads tasks from persistent storage.
     * 
     * @return List of parsed tasks, empty list if no storage file exists
     * 
     * @implSpec File format expectations:
     * <pre>T|status|description  // Todo
     * D|status|description|date  // Deadline
     * E|status|description|from|to  // Event</pre>
     */
    public ArrayList<Task> read(){
        //file does not exist
        if (!f.exists()){
            return new ArrayList<Task>();
        }

        //if file does exists
        ArrayList<Task> tasks = new ArrayList<Task>();
        try{
            Scanner s = new Scanner(f);

            while (s.hasNext()){
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                String status = parts[1];
                String description = parts[2];
                String date;

                switch (type){ 
                    case ("T"):
                        tasks.add(new ToDos(description, Boolean.parseBoolean(status)));
                        break;
                    case ("D"):
                        date = parts[3].trim();
                        tasks.add(new Deadlines(description, Boolean.parseBoolean(status), date));
                        break;
                    case("E"):
                        date = parts[3].trim();
                        String dateTo = parts[4].trim();
                        tasks.add(new Events(description, Boolean.parseBoolean(status), date, dateTo));
                        break;
                }
            }

            s.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch(ArrayIndexOutOfBoundsException e){ 
            System.out.println("Error reading file, file may be corrupted");
            return new ArrayList<Task>();
        } catch (DateTimeParseException e){
            System.out.println("Error reading file, date parsing error");
            return new ArrayList<Task>();
        } catch (MabException e){
            System.out.println(e.getMessage());
            return new ArrayList<Task>();
        }
        
        return tasks;
    }

}
