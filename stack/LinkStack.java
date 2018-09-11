package stack;



/*����ջ,��С��������,�ڴ��Ƕ�̬���ٵ�
 * ��������ջ��С��������,�����Ͽ��������*/
public class LinkStack {
	//�ڲ���,ջ�д�����ݵĽڵ�
	// Node -> Node -> Node ͷ�ڵ㲻�������
	//ͷ�ڵ����һ���ڵ���ջ��
    static class Node{
    	int data; //����
    	Node next; // ��һ���ڵ�
    	Node(int data,Node next){
    		this.data =data;
    		this.next = next;
    	}
    }//
    
    private Node head = new Node(0,null); // ͷ�ڵ㲻�������
    private int size  = 0;//��ǰջ�Ĵ�С 
    
    //�����÷�
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
    	Node topNode = head.next; //ջ���ڵ�
        int temp = topNode.data;
        head.next = topNode.next; //�������,��ͷ�ڵ���һ���ڵ�ָ��ջ���ڵ����һ���ڵ�
        size -= 1;//ջ��С-1
        topNode.next = null;
        return temp;
    }
    
    public void push(int value) {
    	//�����Ƕ�̬��չ��С��������������
    	//����һ������ڵ�
        Node node = new Node(value, null);
        //���ڵ���뵽����
        node.next = head.next;
        head.next = node;
        //��������Լ���ͼ��,����Ĳ���
        size += 1;  //ջ��С+1
    	return ;
    }//void
    
    public boolean isEmpty() {
    	return size == 0;
    }
}
