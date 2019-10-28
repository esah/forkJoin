package de.edu.forkjoinpool.prime;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class PrimeTask extends RecursiveTask<Collection<Long>> {
	private final int threshold;
	private final WorkBunch bunch;
	private static final PrimePredicate primePredicate = new PrimePredicate();

	public PrimeTask(final WorkBunch bunch, final int threshold) {
		this.bunch = bunch;
		this.threshold = threshold;
	}

	public PrimeTask(final long from, final long to, final int threshold) {
		this.threshold = threshold;
		this.bunch = new WorkBunch(from, to);
	}

	@Override
	protected Collection<Long> compute() {
		System.out.println(Thread.currentThread().getName() + ": " + bunch);
		if (bunch.isLess(threshold)) {
			return process();
		}
		return forkAndJoin();
	}

	private Collection<Long> forkAndJoin() {
		return ForkJoinTask.invokeAll(divide()).stream().
				map(t -> t.join()).reduce((o, o2) -> {
			o.addAll(o2);
			return o;
		}).orElse(Collections.emptyList());
	}

	private Collection<PrimeTask> divide() {
		return bunch.split().stream().map(b -> new PrimeTask(b, threshold)).collect(Collectors.toList());
	}

	private Collection<Long> process() {
		return bunch.getStream().filter(primePredicate).
				mapToObj(Long::new).collect(Collectors.toList());
	}

}
