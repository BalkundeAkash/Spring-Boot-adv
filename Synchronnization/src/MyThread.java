
public class MyThread extends Thread {

	private Result result;
	private String name;

	public MyThread(Result result, String name) {
		super();
		this.result = result;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		result.printTopperName(name);
	}

}
