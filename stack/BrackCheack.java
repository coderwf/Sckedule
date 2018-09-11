package stack;


//�������ƥ��Ĵ�����ջʵ��
//�˴��ù̶���С��ջ
public class BrackCheack {
    public static boolean checkBrack(String brace) {
    	//��ʵ����һ��ջ
        FixedStack stack = new FixedStack();
        //System.out.println(stack);   //��ӡ��һ��
        //��ʼ��� ����������ջ ���������� Ŀǰ֧���������͵�����() [] {}
        for(int i=0;i<brace.length();++i) {
        	char c = brace.charAt(i);
        	if(c == '{' || c =='(' || c == '[')//��ջ
                stack.push(c);
        	//�������Ƿ��ջ��Ԫ��ƥ��
        	else {
        		char top = stack.top();
        		if(com(top, c)) 
        			stack.pop();   //���ƥ��ͳ�ջ��ƥ��ͱ���
        		else {
        			System.out.println("invalid ele at "+i);
        			return false;
        		}//else
        	}//else
        }//for
        //�ж�ջ�Ƿ�Ϊ��
        if(stack.isEmpty() == true) {
        	System.out.println("OK");
        	return true;
        }
        System.out.println("INVALID");
        return false;
    }
    
    //������������Ƿ�ƥ��
    private static boolean com(char left,char right) {
    	if(left == '(' && right == ')')
    		return true;
    	if(left == '{' && right == '}') 
    		return true;
    	if(left == '[' && right == ']')
    		return true;
    	//����������ǲ�ƥ���
    	return false;
    }
}
