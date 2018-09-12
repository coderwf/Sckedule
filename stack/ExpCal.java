package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.AbstractDocument.LeafElement;

//用栈进行数学表达式的基础运算
public class ExpCal {
	//检查表达式的字符合法性
	private static Map<Character, String> LegalCharMap ;
	private static Map<String, Integer> Priority;
	private static final String PUSHOP     = "PUSHOP";  //将操作符入栈
	private static final String CAL        = "CAL"   ; //操作数出栈进行计算
	private static final String POPOP      = "POPOP" ;  //弹出操作符
	private static final String PUSHNUM    = "PUSHNUM"; //操作数入栈
	private static final String POPOPANDWAIT = "POPOPANDWAIT";
	
	//导入合法字符集 0-9 +-*/ ()[]{}%
	static {
		LegalCharMap = new HashMap<>();
		Priority     = new HashMap<>();
		for(char i = '0';i<= '9';++i)
			LegalCharMap.put(i, ExpMachine.NUMBER);
		String OpCs     = "+-*/%";
		String LeftBs   = "{[(";
		String RightBs  = ")]}";
		for(int i=0;i<OpCs.length();++i)
			LegalCharMap.put(OpCs.charAt(i), ExpMachine.OPERATION);
		for(int i=0;i<LeftBs.length();++i)
			LegalCharMap.put(LeftBs.charAt(i), ExpMachine.LEFTBRACE);
		for(int i=0;i<RightBs.length();++i)
			LegalCharMap.put(RightBs.charAt(i), ExpMachine.RIGHTBRACE);
		Priority.put("+", 0);
		Priority.put("-", 0);
		Priority.put("*", 3);
		Priority.put("/", 3);
		Priority.put("%", 3);
		Priority.put("#", -3);
	}//static
	
	//根据操作符选择这一步的操作
	private static String getOp(String op1,String op2,String type) {
		//根据类型操作符栈顶操作符返回对应的操作
		if(type == ExpMachine.NUMBER)
			return PUSHNUM;
		if(type == ExpMachine.LEFTBRACE) 
			return PUSHOP;
		
		if(type == ExpMachine.RIGHTBRACE) {
			//右括号且栈顶为左括号则出栈
			if(LegalCharMap.get(op1.charAt(0)) == ExpMachine.LEFTBRACE)
				return POPOP;
			//进行计算
			return CAL;
		}
		if(op1 == op2 && op1 == "#")
			return POPOP;
		if(LegalCharMap.get(op1.charAt(0)) == ExpMachine.LEFTBRACE)
			return PUSHOP;
		//System.out.println(op1);
		//System.out.println(op2);
		if(Priority.get(op1) < Priority.get(op2))
			return PUSHOP;
		return CAL;
	}
	
	private static boolean braceMatch(char left,char right) {
		if(left == '(' && right == ')')
			return true;
		if(left == '[' && right == ']')
			return true;
		if(left == '{' && right == '}') 
			return true;
		return false;
	}
	
	//检查字符的合法性以及括号是否匹配
    private static boolean checkCharAndBrace(String exp) throws RuntimeException{
    	FixedStack <Character>stack = new FixedStack();
    	for(int i = 0;i< exp.length();++i) {
    		String type = LegalCharMap.get(exp.charAt(i));
    		if(type == null)
    			throw new RuntimeException("Illegal char at "+(i+1));
    		if(type == ExpMachine.LEFTBRACE)
    			stack.push(exp.charAt(i));
    		else if(type == ExpMachine.RIGHTBRACE){
				Character top = stack.pop();
				if(braceMatch(top, exp.charAt(i)) == false)
					throw new RuntimeException("un match brace at "+(i+1));
			}//else if
    	}//for
    	if(stack.isEmpty() == false)
    		throw new RuntimeException("un equal brace num ...");
    	return true;
    }
    
    //计算两个数,如果操作符不合法则抛出运行时异常
    private static Integer cal(String a,String b,String op) throws RuntimeException {
    	Integer ia = Integer.valueOf(a);
    	Integer ib = Integer.valueOf(b);
    	switch (op) {
    	    case "+":return ia + ib;
    	    case "-":return ia - ib;
    	    case "/":return ia / ib;
    	    case "*":return ia * ib;
    	    case "%":return ia % ib;
    	    default :throw new RuntimeException("Illegal op "+op);
		}
    }//cal
    
    private static ParseRe parseExp(String exp) {
    	//数据和类别列表
    	List<String> dataList = new ArrayList<>();
    	List<String> typeList = new ArrayList<>();
    	String iString = "";
    	for(int i = 0;i<exp.length();++i) {
    		Character c = exp.charAt(i);
    		String type = LegalCharMap.get(c);
    		if(type == ExpMachine.NUMBER) {
    			//如果时数字那么继续累加
    			iString = iString + c;
    		}//
    		else {
    			//先将前面的数字放入列表
    			if(iString.length() != 0) {
    				dataList.add(iString);
    				typeList.add(ExpMachine.NUMBER);
    				iString = "";
    			}//
    			dataList.add(c.toString());
    			typeList.add(type);
    		}//else
    	}
    	//可能存在最后一个数字
    	if(iString.length() != 0) {
			dataList.add(iString);
			typeList.add(ExpMachine.NUMBER);
			iString = "";
		}//
    	typeList.add("END");
    	System.out.println(dataList);
    	System.out.println(typeList);
    	ParseRe re = new ParseRe();
    	re.datas = (String[]) dataList.toArray(new String[dataList.size()]);
    	re.types = (String[]) typeList.toArray(new String[typeList.size()]);
    	return re;
    }//将表达式字符串的数字操作符括号解析出来放到数组中
    
    //用两个栈对数据进行计算
    private static int calExp(String [] datas,String types[]) {
    	//一个操作数栈
    	FixedStack <String>dataStack = new FixedStack();
    	//一个操作符栈
    	FixedStack <String>opStack   = new FixedStack();
    	datas = Arrays.copyOf(datas, datas.length+1);
    	datas[datas.length-1] = "#"  ; // 补充一个#
    	opStack.push("#");
    	//System.out.println(opStack.isEmpty());
    	int i = 0;
    	while(i<datas.length || !opStack.isEmpty()) {
    		String type = types[i];
    		String data = datas[i];
    		String top = opStack.top();
    		String op = getOp(top, data, type);
    		System.out.println(op);
    		switch(op) {
    		case PUSHOP   :opStack.push(data);i+=1;break;
    		case PUSHNUM  :dataStack.push(data);i+=1;break;
    		case POPOP    :opStack.pop();i+=1;break;
    		case POPOPANDWAIT:opStack.pop();break;
    		case CAL      :{
    			String data1 = dataStack.pop();
    			String data2 = dataStack.pop();
    			String op1    = opStack.pop();
    			String re    = cal(data2, data1, op1).toString();
    			dataStack.push(re);
    		};break;//
    		}//switch
    		
    	}//while
    	
    	return Integer.valueOf(dataStack.pop());
    }//
    
    public static Integer calExp(String exp) {
    	//第一步检查表达式字符正确性
    	//检查语义正确性
    	//计算表达式的值
    	//返回结果
    	return 0;
    }//调用入口
    public static void main(String args[]) {
    	String exp = "(1+2)*3+(7+3)/2";
    	ParseRe re = parseExp(exp);
    	String datas[] = re.datas;
    	String types[] = re.types;
    	System.out.println(ExpMachine.startMachine(types, 0));
    	System.out.println(calExp(datas, types));
    }//main
}

//存储解析字符串后的结果
class ParseRe{
	String [] types = null;  //类型
	String [] datas = null;   //数据
}


