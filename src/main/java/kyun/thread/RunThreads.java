package kyun.thread;

public class RunThreads {

	public static void main(String[] args) {
		RunThreads threads = new RunThreads();
		threads.runBasic();
//		threads.runBasic2();
	}
	
	public void runBasic() {
		RunnableSample runnable = new RunnableSample();
		ThreadSample thread = new ThreadSample();
		
		new Thread(runnable).start();
		thread.start();
		
		System.out.println("RunThreads.runBasic() method is ended.");
	}
	
	public void runBasic2() {
		RunnableSample[] runnable = new RunnableSample[5];
		ThreadSample[] thread = new ThreadSample[5];
		for (int loop = 0; loop < 5; loop++) {
			runnable[loop] = new RunnableSample();
			thread[loop] = new ThreadSample();
			
			new Thread(runnable[loop]).start();
			thread[loop].start();
		}
		
		System.out.println("RunThreads.runBasic2() method is ended.");
	}
	
	public void endless() {
		EndlessThread thread = new EndlessThread();
		thread.start();
	}
	
	public void checkThreadProperty() {
		ThreadSample thread1 = new ThreadSample();
		ThreadSample thread2 = new ThreadSample();
		ThreadSample daemonThread = new ThreadSample();
		
		System.out.println("thread1 id = " + thread1.getId());
		System.out.println("thread2 id = " + thread2.getId());
		
		System.out.println("thread1 name = " + thread1.getName());
		System.out.println("thread2 name = " + thread2.getName());
		
		System.out.println("thread1 priority = " + thread1.getPriority());
		
		daemonThread.setDaemon(true);
		System.out.println("thread1 isDaemon = " + thread1.isDaemon());
		System.out.println("daemonThread isDaemon = " + daemonThread.isDaemon());
	}
	
	public void runDaemonThread() {
		DaemonThread thread = new DaemonThread();
		thread.setDaemon(true);
		thread.start();
	}
	
	public void checkThreadStat1() {
		SleepThread thread = new SleepThread(2000);
		try {
			System.out.println("thread satat = " + thread.getState());
			thread.start();
			System.out.println("thread satat(after start) = " + thread.getState());
			
			Thread.sleep(1000);
			System.out.println("thread satat(after 1sec) = " + thread.getState());
			
			thread.join();
			thread.interrupt();
			System.out.println("thread satat(after join) = " + thread.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void infinite() {
		InfiniteThread thread = new InfiniteThread();
		thread.start();
		try {
			Thread.sleep(500);
			thread.interrupt();
			System.out.println("interrupt() called ");
			thread.join(500);
		} catch (InterruptedException e) {
			e.toString();
		}
		System.out.println("isAlive = " + thread.isAlive());
		System.out.println("isInterrupted = " + thread.isInterrupted());
	}
	
	public void checkThreadState2() {
		Object monitor = new Object();
		StateThread thread = new StateThread(monitor);
		try {
			System.out.println("thread state=" + thread.getState());
			thread.start();
			System.out.println("thread state(after start)=" + thread.getState());
			Thread.sleep(100);
			System.out.println("thread state(after 0.1 sec)=" + thread.getState());
			synchronized (monitor) {
				monitor.notify();
			}
			Thread.sleep(100);
			System.out.println("thread state(after notify)=" + thread.getState());
			thread.join();
			System.out.println("thread state(after join)=" + thread.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void checkThreadState3() {
		Object monitor = new Object();
		StateThread thread = new StateThread(monitor);
		StateThread thread2 = new StateThread(monitor);
		try {
			System.out.println("thread state=" + thread.getState());
			thread.start();
			thread2.start();
			System.out.println("thread state(after start)=" + thread.getState());
			Thread.sleep(100);
			System.out.println("thread state(after 0.1 sec)=" + thread.getState());
			synchronized (monitor) {
//				monitor.notify();
//				monitor.notify();
				monitor.notifyAll();
			}
			Thread.sleep(100);
			System.out.println("thread state(after notify)=" + thread.getState());
			thread.join();
			System.out.println("thread state(after join)=" + thread.getState());
			thread2.join();
			System.out.println("thread2 state(after join)=" + thread2.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
