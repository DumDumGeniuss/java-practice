package thread;

public class WritingMaching implements Runnable{

	private String message;
	
	public WritingMaching(String message){
		this.message = message;
	}
	
	
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("Writing: "+ message);
		}
	}

}
