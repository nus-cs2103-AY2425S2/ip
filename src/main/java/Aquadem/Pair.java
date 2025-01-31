package Aquadem;

public class Pair {
    protected int head;
    protected String[] contents;

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
}
