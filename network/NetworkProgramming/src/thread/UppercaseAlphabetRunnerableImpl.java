package thread;

public class UppercaseAlphabetRunnerableImpl extends UppercaseAlphabet implements Runnable {

	@Override
	public void run() {
		print();
	}

}
