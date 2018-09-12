package stack;

//表达式状态转移状态机
public class ExpMachine {
    public static String LEFTBRACE     = "LEFTBRACE";
    public static String RIGHTBRACE    = "RIGHTBRACE";
    public static String NUMBER        = "NUMBER";
    public static String OPERATION     = "OPERATION";
    
    public static boolean startMachine(String []types,int index) {
    	Node startNode = new StartNode();
    	return startNode.process(types, index);
    }
}

class Node{
	String status    = null;     //状态
	String accepts[] = null;     //能够接受的输入
	Node   next      = null;     //进入下一个状态
	//处理接收到的输入并进行下一个状态
	public boolean process(String types[],int index) {
		throw new UnsupportedOperationException();
	}
	//这个输入能够接受
	protected final boolean accept(String type) {
		for(String acc:this.accepts)
			if(type == acc)
				return true;
	    return false;
	}
}//状态节点

//开始节点
class StartNode extends Node{
	public StartNode() {
		 this.accepts  = new String[]{ExpMachine.LEFTBRACE,ExpMachine.NUMBER,"END"};
		 this.status   = "start";
	}//
    
	@Override
	public boolean process(String types[],int index) {
		String type = types[index];
		if(accept(type) == false)
			throw new RuntimeException("Illegal start of exp at "+index);
		if(type == ExpMachine.LEFTBRACE) 
			this.next = new LeftBraceNode();
		else if(type == ExpMachine.NUMBER)
			this.next = new NumNode();
		else 
			this.next = new EndNode();
		return this.next.process(types,index+1);
	}//process
}//

class LeftBraceNode extends Node {
	public LeftBraceNode() {
		this.accepts = new String[] {ExpMachine.LEFTBRACE,ExpMachine.NUMBER};
		this.status  = "leftbrace";
	}//
	@Override
	public boolean process(String types[],int index) {
		String type = types[index];
		if(accept(type) == false)
			throw new RuntimeException("Illegal start of exp at "+index);
		if(type == ExpMachine.LEFTBRACE) 
			this.next = new LeftBraceNode();
		else 
			this.next = new NumNode();
		return this.next.process(types,index+1);
	}//process
}

class RightBraceNode extends Node{
	public RightBraceNode() {
		this.accepts = new String[] {ExpMachine.RIGHTBRACE,ExpMachine.OPERATION,"END"};
		this.status  = "rightbrace";
	}//
	@Override
	public boolean process(String types[],int index) {
		String type = types[index];
		if(accept(type) == false)
			throw new RuntimeException("Illegal start of exp at "+index);
		if(type == ExpMachine.RIGHTBRACE) 
			this.next = new RightBraceNode();
		else if(type == ExpMachine.OPERATION)
			this.next = new OpNode();
		else
			this.next = new EndNode();
		return this.next.process(types,index+1);
	}
}

class NumNode extends Node{
	public NumNode() {
		this.accepts = new String[] {ExpMachine.RIGHTBRACE,ExpMachine.OPERATION,"END"};
		this.status  = "num";
	}//
	@Override
	public boolean process(String types[],int index) {
		String type = types[index];
		if(accept(type) == false)
			throw new RuntimeException("Illegal start of exp at "+index);
		if(type == ExpMachine.RIGHTBRACE) 
			this.next = new RightBraceNode();
		else if(type == ExpMachine.OPERATION)
			this.next = new OpNode();
		else
			this.next = new EndNode();
		return this.next.process(types,index+1);
	}
}

class OpNode extends Node{
	public OpNode() {
		this.accepts = new String[] {ExpMachine.NUMBER,ExpMachine.LEFTBRACE};
		this.status  = "num";
	}//
	@Override
	public boolean process(String types[],int index) {
		String type = types[index];
		if(accept(type) == false)
			throw new RuntimeException("Illegal start of exp at "+index);
		if(type == ExpMachine.LEFTBRACE) 
			this.next = new LeftBraceNode();
		else 
			this.next = new NumNode();
		return this.next.process(types,index+1);
	}	
}

class EndNode extends Node{
	public EndNode() {
		this.accepts = new String[] {"END"};
		this.status  = "end";
	}//
	@Override
	public boolean process(String types[],int index) {
		return true;
	}	
}