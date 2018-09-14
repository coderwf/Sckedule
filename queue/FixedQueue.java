package queue;

/*fixed queue
 * 总容量固定,
 * 底层用数组实现
 * 
 * 为了比较容易分辨空和满需要多留一格*/
public class FixedQueue <T>{
	private T [] a;
	private int size ;
	private int front = 0;
	private int tail  = 0; 
    public FixedQueue(int totalSize){
    	this.size = 0;
    	this.a = (T [])new Object[totalSize+1];
    }
    public FixedQueue(){
    	this(50); //默认大小为50
    }
    
    public int totalSize() {
    	return a.length - 1;
    }
    public int size() {
    	return size;
    }
    public void push(T value) {
    	int next = (tail + 1) % a.length;
    	if(next == front) {
    		System.out.println("queue is full");
    		return ;
    	}
    	size += 1;
    	this.a[tail] = value;
    	tail = next;
    }//
    public T pop() {
    	if(front == tail) {
    		System.out.println("queue is empty...");
    		return null;
    	}
    	size -= 1;
    	T res = this.a[front];
    	front = (front + 1) % a.length;
    	return res;
    }
    
    public boolean isFull() {
    	return ((tail+1) % a.length) == front;
    }
    
    public boolean isEmpty() {
    	return front == tail;
    }
    
    
    public static void main(String []args) {
    	FixedQueue<Integer> queue = new FixedQueue<>();
    	for(int i=0;i<50;++i)
    		queue.push(i);
    	for(int i=0;i<20;++i)
    		System.out.println(queue.pop());
   	    for(int i=50;i>40;--i)
   		    queue.push(i);
    	for(int i=0;i<40;++i)
    		System.out.println(queue.pop());
    }
}
