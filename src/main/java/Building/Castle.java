package Building;

public class Castle implements Building{
	
	private String name;
	
	public Castle(String name) throws InterruptedException{
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
