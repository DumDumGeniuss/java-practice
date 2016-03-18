package reactive_x;

import java.util.List;

import Building.Building;
import Building.BuildingBuilder;
import rx.Observable;
import rx.Subscriber;

public class BuildingReactiveX {
	
	private BuildingBuilder builder;
	
	public BuildingReactiveX(BuildingBuilder builder){
		this.builder =builder;
	}
	
	public Observable<Building> buildHouseObserver(final String name){
		Observable<Building> observe = Observable.create(
				new Observable.OnSubscribe<Building>() {

					public void call(Subscriber<? super Building> sub) {
						try {
							Building house = builder.buildHouse(name);
							sub.onNext(house);
							sub.onCompleted();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
		);
		return observe;
	}
	
	public Observable<Building> buildCastleObserver(final String name){
		Observable<Building> observe = Observable.create(
				new Observable.OnSubscribe<Building>() {

					public void call(Subscriber<? super Building> sub) {
						try {
							Building castle = builder.buildCastle(name);
							sub.onNext(castle);
							sub.onCompleted();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
		);
		return observe;
	}
	
	public void appendBuilding(List<Building> buildings , Building building) throws InterruptedException{
		builder.appendBuilding(buildings, building);
	}
	
	
	
}
