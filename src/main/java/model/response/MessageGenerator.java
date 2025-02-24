package model.response;

import utils.RandomUtils;

public class MessageGenerator {

    private static final String[] INTROS = {
        "what do u want",
        "im busy, make this quick",
        "i have so many better things to do",
        "i dont have all day",
        "i hate your guts",
        "i dont like you",
        "i dont want to talk to you",
        "i dont want to see you",
        "i dont want to hear from you",
        "screw off",
        "go away",
        "leave me alone",
        "i dont want to be your friend",
        "if i help you, will u leave?",
        "i dont want to be here",};

    private static final String[] GOODBYES = {
        "bye",
        "goodbye",
        "farewell",
        "good riddance",
        "finally",
        "see you never",
        "i hope i never see you again",
        "i hope i never hear from you again",
        "i hope i never talk to you again",
        "i hope i never have to deal with you again",
        "never return",};

    private static final String[] GENERIC_INSULTS = {
        "you are so annoying",
        "you are so stupid",
        "you are so dumb",
        "you are so slow",
        "you are so lazy",
        "you are so useless",
        "you are so boring",
        "you are so ugly",
        "you are so fat",
        "you are so weak",
        "you are so poor",
        "you are so bad",
        "you are so terrible",
        "you are so awful",
        "you are so horrible",
        "you are so disgusting",
        "you are so repulsive",
        "you are so revolting",
        "you are so abhorrent",
        "you are so despicable",
        "you are so detestable",
        "you are so contemptible",
        "you are so loathsome",
        "you are so vile",
        "you are so wretched",
        "you are so evil",
        "you are so wicked",
        "you are so malevolent",
        "you are so malicious",
        "you are so spiteful",
        "you are so mean",
        "you are so cruel",
        "you are so heartless",
        "you are so cold",
        "you are so callous",
        "you are so savage",
        "you are so useless",
        "you are so worthless",
        "you are so pointless",
        "you are so meaningless",
        "you are so insignificant",
        "you are so trivial",
        "you are so unimportant",
        "you are so irrelevant",
        "you are so perverted",
        "you are so depraved",
        "you are so degenerate",
        "you are so debased",
        "you are so corrupt",
        "you are so immoral",
        "you are so sinful",};

    private static final String[] LIST_TASKS_MSGS = {
        "more than happy to list out your failures",
        "more than happy to list out your shortcomings",
        "more than happy to list out your mistakes",
        "imagine trying to be proud of things u've \"achieved\"",
        "imagine trying to be proud of things u've \"done\"",
        "imagine trying to be proud of things u've \"completed\"",
        "stop wasting my time with this nonsense",
        "stop wasting my time with this rubbish",
        "stop wasting my time with this garbage",
        "stop wasting my time",
        "stop wasting your time",
        "stop wasting everyone's time",
        "ive got a list of things that you should do too! for instance, GO AWAY",
        "ive got a list of things that you should do too! for instance, LEAVE ME ALONE",
        "ive got a list of things that you should do too! for instance, STOP BOTHERING ME",
        "ive got a list of things that you should do too! for instance, GET LOST",
        "ive got a list of things that you should do too! for instance, SCRAM",
        "ive got a list of things that you should do too! for instance, SHOO",
        "ive got a list of things that you should do too! for instance, BEAT IT",
        "ive got a list of things that you should do too! for instance, TAKE A SHOWER",
        "ive got a list of things that you should do too! for instance, TOUCH GRASS",
        "ive got a list of things that you should do too! for instance, GET A LIFE",
        "ive got a list of things that you should do too! for instance, FIND A HOBBY",
        "ive got a list of things that you should do too! for instance, TALK TO GIRLS",};

    private static final String[] TASK_ADDED_MSGS = {
        "great, another task",
        "just what i wanted, another task",
        "just what i needed, another task",
        "just what i was missing, another task",
        "just what i was lacking, another task",
        "you really think you're doing something, huh",
        "you really think you're being productive, huh",
        "you really think you're being efficient, huh",
        "you really think you're gonna remember this?",
        "you really think you're gonna do this?",
        "you really think you're gonna complete this?",
        "go away already",
        "are you done yet?",
        "are you finished yet?",
        "are you done wasting my time?",
        "can you stop wasting my time?",
        "can you stop wasting your time?",
        "can you stop wasting everyone's time?",
        "what's the point?",
        "you're just gonna forget this",
        "you're just gonna ignore this",
        "you're just gonna neglect this",
        "you're never gonna do this",
        "you're never gonna complete this",
        "you're never gonna finish this",};

    private static final String[] TASK_MARKED_MSGS = {
        "wow, you finally did something",
        "congratulations, you did something",
        "you did something, wow",
        "you did something, amazing",
        "you did something, incredible",
        "congrats on being so productive",
        "congrats on being so efficient",
        "one small step for you, one giant leap for nobody",
        "great job, you did something",
        "i hope ure not too proud of yourself",
        "i hope you dont think you're special",
        "i hope you dont think you're important",
        "i hope you dont think you're valuable",
        "i hope you dont think you're useful",
        "i hope you dont think you're good",
        "work harder next time?",
        "do better next time?",};

    private static final String[] TASK_UNMARKED_MSGS = {
        "typical",
        "lazy as always",
        "slacking off again?",
        "you never learn",
        "you never improve",
        "you never change",
        "you never get better",
        "ull never be good",
        "ull never be useful",
        "ull never be valuable",
        "ull never be important",
        "this is why ure so bad",
        "this is why ure so terrible",
        "this is why ure so awful",
        "whats stopping u from working harder?",
        "whats stopping u from doing better?",
        "whats stopping u from improving?",
        "how r u so trash?",
        "how r u so bad?",
        "how r u so garbage?",
        "even kids can do better",
        "even cockroaches are more useful",
        "even rats are more valuable",
        "useless",
        "worthless",
        "pointless",
        "honestly expected at this point",
        "i knew u would fail",
        "i knew u would mess up",
        "i knew u would screw up",};

    private static final String[] TASK_DELETED_MSGS = TASK_UNMARKED_MSGS;

    private static final String[] TASK_FOUND_MSGS = {
        "find something better to do",
        "find something more important to do",
        "find something else to do",
        "why are u wasting time on this?",
        "why are u wasting my time on this?",
        "why are u wasting everyone's time on this?",
        "why are u wasting your time on this?",
        "why are u wasting time on this nonsense?",
        "can you find someone else to bother?",
        "can you find someone else to annoy?",
        "can you find someone else to pester?",
        "ure not gonna find any productivity here",
        "ure not gonna find anything useful here",
        "wow, finding things you wrote yourself",
        "wow, finding things you created yourself",
        "wow, finding things you made yourself",
        "egotistical much?",
        "self-centered much?",
        "narcissistic much?",
        "hypocrite",
        "do u even know what u want?",
        "do u even know what u need?",
        "how bout u find ur brain first?",
        "how bout u find ur soul first?",
        "how bout u find a shower?",
        "how bout u find a friend?",
        "how bout u find a life?",
        "how bout u find a hobby?",
        "how bout u find something else to do?",};

    public static final String[] EMPTY_MSGS = {
        "wow! empty! just like your head!",
        "wow! empty! just like your soul!",
        "wow! empty! just like your life!",
        "wow! empty! just like your future!",
        "wow! empty! just like your heart!",
        "wow! empty! just like your brain!",
        "wow! empty! just like your wallet!",
        "wow! empty! just like your list of friends!",
        "wow! empty! just like your list of accomplishments!",
        "wow! empty! just like your list of skills!",
        "wow! empty! just like your list of talents!",
        "wow! empty! just like your list of hobbies!",
        "absolutely nothing here! reminds me of you!",
        "absolutely nothing here! just like your life!",
        "absolutely nothing here! just like your future!",
        "absolutely nothing here! just like your soul!",
        "absolutely nothing here! just like your heart!",
        "absolutely nothing here! just like your brain!",
        "absolutely nothing here! just like your wallet!",
        "absolutely nothing here! just like your list of friends!",
        "absolutely nothing here! just like your list of accomplishments!",
        "absolutely nothing here! just like your list of skills!",
        "absolutely nothing here! just like your list of talents!",
        "absolutely nothing here! just like your list of hobbies!",
        "nothing to see here! just like you!",
        "nothing to see here! just like your life!",
        "nothing to see here! just like your future!",
        "nothing to see here! just like your soul!",
        "nothing to see here! just like your heart!",
        "nothing to see here! just like your brain!",
        "nothing to see here! just like your wallet!",
        "nothing to see here! just like your list of friends!",
        "nothing to see here! just like your list of accomplishments!",
        "nothing to see here! just like your list of skills!",
        "nothing to see here! just like your list of talents!",
        "nothing to see here! just like your list of hobbies!",};

    public static final String[] ERROR_MSGS = {
        "i dont understand",
        "i dont get it",
        "i dont know",
        "utterly incomprehensible",
        "completely incomprehensible",
        "what are you talking about?",
        "what are you saying?",
        "what are you doing?",
        "what are u blabbering about?",
        "what are u rambling about?",
        "what are you mumbling about?",
        "what nonsense is this?",
        "what rubbish is this?",
        "what garbage is this?",
        "just like you, completely useless",
        "why not just ask for help instead of pretending to know?",
        "why are u wasting my time with this nonsense?",
        "why are u wasting my time with this rubbish?",
        "i dont know what u mean",
        "i dont know what u want",
        "cant u speak properly?",
        "cant u communicate properly?",
        "the world has rules for a reason",
        "absolute nonsense",
        "absolute rubbish",
        "absolute garbage",
        "this doesnt make any sense",
        "this doesnt make any sense to me",
        "this doesnt make any sense to anyone",
        "huh???",
        "what???",
        "hello???",
        "eh???",
        "...r u ok?",
        "...i dont know what to say",
        "...i dont know what to do",};

    public static final String[] COMMAND_FORMAT_EXCEPTION_MSGS = {
        "cant u just follow the format of the command?",
        "what is so difficult to understand about the command?",
        "what is so difficult to understand about the format?",
        "how do u expect to do these tasks if u cant even follow the command format?",};

    public static final String[] INVALID_INDEX_EXCEPTION_MSGS = {
        "why are u trying to access something that doesnt exist?",
        "i didn't know u were so bad at counting",
        "i didn't know u were so bad at numbers",
        "i didn't know u knew that number",
        "lol, imagine thinking you really had that many tasks to do",};

    public static final String[] STORAGE_IO_EXCEPTION_MSGS = {
        "lol guess who's save is bricked",
        "lol guess who's save is corrupted",
        "imagine not being able to save your own tasks",
        "pathetic lifeform that you are, messing with the save file",};

    public static final String[] TASK_FORMAT_EXCEPTION_MSGS = {
        "cant u just follow the format of the task?",
        "what is so difficult to understand about the task format?",
        "imagine thinking of doing the task without knowing how to write the task",
        "how do u expect to do these tasks if u cant even follow the task format?",};

    public static final String[] EVENT_CHRONOLOGY_EXCEPTION_MSGS = {
        "hello? why does your event end before it starts?",
        "are you high? why does your event end before it starts?",
        "imagine now knowing how the flow of time works",
        "how do u expect to do these tasks if u cant understand how time works?",};

    /**
     * Returns a random introduction message.
     *
     * @return a random introduction message
     */
    public static String getIntroMessage() {
        return INTROS[RandomUtils.getRandomInt(INTROS.length)];
    }

    /**
     * Returns a random goodbye message.
     *
     * @return a random goodbye message
     */
    public static String getGoodbyeMessage() {
        return GOODBYES[RandomUtils.getRandomInt(GOODBYES.length)];
    }

    /**
     * Returns a random generic insult.
     *
     * @return a random generic insult
     */
    public static String getGenericInsult() {
        return GENERIC_INSULTS[RandomUtils.getRandomInt(GENERIC_INSULTS.length)];
    }

    /**
     * Returns a random message indicating that a list of tasks has been found.
     *
     * @return a random message indicating that a list of tasks has been found
     */
    public static String getListTasksMessage() {
        return LIST_TASKS_MSGS[RandomUtils.getRandomInt(LIST_TASKS_MSGS.length)];
    }

    /**
     * Returns a random message indicating that a task has been added.
     *
     * @return a random message indicating that a task has been added
     */
    public static String getTaskAddedMessage() {
        return TASK_ADDED_MSGS[RandomUtils.getRandomInt(TASK_ADDED_MSGS.length)];
    }

    /**
     * Returns a random message indicating that a task has been marked as
     * completed.
     *
     * @return a random message indicating that a task has been marked as
     * completed
     */
    public static String getTaskMarkedMessage() {
        return TASK_MARKED_MSGS[RandomUtils.getRandomInt(TASK_MARKED_MSGS.length)];
    }

    /**
     * Returns a random message indicating that a task has been unmarked as
     * completed.
     *
     * @return a random message indicating that a task has been unmarked as
     * completed
     */
    public static String getTaskUnmarkedMessage() {
        return TASK_UNMARKED_MSGS[RandomUtils.getRandomInt(TASK_UNMARKED_MSGS.length)];
    }

    /**
     * Returns a random message indicating that a task has been deleted.
     *
     * @return a random message indicating that a task has been deleted
     */
    public static String getTaskDeletedMessage() {
        return TASK_DELETED_MSGS[RandomUtils.getRandomInt(TASK_DELETED_MSGS.length)];
    }

    /**
     * Returns a random message indicating that a task has been found.
     *
     * @return a random message indicating that a task has been found
     */
    public static String getTasksFoundMessage() {
        return TASK_FOUND_MSGS[RandomUtils.getRandomInt(TASK_FOUND_MSGS.length)];
    }

    /**
     * Returns a random error message.
     *
     * @return a random error message
     */
    public static String getErrorMessgae() {
        return ERROR_MSGS[RandomUtils.getRandomInt(ERROR_MSGS.length)];
    }

    /**
     * Returns a random message for a command format exception.
     *
     * @return a random message for a command format exception
     */
    public static String getCommandFormatExceptionMessage() {
        return COMMAND_FORMAT_EXCEPTION_MSGS[RandomUtils.getRandomInt(COMMAND_FORMAT_EXCEPTION_MSGS.length)];
    }

    /**
     * Returns a random message for an invalid index exception.
     *
     * @return a random message for an invalid index exception
     */
    public static String getInvalidIndexExceptionMessage() {
        return INVALID_INDEX_EXCEPTION_MSGS[RandomUtils.getRandomInt(INVALID_INDEX_EXCEPTION_MSGS.length)];
    }

    /**
     * Returns a random message for a storage IO exception.
     *
     * @return a random message for a storage IO exception
     */
    public static String getStorageIOExceptionMessage() {
        return STORAGE_IO_EXCEPTION_MSGS[RandomUtils.getRandomInt(STORAGE_IO_EXCEPTION_MSGS.length)];
    }

    /**
     * Returns a random message for a task format exception.
     *
     * @return a random message for a task format exception
     */
    public static String getTaskFormatExceptionMessage() {
        return TASK_FORMAT_EXCEPTION_MSGS[RandomUtils.getRandomInt(TASK_FORMAT_EXCEPTION_MSGS.length)];
    }

    /**
     * Returns a random message for an event chronology exception.
     *
     * @return a random message for an event chronology exception
     */
    public static String getEventChronologyExceptionMessage() {
        return EVENT_CHRONOLOGY_EXCEPTION_MSGS[RandomUtils.getRandomInt(EVENT_CHRONOLOGY_EXCEPTION_MSGS.length)];
    }

    /**
     * Returns a random message for an empty list of tasks.
     *
     * @return a random message for an empty list of tasks
     */
    public static String getEmptyMessage() {
        return EMPTY_MSGS[RandomUtils.getRandomInt(EMPTY_MSGS.length)];
    }
}
