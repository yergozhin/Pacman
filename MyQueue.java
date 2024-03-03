import java.util.List;

public class MyQueue {
     class Node {
        Triple data;
        Node next = null; 
        Node(Triple d) {
            data = d;
        }
    }
    private Node head, tail;
    public MyQueue() {
        head = tail = null;
    }
    public void enqueue(Triple s) {
        if (head == null)
            head = tail = new Node(s);
        else
            tail = tail.next = new Node(s);
    }
    public Triple dequeue() {
        Triple s = head.data;
        if ((head = head.next) == null) tail = null;
        return s;
    }
    public boolean empty() {
        return head == null;
    }
    public int size(){
        Node temp = head;
        int count = 0;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }
}
class Triple{
    int i;
    int j;
    List<int[]> list;
    public Triple(int i, int j, List<int[]> list){
        this.i = i;
        this.j = j;
        this.list = list;
    }
}
