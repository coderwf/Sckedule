package stack;

/*固定大小的栈,底层用数组实现,不可扩充
 * 基本方法:
 * 1.取数据
 * 2.存数据
 * 3.判断为空
 * 4.判断为满
 * 5.查看顶部数据
 * 6.复写toString方法*/
public class FixedStack {
	//定义一个存放数据的容器大小固定
	char container[];
	int size;
	int top = -1;//栈顶的位置索引
	//构造函数传入数组大小
    public FixedStack(int size) {
        this.container = new char [50];
        this.size      = size;
    }
    //默认大小50
    public FixedStack() {
    	this(50);
    }
    
    //..........基本方法
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
