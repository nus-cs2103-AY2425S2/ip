package aquadem;

/**
 * A class that is used to parse the give user input.
 */

public class Parser {
    /**
     * Constructs an empty Parser.
     */
    public Parser() {
        //
    }

    /**
     * Checks if the given string is empty.
     * @param detail
     * @throws DetailException
     */
    public static void detailCheck(String detail) throws DetailException {
        if (detail.trim().isEmpty()) {
            throw new DetailException("Sorry i need something more in my knowledge space...");
        }
    }

    /**
     * Checks if the given string is a number wihtin a given limit.
     * @param detail
     * @param limit
     * @throws DetailException
     */

    public void numCheck(String detail, int limit) throws DetailException {
        try {
            int i = Integer.parseInt(detail)-1;

            if (i >= limit || i < 0) {
                throw new DetailException("that task does not exist... maybe you finished it?");
            }

        }
        catch (NumberFormatException e) {
            throw new DetailException("you need a number here...");
        }
    }


    /**
     * Checks if the given String contains a deadline (/by).
     * @param detail
     * @throws DetailException
     */

    public void deadlineCheck(String detail) throws DetailException {
        if (detail == ""){
            throw new DetailException("if you have no work then why you setting a deadline???");
        } else if (!detail.contains("/by")) {
            throw new DetailException("Without a deadline to submit by, your work gonna be " +
                    "a dead line, get it....");
        }
    }


    /**
     * Checks if the given String contains /from and /to.
     * @param detail
     * @throws DetailException
     */

    public void eventCheck(String detail) throws DetailException {
        if (detail.equals("")){
            throw new DetailException("event for what? , for who? when ?????");
        } else if (!detail.contains("/from") && !detail.contains("/to")){
            throw new DetailException("From when to when?????");
        } else if ((detail.contains("/from") && !detail.contains("/to")) ||
                (!detail.contains("/from") && detail.contains("/to"))) {
            throw new DetailException("From and To please");
        }
    }

    /**
     * Checks the given string is not empty.
     * @param detail
     * @throws DetailException
     */
    public void todoCheck(String detail) throws DetailException {
        if (detail == ""){
            throw new DetailException("You need to-do something, todo something, get it...");
        }
    }

    /**
     * Checks the given string is not empty and contains after.
     * @param detail
     * @throws DetailException
     */
    public void doafterCheck(String detail) throws DetailException {
        if (detail == "" || !detail.contains("/after")){
            throw new DetailException("You need a date if you want to do something after");
        }
    }

    /**
     * Returns an encoded Pair which contians information about parsing.
     * @param input Given input
     * @param size Size of the tasklist
     * @return an object of type Pair
     * @throws DetailException
     */
    public Pair encodeCommand(String input, int size) throws DetailException {
        String arr[] = input.split(" ",2); //Lower level abstraction is easier to understand.
        String command = arr[0];
        String detail = "";
        if (arr.length > 1) {
            detail = arr[1];
        }

        switch(command) {
        case "list":
            return listParse();
        case "deadline":
            return deadlineParse(detail);
        case "event":
            return eventParse(detail);
        case "todo":
            return todoParse(detail);
        case "mark":
            return markParse(size, detail);
        case "unmark":
            return unmarkParse(size, detail);
        case "delete":
            return deleteParse(size, detail);
        case "getdate":
            return getdateParse(size, detail);
        case "bye":
            return byeParse();
        case "find":
            return findParse(detail);
        case "doafter":
            return doafterParse(detail);
        default:
            return defaultParse();
        }


    }

    /**
     * Parses default case.
     * @return A pair.
     */
    private static Pair defaultParse() {
        String[] defaultDetail = {"unknown"};
        return new Pair(-1, defaultDetail);
    }

    /**
     * Parses doafters.
     * @param detail
     * @return A pair.
     * @throws DetailException
     */
    private Pair doafterParse(String detail) throws DetailException {
        doafterCheck(detail);
        String[] doafterString = detail.split("/after",2);
        return new Pair(10, doafterString);
    }

    /**
     * Parses finds commands.
     * @param detail
     * @return A pair.
     * @throws DetailException
     */
    private static Pair findParse(String detail) throws DetailException {
        detailCheck(detail);
        String[] findDetail = {detail};
        return new Pair(9, findDetail);
    }

    /**
     * Parses byes.
     * @return A pair.
     */
    private static Pair byeParse() {
        String[] noDetail = {" "};
        return new Pair(8, noDetail);
    }

    /**
     *  Parses getdate commands.
     * @param size
     * @param detail
     * @return  A pair.
     * @throws DetailException
     */
    private Pair getdateParse(int size, String detail) throws DetailException {
        numCheck(detail, size);
        String[] dateDetail = {detail};
        return new Pair(7, dateDetail);
    }

    /**
     * Parses delete commands.
     * @param size
     * @param detail
     * @return  A pair.
     * @throws DetailException
     */
    private Pair deleteParse(int size, String detail) throws DetailException {
        numCheck(detail, size);
        String[] deleteDetail = {detail};
        return new Pair(6, deleteDetail);
    }

    /**
     * Parses unmark commands.
     * @param size
     * @param detail
     * @return A pair.
     * @throws DetailException
     */
    private Pair unmarkParse(int size, String detail) throws DetailException {
        numCheck(detail, size);
        String[] unmarkDetail = {detail};
        return new Pair(5, unmarkDetail);
    }

    /**
     * Parses mark commands.
     * @param size
     * @param detail
     * @return A pair.
     * @throws DetailException
     */
    private Pair markParse(int size, String detail) throws DetailException {
        numCheck(detail, size);
        String[] markDetail = {detail};
        return new Pair(4, markDetail);
    }

    /**
     * Parses todos.
     * @param detail
     * @return  A pair.
     * @throws DetailException
     */
    private Pair todoParse(String detail) throws DetailException {
        todoCheck(detail);
        String[] todoDetail = {detail};
        return new Pair(3, todoDetail);
    }

    /**
     * Parses events.
     * @param detail
     * @return  A pair.
     * @throws DetailException
     */
    private Pair eventParse(String detail) throws DetailException {
        eventCheck(detail);
        String[] eventString1 = detail.split("/from",2);
        String[] eventString2 = eventString1[1].split("/to",2);
        String[] eventString = {eventString1[0], eventString2[0], eventString2[1]};
        assert !eventString[2].isEmpty();
        return new Pair(2, eventString);
    }

    /**
     * Parses deadlines.
     * @param detail
     * @return  A pair.
     * @throws DetailException
     */
    private Pair deadlineParse(String detail) throws DetailException {
        deadlineCheck(detail);
        String[] deadlineString = detail.split("/by",2);
        assert !deadlineString[1].isEmpty();
        return new Pair(1, deadlineString);
    }

    /**
     * Parses list commands.
     * @return A pair.
     */
    private static Pair listParse() {
        String[] listDetail = {""};
        return new Pair(0, listDetail);
    }


}

