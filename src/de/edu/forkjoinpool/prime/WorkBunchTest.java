package de.edu.forkjoinpool.prime;

import org.junit.Assert;
import org.junit.Test;

public class WorkBunchTest {

	@Test
	public void split() {
		Assert.assertArrayEquals(new WorkBunch[]{new WorkBunch(1, 5), new WorkBunch(6, 10)},
				new WorkBunch(1, 10).split().toArray());

		Assert.assertArrayEquals(new WorkBunch[]{new WorkBunch(50, 75), new WorkBunch(76, 100)},
				new WorkBunch(50, 100).split().toArray());
	}


}
