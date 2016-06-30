package app;



public class Main {

	public static void main(String[] args)throws Exception{
		Thread chat1 = new Thread(new Chat("chat1",1));
		chat1.start();
		Thread chat2 = new Thread(new Chat("chat2",2));
		chat2.start();
		
	}
}
