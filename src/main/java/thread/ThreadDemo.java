package thread;

public class ThreadDemo {
	public static void main(String args[]){
		
		Thread t1 = new Thread(new WritingMaching("I'm Maching_111"));
		Thread t2 = new Thread(new WritingMaching("I'm Maching_222"));
		t1.start();
		t2.start();
	}
}
