package synchronize;

import java.time.LocalDateTime;

public class Bank{
	
	private static Bank instance;
	double totalMoney;
	private LocalDateTime createTime;
	
	private Bank(LocalDateTime localDateTime){
		this.createTime = localDateTime;
	}
	
	public static synchronized Bank getInstance(){
		if(instance == null){
			instance = new Bank(LocalDateTime.now());
		}
		return instance;
	}
	
	public static  Bank getInstanceThreadNotSafe(){
		if(instance == null){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance = new Bank(LocalDateTime.now());
		}
		return instance;
	}

	public void showCreateTime(){
		System.out.println(createTime.toString());
	}
	
	
	
	public synchronized void setTotalMoney(double money){
		if(totalMoney==0){
			this.totalMoney = money;
		}else{
			System.out.println("Money has been set");
		}
	}
	
	public void showLeftMoney(){
		System.out.println("Money left: "+ this.totalMoney);
	}
	
	public void saveMoney(double money){
		this.totalMoney+=money;
	}
	
	public void getMoney(double money) throws moneyLessThanZero{
		try {
			Thread.sleep(1);
			synchronized(this){
				if( (this.totalMoney - money ) > 0 ){
					System.out.println("Before getting money: " + this.totalMoney);
					this.totalMoney-=money;
					System.out.println("After getting money: " + this.totalMoney);
					if(this.totalMoney<0){
						throw new moneyLessThanZero() ;
					}
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void getMoneyThreadNotSafe(double money) throws moneyLessThanZero{
		try {
			Thread.sleep(1);
			if( (this.totalMoney - money ) > 0 ){
				System.out.println("Before getting money: " + this.totalMoney);
				this.totalMoney-=money;
				System.out.println("After getting money: " + this.totalMoney);
				if(this.totalMoney<0){
					throw new moneyLessThanZero() ;
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public class moneyLessThanZero extends Exception{
		private static final long serialVersionUID = 1L;
		
	}
	
}
