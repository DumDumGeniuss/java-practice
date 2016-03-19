package synchronize;

import synchronize.Bank.moneyLessThanZero;

public class SynchronizeDemo {
	public static void main(String[] args){
		//demo1();
		
		//demo2();
		
		//demo3();
		
		demo4();
	}
	
	private static void demo4() {
		
		Bank bank = Bank.getInstance();
		bank.setTotalMoney(3000.0);
		
		double money = 100.0;
		
		for(int i=0;i<100;i++){
			new Thread(){
				public void run(){
					try {
						bank.getMoneyThreadNotSafe(money);;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		
	}
	
	private static void demo3() {
		
		Bank bank = Bank.getInstance();
		bank.setTotalMoney(10000.0);
		
		double money = 100.0;
		
		for(int i=0;i<100;i++){
			new Thread(){
				public void run(){
					try {
						bank.getMoney(money);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Oops");
					}
				}
			}.start();
		}
		
	}
	

	private static void demo2() {
		for(int i=0 ; i<1000 ; i++){
			new Thread(){
				public void run(){
					Bank.getInstanceThreadNotSafe().showCreateTime();;
				}
			}.start();
		}
	}

	public static void demo1(){
		
		for(int i=0 ; i<10 ; i++){
			new Thread(){
				public void run(){
					Bank.getInstance().showCreateTime();;
				}
			}.start();
		}
		
		
	}
	
}
