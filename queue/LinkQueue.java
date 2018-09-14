package queue;

/*用链表实现动态扩展队列
 * */
public class LinkQueue<T> {
	private Node head;
	private Node tail;
	int size = 0;
    public LinkQueue() {
    	head = new Node(null, null);
    	tail = head;
    	size = 0;
    }
    public int size() {
    	return size;
    }
    public boolean isEmpty() {
    	return head.next == null;
    }//
    
    public void push(T value) {
    	Node node = new Node(value, null);
    	this.tail.next = node;
    	this.tail = node;
    	size += 1;
    }
    public T pop() {
    	if(isEmpty()) {
    		System.out.println("queue is empty ...");
    		return null;
    	}//
    	size -= 1;
    	Node<T> headNext = head.next;
    	head.next = headNext.next;
    	headNext.next = null; // for gc
    	if(headNext == tail)
    		tail = head;
    	return headNext.value;
    }//pop
    
    public static void main(String []args) {
    	LinkQueue<Integer > queue = new LinkQueue<>();
    	for(int i=0;i<100;++i)
    		queue.push(i);
    	for(int i=0;i<100;++i)
    		System.out.println(queue.pop());
    	System.out.println(queue.size());
    	for(int i=0;i<50;++i)
    		queue.push(i);
    	System.out.println(queue.size());
    	for(int i=0;i<30;++i)
    		System.out.println(queue.pop());
    	for(int i=0;i<50;++i)
    		queue.push(i);
    	for(int i=0;i<70;++i)
    		System.out.println(queue.pop());
    }
}

//队列内部节点
class Node<T> {
	T value;
	Node next;
	public Node(T value,Node next) {
		this.value = value;
		this.next  = next;
	}
}
