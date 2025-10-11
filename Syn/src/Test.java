
public class Test {

	public static void main(String[] args) {
		Result result = new Result();
		
		Thread t1 = new MyThread(result, "john");
		Thread t2 = new MyThread(result, "bob");
		Thread t3 = new MyThread(result, "jn");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
