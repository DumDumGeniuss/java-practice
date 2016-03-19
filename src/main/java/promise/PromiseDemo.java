package promise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import Building.Building;
import Building.BuildingBuilder;


public class PromiseDemo {

	public static void main(String args[]) throws InterruptedException, ExecutionException{


		long startT = System.currentTimeMillis();
		
		System.out.println("============Demo1 start!!============");
		demo1();
		System.out.println("===========Demo1 with used time: "+ (System.currentTimeMillis() - startT) + "ms============" );
		
		
		Thread.sleep(4000);
		
		startT = System.currentTimeMillis();
		System.out.println("============Demo2 start!!============");
		demo2();
		System.out.println("===========Demo2 with used time: "+ (System.currentTimeMillis() - startT) + "ms============" );

	}

	private static void demo2() throws InterruptedException {

		BuildingBuilder bb = new BuildingBuilder();

		List<Building> houses = new LinkedList<Building>();
		List<Building> castles = new LinkedList<Building>();
		
		Building house1 = bb.buildHouse("Amy's house");
		Building house2 = bb.buildHouse("Steven's house");
		Building house3 = bb.buildHouse("Allen's house");
		Building castle1 = bb.buildCastle("Linda's Castle");
		Building castle2 = bb.buildCastle("Allen's Castle");
		Building castle3 = bb.buildCastle("Kate's Castle");
		
		bb.appendBuilding(houses, house1);
		bb.appendBuilding(houses, house2);
		bb.appendBuilding(houses, house3);
		bb.appendBuilding(castles, castle1);
		bb.appendBuilding(castles, castle2);
		bb.appendBuilding(castles, castle3);
		
	}

	private static void demo1() throws InterruptedException, ExecutionException {
		
		BuildingFutureTask bt = new BuildingFutureTask(new BuildingBuilder());
		
		List<Building> houses = new LinkedList<Building>();
		List<Building> castles = new LinkedList<Building>();
		
		Future<Building> housePromise1 = bt.getHouse("Amy's house"); 
		Future<Building> castlePromise1 = bt.getCastle("John's Castle"); 
		Future<Building> housePromise2 = bt.getHouse("Steven's house"); 
		Future<Building> castlePromise2 = bt.getCastle("Linda's Castle"); 
		Future<Building> housePromise3 = bt.getHouse("Allen's house"); 
		Future<Building> castlePromise3 = bt.getCastle("Kate's Castle"); 
		
		int check=0;
		int[] flags = new int[6];
		Arrays.fill(flags, 0);
		while(true){
			if(castlePromise1.isDone()&&flags[0]==0){
				bt.appendBuilding(castles, castlePromise1.get());
				flags[0] = 1;
				check++;
			}
			if(castlePromise2.isDone()&&flags[1]==0){
				bt.appendBuilding(castles, castlePromise2.get());
				flags[1] = 1;
				check++;
			}
			if(castlePromise3.isDone()&&flags[2]==0){
				bt.appendBuilding(castles, castlePromise3.get());
				flags[2] = 1;
				check++;
			}
			if(housePromise1.isDone()&&flags[3]==0){
				bt.appendBuilding(houses, housePromise1.get());
				flags[3] = 1;
				check++;
			}
			if(housePromise2.isDone()&&flags[4]==0){
				bt.appendBuilding(houses, housePromise2.get());
				flags[4] = 1;
				check++;
			}
			if(housePromise3.isDone()&&flags[5]==0){
				bt.appendBuilding(houses, housePromise3.get());
				flags[5] = 1;
				check++;
			}
			if(check==6) {break;}
			Thread.sleep(500);
		}
		

		
	}

}
