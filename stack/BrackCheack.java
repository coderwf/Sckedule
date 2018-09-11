package stack;


//检查括号匹配的代码用栈实现
//此处用固定大小的栈
public class BrackCheack {
    public static boolean checkBrack(String brace) {
    	//先实例化一个栈
        FixedStack stack = new FixedStack();
        //System.out.println(stack);   //打印看一下
        //开始检查 遇到左括入栈 右括号则检查 目前支持三种类型的括号() [] {}
        for(int i=0;i<brace.length();++i) {
        	char c = brace.charAt(i);
        	if(c == '{' || c =='(' || c == '[')//入栈
                stack.push(c);
        	//否则检查是否和栈顶元素匹配
        	else {
        		char top = stack.top();
        		if(com(top, c)) 
        			stack.pop();   //如果匹配就出栈不匹配就报错
        		else {
        			System.out.println("invalid ele at "+i);
        			return false;
        		}//else
        	}//else
        }//for
        //判断栈是否为空
        if(stack.isEmpty() == true) {
        	System.out.println("OK");
        	return true;
        }
        System.out.println("INVALID");
        return false;
    }
    
    //检查左右括号是否匹配
    private static boolean com(char left,char right) {
    	if(left == '(' && right == ')')
    		return true;
    	if(left == '{' && right == '}') 
    		return true;
    	if(left == '[' && right == ']')
    		return true;
    	//其余情况都是不匹配的
    	return false;
    }
}
