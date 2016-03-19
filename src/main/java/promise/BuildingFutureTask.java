package promise;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import building.Building;
import building.BuildingFactory;

public class BuildingFutureTask {
	
	private BuildingFactory builder;
	
	public BuildingFutureTask(BuildingFactory builder){
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
	



}
