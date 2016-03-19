package building;

public class House implements Building {
	
	private String name;
	
	
	public House(String name) throws InterruptedException{
		this.name = name;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
