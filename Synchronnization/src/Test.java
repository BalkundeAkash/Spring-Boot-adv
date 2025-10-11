
public class Test {

	public static void main(String[] args) {

		Result r = new Result();

		Thread t1 = new MyThread(r, "John");
		Thread t2 = new MyThread(r, "BOB");
		Thread t3 = new MyThread(r, "Nil");
		
		t1.start();
		t2.start();
		t3.start();

	}
}
