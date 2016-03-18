package promise;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import Building.Building;
import Building.BuildingBuilder;

public class BuildingFutureTask {
	
	private BuildingBuilder builder;
	
	public BuildingFutureTask(BuildingBuilder builder){
		this.builder = builder;
	}
	
	public Future<Building> getHouse(final String name){
		Callable <Building> callable = new Callable<Building>(){

			public Building call() throws Exception {
				return builder.buildHouse(name);
			}
			
		};
		
		FutureTask<Building> promise = new FutureTask<Building>(callable);
		new Thread(promise).start();
		
		return promise;
 	}
	
	public Future<Building> getCastle(final String name){
		Callable <Building> callable = new Callable<Building>(){

			public Building call() throws Exception {
				return builder.buildCastle(name);
			}
			
		};
		
		FutureTask<Building> promise = new FutureTask<Building>(callable);
		new Thread(promise).start();
		
		return promise;
 	}
	
	public Future<Boolean> appendBuilding(final List<Building> buildings , final Building building){
		
		Callable <Boolean> callable = new Callable<Boolean>(){

			public Boolean call() throws Exception {
				builder.appendBuilding(buildings, building);
				return true;
			}
			
		};
		
		FutureTask<Boolean> promise = new FutureTask<Boolean>(callable);
		new Thread(promise).start();
		
		return promise;
		
	}


}
