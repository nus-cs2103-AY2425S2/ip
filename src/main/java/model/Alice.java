package model;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import model.command.Command;
import model.command.Parser;
import model.exception.AliceException;
import model.exception.AliceExit;
import model.exception.StorageIOException;
import model.response.Response;
import model.response.Response.RESPONSE_TYPE;
import model.storage.Storage;
import model.task.TaskList;

/**
 * The main class for the Alice application.
 */
public class Alice {

    private TaskList tasks;
    private final Storage storage;
    //A blocking queue that enables asynchronous listening for responses
    private final BlockingQueue<Response> responseBuffer;
    private final Random random = new Random(System.currentTimeMillis());

    public static enum ANGER_LEVEL {
        ANNOYED, ANGRY, ENRAGED
    }

    private static final double ANGER_CHANCE = 0.3;
    private ANGER_LEVEL angerLevel = ANGER_LEVEL.ANNOYED;

    /**
     * Constructs an Alice object and initializes the task list and storage.
     */
    public Alice() {
        this.tasks = new TaskList();
        this.storage = new Storage();
        this.responseBuffer = new LinkedBlockingQueue<>();
        startInsultThread();
    }

    /**
     * Starts a thread that generates insults at random intervals based on
     * Alice's anger level. This is a daemon thread that will run in the
     * background and will not block on shutdown.
     */
    private void startInsultThread() {
        Thread insultThread = new Thread(() -> {
            while (true) {
                try {
                    long interval = getInsultInterval();
                    TimeUnit.MILLISECONDS.sleep(interval);
                    responseBuffer.add(new Response(RESPONSE_TYPE.GENERIC_INSULT));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        insultThread.setDaemon(true);
        insultThread.start();
    }

    private long getInsultInterval() {
        return switch (angerLevel) {
            case ANNOYED ->
                TimeUnit.MINUTES.toMillis(2);
            case ANGRY ->
                TimeUnit.MINUTES.toMillis(1);
            case ENRAGED ->
                TimeUnit.SECONDS.toMillis(30);
        };
    }

    /**
     * Initializes the task list with the tasks stored in the storage.
     *
     * @throws StorageIOException If an error occurs during initialization.
     */
    public void initialize() {
        responseBuffer.clear();
        responseBuffer.add(new Response(RESPONSE_TYPE.INTRO));
        try {
            this.tasks = storage.loadTasks();
        } catch (StorageIOException e) {
            responseBuffer.add(new Response(RESPONSE_TYPE.ERROR, e.getMessage()));
        }
    }

    /**
     * Parse and execute the input string.
     *
     * @param input The input string.
     * @throws AliceExit If the application should exit.
     * @throws AliceException If an error occurs during parsing and/or execution
     */
    public void run(String input) throws AliceExit {
        try {
            Command command = Parser.parseCommand(input);
            Response response = command.execute(tasks, storage);
            responseBuffer.add(response);
            if (random.nextDouble() < ANGER_CHANCE) {
                increaseAngerLevel();
            }
        } catch (AliceExit e) {
            throw e;
        } catch (AliceException e) {
            responseBuffer.add(new Response(RESPONSE_TYPE.ERROR, e.getMessage()));
        }
    }

    private void increaseAngerLevel() {
        switch (this.angerLevel) {
            case ANNOYED ->
                angerLevel = ANGER_LEVEL.ANGRY;
            case ANGRY ->
                angerLevel = ANGER_LEVEL.ENRAGED;
            case ENRAGED -> {
            }
        }
    }

    public String getImageUrl() {
        return switch (this.angerLevel) {
            case ANNOYED ->
                "images/alice_annoyed.png";
            case ANGRY ->
                "images/alice_angry.png";
            case ENRAGED ->
                "images/alice_enraged.png";
        };
    }

    /**
     * Takes a response from the response buffer.
     *
     * @return The response taken from the buffer.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public Response takeResponse() throws InterruptedException {
        Response response;
        synchronized (responseBuffer) {
            response = responseBuffer.take();
            responseBuffer.notify();
        }
        return response;
    }

    public void quit() {
        responseBuffer.add(new Response(RESPONSE_TYPE.GOODBYE));
    }

    public ANGER_LEVEL getAngerLevel() {
        return angerLevel;
    }

}
