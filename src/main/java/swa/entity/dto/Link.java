package swa.entity.dto;

public class Link {
    String first;
    String prev;
    String next;
    String last;
    String self;
    String related;

    public Link() {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    @Override
    public String toString() {
        return "Link [first=" + first + ", prev=" + prev + ", next=" + next + ", last=" + last + ", self=" + self
                + ", related=" + related + "]";
    }

}
