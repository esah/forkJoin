package de.edu.forkjoinpool.prime;

import java.util.function.LongPredicate;

public class PrimePredicate implements LongPredicate {

	@Override
	public boolean test(final long value) {
		if (value <= 1) {
			return false;
		}

		for (long factor = value - 1; factor > 1; factor--) {
			if (value % factor == 0) {
				return false;
			}
		}
		return true;
	}
}
