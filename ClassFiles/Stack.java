package DSA;

public class Stack {
	class Node{
		String data;
		Node next;
		
		Node(String data){
			this.data=data;
			next=null;
		}
	}
	
	Node head;
	Stack(){
		head=null;
	}
	
	Boolean isEmpty() {
		return head==null;
	}
	
	void push(String str) {
		Node temp=new Node(str);
		if(isEmpty()) {
			head=temp;
		}
		else {
			temp.next=head;
			head=temp;
		}
	}
	
	String pop() {
		String str="";
		if(!isEmpty()) {
			str=head.data;
			head=head.next;
		}
		return str;
	}
}
