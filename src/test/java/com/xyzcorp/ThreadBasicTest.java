package com.xyzcorp;

import org.junit.Test;

public class ThreadBasicTest {

	@Test
	public void testBasicThread() throws InterruptedException {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hey I am in a Thread");
			    System.out.println(Thread.currentThread().getName());
			}
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		Thread.sleep(3000);
	}
}
