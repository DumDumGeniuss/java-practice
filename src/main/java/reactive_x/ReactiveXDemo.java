package reactive_x;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import Building.Building;
import Building.BuildingBuilder;
import rx.*;
import rx.schedulers.Schedulers;
public class ReactiveXDemo {
	public static void main(String args[]) throws InterruptedException {
		
		demo1();

	}

	
	private static void demo1() {
		final BuildingReactiveX brx = new BuildingReactiveX( new BuildingBuilder());
		List<Observable<Building>> houseObservers = new LinkedList<Observable<Building>>();
		List<Observable<Building>> castleObservers = new LinkedList<Observable<Building>>();
		final LinkedList<Building> houses = new LinkedList<Building>();
		final LinkedList<Building> castles = new LinkedList<Building>();
		String[] houseNames = new String[]{"Amy's house","Steven's house","Allen's house"};
		String[] castleNames = new String[]{"Linda's castle","Allen's castle","Kate's castle"};
		
		//ThreadPoolExecutor exec = new ThreadPoolExecutor(0, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		//Scheduler s = Schedulers.from(exec);
		
		for(final String name: houseNames){
			houseObservers.add( brx.buildHouseObserver(name) );
		}
		for(final String name: castleNames){
			castleObservers.add( brx.buildCastleObserver(name) );
		}
		
		Subscriber<Building> sub1 = new Subscriber<Building>() {
		    public void onCompleted() {
		        System.out.println("Complete");
		    }
		    public void onError(Throwable e) {

		    }
		    public void onNext(Building house) {
		    	try {
					brx.appendBuilding(houses, house);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		};
		Subscriber<Building> sub2 = new Subscriber<Building>() {
		    public void onCompleted() {
		        System.out.println("Complete");
		    }
		    public void onError(Throwable e) {

		    }
		    public void onNext(Building castle) {
		    	try {
					brx.appendBuilding(castles, castle);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		};
		
		for(Observable<Building> observer:houseObservers){
			observer.subscribeOn(Schedulers.from(new ThreadPoolExecutor(0, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()))).observeOn(Schedulers.computation()).subscribe(sub1);
		}
		for(Observable<Building> observer:castleObservers){
			observer.subscribeOn(Schedulers.from(new ThreadPoolExecutor(0, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()))).observeOn(Schedulers.computation()).subscribe(sub2);
		}
		
	}	
}
