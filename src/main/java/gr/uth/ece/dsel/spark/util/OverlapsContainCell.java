package gr.uth.ece.dsel.spark.util;

import java.util.HashSet;
import org.apache.spark.api.java.function.Function;
import scala.Tuple2;

public final class OverlapsContainCell implements Function<Tuple2<String, Point>, Boolean>
{
	private HashSet<String> overlaps;
	
	public OverlapsContainCell(HashSet<String> overlaps)
	{
		this.overlaps = overlaps;
	}
	
	@Override
	public final Boolean call(Tuple2<String, Point> tuple)
	{
		return this.overlaps.contains(tuple._1);
	}
}