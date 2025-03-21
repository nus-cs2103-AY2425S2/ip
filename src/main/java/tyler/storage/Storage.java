package tyler.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import tyler.task.Deadline;
import tyler.task.Event;
import tyler.task.ToDo;
import tyler.task.list.TaskList;

public class Storage {
    private final String home = System.getProperty("user.dir");
    private final Path DEFAULT_PATH = Paths.get(home, "data", "tyler.txt");
    private final Path filePath;

    public Storage() {
        this.filePath = DEFAULT_PATH;
    }

    public Storage(String filePath) {
        this.filePath = Paths.get(home, filePath);
    }

    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        if (Files.notExists(this.filePath)) {
            Files.createDirectories(this.filePath.getParent());
            Files.createFile(this.filePath);
        }

        List<String> stored = Files.readAllLines(this.filePath);
        for (String item: stored) {
            List<String> itemTokens = Arrays.asList(item.split("\\|"));
            itemTokens.replaceAll(String::strip);
            boolean isNewAdded = false;
            if (itemTokens.get(0).equals("T") && itemTokens.size() == 3) {
                tasks.add(new ToDo(itemTokens.get(2)));
                isNewAdded = true;
            } else if (itemTokens.get(0).equals("D") && itemTokens.size() == 4) {
                tasks.add(new Deadline(itemTokens.get(2), LocalDateTime.parse(itemTokens.get(3))));
                isNewAdded = true;
            } else if (itemTokens.get(0).equals("E") && itemTokens.size() == 5) {
                tasks.add(new Event(itemTokens.get(2),
                        LocalDateTime.parse(itemTokens.get(3)), LocalDateTime.parse(itemTokens.get(4))));
                isNewAdded = true;
            }
            if (isNewAdded && itemTokens.get(1).equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        return tasks;
    }

    public void save(List<String> formattedTasks) throws IOException {
        Files.write(this.filePath, formattedTasks);
    }

}
