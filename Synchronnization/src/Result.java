
public class Result {

	public synchronized void printTopperName(String name) {
		System.out.println(Thread.currentThread().getName() + name);
		System.out.println(Thread.currentThread().getName() + name);
		System.out.println(Thread.currentThread().getName() + name);
		System.out.println(Thread.currentThread().getName() + name);
		System.out.println(Thread.currentThread().getName() + name);
		System.out.println(Thread.currentThread().getName() + name);
		
	
	}
}
