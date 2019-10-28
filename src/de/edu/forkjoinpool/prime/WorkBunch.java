package de.edu.forkjoinpool.prime;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.LongStream;

public class WorkBunch {

	private final long from, to;

	public WorkBunch(final long from, final long to) {
		this.from = from;
		this.to = to;
	}

	public Collection<WorkBunch> split() {
		final long middle = (from + to) / 2;
		return Arrays.asList(new WorkBunch(from, middle), new WorkBunch(middle + 1, this.to));
	}

	public LongStream getStream() {
		return LongStream.rangeClosed(from, to);
	}

	public boolean isLess(final int threshold) {
		return to - from <= threshold;
	}

	@Override
	public String toString() {
		return from + "-" + to;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final WorkBunch bunch = (WorkBunch) o;

		if (from != bunch.from) return false;
		return to == bunch.to;
	}

	@Override
	public int hashCode() {
		int result = (int) (from ^ (from >>> 32));
		result = 31 * result + (int) (to ^ (to >>> 32));
		return result;
	}
}
