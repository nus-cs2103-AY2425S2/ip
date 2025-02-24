package model.response;

import utils.ArrayUtils;

/**
 * Represents a response from Alice.
 */
public class Response {

    public static enum RESPONSE_TYPE {
        INTRO, TASK_ADDED, TASK_DELETED, TASK_MARKED, TASK_UNMARKED, LIST_TASKS, TASKS_FOUND, GOODBYE, ERROR, 
        GENERIC_INSULT
    }

    private final String[] messages;

    /**
     * Default constructor for a response. Takes a response type enum as the
     * first argument, and a vararg of verbatim messages to be appended to the
     * response. The enum type is used to generate the first message in the
     * response.
     *
     * @param type The type of the response.
     * @param messages The messages to be appended to the response.
     */
    public Response(RESPONSE_TYPE type, String... messages) {
        String firstMessage = switch(type) {
            case INTRO ->
                MessageGenerator.getIntroMessage();
            case TASK_ADDED ->
                MessageGenerator.getTaskAddedMessage();
            case TASK_DELETED ->
                MessageGenerator.getTaskDeletedMessage();
            case TASK_MARKED ->
                MessageGenerator.getTaskMarkedMessage();
            case TASK_UNMARKED ->
                MessageGenerator.getTaskUnmarkedMessage();
            case LIST_TASKS ->
                MessageGenerator.getListTasksMessage();
            case TASKS_FOUND ->
                MessageGenerator.getTasksFoundMessage();
            case GOODBYE ->
                MessageGenerator.getGoodbyeMessage();
            case ERROR ->
                MessageGenerator.getErrorMessgae();
            case GENERIC_INSULT -> MessageGenerator.getGenericInsult();
        };
        messages = ArrayUtils.prepend(messages, firstMessage);
        this.messages = ArrayUtils.map(messages, msg -> {
            return msg.length() > 0 ? msg : MessageGenerator.getEmptyMessage();
        });
    }

    public Response(String... messages) {
        this.messages = messages;
    }

    public String[] getMessages() {
        return messages;
    }

}
