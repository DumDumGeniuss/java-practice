package reactive_x;

import java.util.LinkedList;
import java.util.List;

import building.Building;
import building.BuildingFactory;
import building.House;
import rx.*;
public class ReactiveXDemo {
	public static void main(String args[]) throws InterruptedException {
		
		demo1();

	}

	
	private static void demo1() {
		
		final BuildingFactory bb = BuildingFactory.getInstance();
		final String[] houseNames = new String[]{"Amy's house","Steven's hosue","Allen's house"};
		final String[] castleNames = new String[]{"Linda's castle","Allen's castle","Kate's castle"};	
		final List<Building> houses = new LinkedList<Building>();
		final List<Building> castles = new LinkedList<Building>();
		
		Observable.create(new Observable.OnSubscribe<Building>() {

			public void call(final Subscriber<? super Building> obs) {
				for(final String name : houseNames){
					new Thread(){
						public void run(){
							obs.onNext( bb.buildHouse(name));
						}
					}.start();
				}
				for(final String name : castleNames){
					new Thread(){
						public void run(){
							try {
								obs.onNext( bb.buildCastle(name));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}.start();
				}
			}
		}).subscribe(new Subscriber<Building>(){

			public void onCompleted() {
				System.out.println("Complete");
				
			}

			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onNext(Building building) {
				System.out.println("Complete building: " + building.getName());
				if(building instanceof House){
					houses.add(building);
				}else{
					castles.add(building);
				}
				
			}
			
		});
		
		System.out.println("=========Do this first===========");
		
		 
	}
}
