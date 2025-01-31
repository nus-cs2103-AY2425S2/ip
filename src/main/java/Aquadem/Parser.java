package Aquadem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {
        //
    }


    public static void detailCheck(String detail) throws Aquadem.DetailException {
        if (detail.trim().isEmpty()) {
            throw new Aquadem.DetailException("Sorry i need something more in my knowledge space...");
        }
    }

    public void numCheck(String detail, int limit) throws Aquadem.DetailException {
        try {
            int i = Integer.parseInt(detail)-1;
            if (i >= limit || i < 0) {
                throw new Aquadem.DetailException("that task does not exist... maybe you finished it?");
            }

        }
        catch (NumberFormatException e) {
            throw new Aquadem.DetailException("you need a number here...");
        }
    }

    public void deadlineCheck(String detail) throws Aquadem.DetailException {
        if (detail == ""){
            throw new Aquadem.DetailException("if you have no work then why you setting a deadline???");
        } else if (!detail.contains("/by")) {
            throw new Aquadem.DetailException("Without a deadline to submit by, your work gonna be a dead line, get it....");
        }
    }

    public void eventCheck(String detail) throws Aquadem.DetailException {
        if (detail.equals("")){
            throw new Aquadem.DetailException("event for what? , for who? when ?????");
        } else if (!detail.contains("/from") && !detail.contains("/to")){
            throw new Aquadem.DetailException("From when to when?????");
        } else if ((detail.contains("/from") && !detail.contains("/to")) ||(!detail.contains("/from") && detail.contains("/to"))) {
            throw new Aquadem.DetailException("From and To please");
        }
    }

    public void todoCheck(String detail) throws Aquadem.DetailException {
        if (detail == ""){
            throw new Aquadem.DetailException("You need to-do something, todo something, get it...");
        }
    }


    public Pair encodeCommand(String input, int size) throws DetailException {
        String arr[] = input.split(" ",2);

        String command = arr[0];
        String detail = "";
        if (arr.length > 1) {
            detail = arr[1];
        }

        switch(command) {
            case "list":
                String[] listDetail = {""};
                return new Aquadem.Pair(0, listDetail);
            case "deadline":
                deadlineCheck(detail);
                String[] deadlineString = detail.split("/by",2);
                return new Aquadem.Pair(1, deadlineString);
            case "event":

                eventCheck(detail);
                String[] eventString1 = detail.split("/from",2);
                String[] eventString2 = eventString1[1].split("/to",2);
                String[] eventString = {eventString1[0], eventString2[0], eventString2[1]};
                return new Aquadem.Pair(2, eventString);
            case "todo":
                todoCheck(detail);
                String[] todoDetail = {detail};
                return new Aquadem.Pair(3, todoDetail);

            case "mark":
                numCheck(detail, size);
                String[] markDetail = {detail};
                return new Aquadem.Pair(4, markDetail);
            case "unmark":
                numCheck(detail, size);
                String[] unmarkDetail = {detail};
                return new Aquadem.Pair(5, unmarkDetail);

            case "delete":
                numCheck(detail, size);
                String[] deleteDetail = {detail};
                return new Aquadem.Pair(6, deleteDetail);

            case "getdate":
                numCheck(detail, size);
                String[] dateDetail = {detail};
                return new Aquadem.Pair(7, dateDetail);

            case "bye":
                String[] noDetail = {" "};
                return new Aquadem.Pair(8, noDetail );

            default:
                String[] defaultDetail = {"unknown"};
                return new Aquadem.Pair(-1, defaultDetail);
        }


    }


}

