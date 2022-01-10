package gr.uth.ece.dsel.spark.util;

import java.util.Iterator;
import org.apache.spark.api.java.function.Function;

public final class IterablePointsCount implements Function<Iterable<Point>, Integer>
{
	@Override
	public final Integer call(Iterable<Point> it)
	{
		Iterator<Point> iterator = it.iterator();
		
		int counter = 0;
		
		while (iterator.hasNext())
		{
			iterator.next();
			counter++;
		}
		
		return counter;
	}

}
