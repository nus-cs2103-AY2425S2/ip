package Aquadem;

import java.util.Arrays;

/**
 * A class that represents the encoded version of the parsed user input
 */
public class Pair {
    protected int head;
    protected String[] contents;

    /**
     * Constructor for the class Pair
     * @param head
     * @param arr
     */
    public Pair(int head, String[] arr) {
        this.head = head;
        this.contents = arr;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String[] getContents() {
        return this.contents;
    }

    public void setContents(String[] contents) {
        this.contents = contents;
    }

    /**
     * Overriding the equals method of object
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        if (obj instanceof Pair) {
            Pair o = (Pair) obj;
            if (this.head == o.head && Arrays.equals(this.contents, o.contents)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
