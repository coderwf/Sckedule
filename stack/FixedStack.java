package stack;

/*�̶���С��ջ,�ײ�������ʵ��,��������
 * ��������:
 * 1.ȡ����
 * 2.������
 * 3.�ж�Ϊ��
 * 4.�ж�Ϊ��
 * 5.�鿴��������
 * 6.��дtoString����*/
public class FixedStack {
	//����һ��������ݵ�������С�̶�
	char container[];
	int size;
	int top = -1;//ջ����λ������
	//���캯�����������С
    public FixedStack(int size) {
        this.container = new char [50];
        this.size      = size;
    }
    //Ĭ�ϴ�С50
    public FixedStack() {
    	this(50);
    }
    
    //..........��������
    public char pop() {
    	if(top == -1) {
    		System.out.println("stack is empty ...");
    		return 0;
    	}//if
    	
    	char temp = container[top];
    	top -= 1;
    	return temp;
    }//pop
    
    public char top() {
    	if(top == -1) {
    		System.out.println("stack is empty ...");
    		return 0;
    	}//if
    	return container[top];
    }
    
    public void push(char value) {
    	//System.out.println(size-1);
    	if(top == size - 1) {
    		System.out.println("stack is full ...");
    		return ;
    	}//if
    	top += 1;
    	container[top ] = value;
    }
    
    public boolean isEmpty() {
    	return top == -1;
    }
    public boolean isFull() {
    	return top == size -1 ;
    }
    
    public String toString() {
    	String text = "";
    	text += "stack :"+"[size : "+(top+1) + " ] data :[";
    	for(int i=0;i<=top;++i)
    		text += container[i] + "";
        text += "]";
        return text;
    }
}
