package de.edu.forkjoinpool;

import de.edu.forkjoinpool.prime.PrimeTask;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

public class Main {

	public static void main(String[] args) {
/*
		long from = (long) Math.pow(10, 6);
		long to = (long) Math.pow(10, 6) + 10_000;
*/
		final PrimeTask task = new PrimeTask(1, 100_000, 1000);

		ForkJoinPool.commonPool().execute(task);

		final Collection<Long> result = task.join();
		System.out.println(result);
	}
}
