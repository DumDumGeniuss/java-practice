package reactive_x;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import Building.Building;
import Building.BuildingBuilder;
import Building.House;
import rx.*;
import rx.functions.Action;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
public class ReactiveXDemo {
	public static void main(String args[]) throws InterruptedException {
		
		demo1();

	}

	
	private static void demo1() {
		
		final BuildingBuilder bb = new BuildingBuilder();
		final String[] houseNames = new String[]{"Amy's house","Steven's hosue","Allen's house"};
		final String[] castleNames = new String[]{"Linda's castle","Allen's castle","Kate's castle"};	
		final LinkedList<Building> houses = new LinkedList<Building>();
		final LinkedList<Building> castles = new LinkedList<Building>();
		
		Observable.create(new Observable.OnSubscribe<Building>() {

			public void call(final Subscriber<? super Building> obs) {
				for(final String name : houseNames){
					new Thread(){
						public void run(){
							try {
								obs.onNext( bb.buildHouse(name));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
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
				try {
					if(building instanceof House){
						bb.appendBuilding(houses, building);
					}else{
						bb.appendBuilding(castles, building);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		
	}
}
