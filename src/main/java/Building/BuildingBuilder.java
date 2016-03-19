package Building;

import java.util.List;

public class BuildingBuilder {
	
	
	public BuildingBuilder(){
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
	
	public void appendBuilding(List<Building> buildings ,Building building) {
		try{
			System.out.println("Add "+building.getName());
			Thread.sleep(3000);
			buildings.add(building);
			System.out.println("Complete Adding " + building.getName());
		}catch(InterruptedException e){
			System.out.println("Add "+building.getName());
			buildings.add(building);
			System.out.println("Complete Adding " + building.getName());
		}finally{
			
		}
	}

	
	
}
