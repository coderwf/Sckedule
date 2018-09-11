package stack;



/*链表栈,大小可以扩充,内存是动态开辟的
 * 所以链表栈大小不可能满,理论上可以无穷大*/
public class LinkStack {
	//内部类,栈中存放数据的节点
	// Node -> Node -> Node 头节点不存放数据
	//头节点的下一个节点是栈顶
    static class Node{
    	int data; //数据
    	Node next; // 下一个节点
    	Node(int data,Node next){
    		this.data =data;
    		this.next = next;
    	}
    }//
    
    private Node head = new Node(0,null); // 头节点不存放数据
    private int size  = 0;//当前栈的大小 
    
    //基本用法
    public int top() {
    	if(size == 0) {
    		System.out.println("stack is empty ...");
    		return 0;
    	}//if
    	return head.next.data;
    }
    public int pop() {
    	if(size == 0) {
    		System.out.println("stack is empty ...");
    		return 0;
    	}//if
    	Node topNode = head.next; //栈顶节点
        int temp = topNode.data;
        head.next = topNode.next; //链表操作,将头节点下一个节点指向栈顶节点的下一个节点
        size -= 1;//栈大小-1
        topNode.next = null;
        return temp;
    }
    
    public void push(int value) {
    	//由于是动态扩展大小所以链表不可能满
    	//构造一个链表节点
        Node node = new Node(value, null);
        //将节点插入到顶部
        node.next = head.next;
        head.next = node;
        //插入操作自己画图看,链表的操作
        size += 1;  //栈大小+1
    	return ;
    }//void
    
    public boolean isEmpty() {
    	return size == 0;
    }
}
