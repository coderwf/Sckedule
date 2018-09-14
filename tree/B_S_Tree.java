package tree;

/*二叉排序树
 * 节点内部元素必须具有可比较性*/
public class B_S_Tree <T extends Comparable<T>>{
    private Node<T> root;
    private int size = 0;
    public B_S_Tree() {
    	
    }
    public T find(T value) {
    	return null;
    }
    public T push(T value) {
    	return null;
    }
    public T delete(T value) {
    	return null;
    }
    //按照什么顺序打印
    public void print(boolean reverse) {
    	
    }//
    
    public static void main(String args[]) {
    	
    }//
}

class Node<T extends Comparable<T>> {
	T value;
	Node left;
	Node right;
	Node(T value,Node left,Node right){
		this.left = left;
		this.right = right;
		this.value = value;
		
	}
}

class A{
	
}
