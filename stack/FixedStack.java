package stack;

/*固定大小的数组栈
 * 1.pop()
 * 2.top()
 * 3.push()
 * 4.isEmpty()
 * 5.isFull()
 * 6.toString()*/
public class FixedStack <T> {
	//装数据的容器用数组表示
	T [] container;
	int size;
	int top = -1;//栈为空 则top为-1
	//构造函数传入栈大小
    public FixedStack(int size) {
        this.container = (T[]) new Object[50];
        this.size      = size;
    }
    //栈默认大小为50
    public FixedStack() {
    	this(50);
    }
    
    //弹出栈顶元素
    public T pop() {
    	if(top == -1) {
    		System.out.println("stack is empty ...");
    		return null;
    	}//if
    	
    	T temp = container[top];
    	top -= 1;
    	return temp;
    }//pop
    
    public T top() {
    	if(top == -1) {
    		System.out.println("stack is empty ...");
    		return null;
    	}//if
    	return container[top];
    }
    
    public void push(T value) {
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
