import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


public class Test {

	class Basket {
		public BlockingQueue<String> basket = new LinkedBlockingQueue<String>();
		
		public void produce() throws InterruptedException{
			basket.put("An apple");
		}
		
		public void consume() throws InterruptedException{
			basket.take();
		}
		
	}
	
	class Producer implements Runnable{

		
		public Producer(String instance, Basket basket) {
			super();
			this.instance = instance;
			this.basket = basket;
		}
		
		@Override
		public void run() {
			try{
				while(true){
					System.out.println("producing : " + instance);
					basket.produce();
					System.out.println("produced : " + instance);
					Thread.sleep(300);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		private String instance;
		private Basket basket;
	}
	
	class Consumer implements Runnable{

		public Consumer(String instance, Basket basket) {
			super();
			this.instance = instance;
			this.basket = basket;
		}
		
		@Override
		public void run() {
			try{
				while(true){
					System.out.println("Consuming : " + instance);
					basket.consume();
					System.out.println("Consumed : " + instance);
					Thread.sleep(1000);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
		private String instance;
		private Basket basket;
	}
	
	public static void main(String[] args){
		Test test = new Test();
		Basket basket = test.new Basket();
		
		System.out.println("service started...");
		
		ExecutorService service = Executors.newCachedThreadPool();
		Producer producer1 = test.new Producer("producer001", basket);
		Producer producer2 = test.new Producer("producer002", basket);
		Consumer consumer = test.new Consumer("consumer001", basket);
		service.submit(producer1);
		service.submit(producer2);
		service.submit(consumer);
		
		System.out.println("service stoped...");
		
	}
	
	
}
