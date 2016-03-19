package building;

import java.time.LocalDateTime;

public class BuildingFactory {
	
	
	private static BuildingFactory instance = null;
	private LocalDateTime createTime;
	
	private BuildingFactory(LocalDateTime localDateTime){
		createTime = localDateTime;
	}
	
	public static BuildingFactory getInstance(){
		if(instance == null){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance = new BuildingFactory(LocalDateTime.now());
		}
		return instance;
	}
	
	public void showCreateTime(){
		System.out.println(createTime.toString());
	}
	
	public static synchronized BuildingFactory getInstanceWithThreadSafe(){
		if(instance == null){
			try {
				System.out.println("getInstanceWithThreadSafe is blocked now...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance = new BuildingFactory(LocalDateTime.now());
		}
		return instance;
	}


	public Building buildHouse(String name){
		House house = null;
		try{
			house = new House(name);
			System.out.println("Start building house , name: "+name);
			Thread.sleep(2000);
			System.out.println("Finsih building house , name: "+name);
			return house;
		}catch(InterruptedException e){
			return house;
		}
	}

	public Building buildCastle(String name) throws InterruptedException {
		Castle castle = null;
		try{
			castle = new Castle(name);
			System.out.println("Start building castle , name: "+name);
			Thread.sleep(5000);
			System.out.println("Finsih building castle , name: "+name);
			return castle;
		}catch(InterruptedException e){
			return castle;
		}
	}
	

	
	
}
