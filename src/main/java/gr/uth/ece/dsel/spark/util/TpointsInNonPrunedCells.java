package gr.uth.ece.dsel.spark.util;

import java.util.HashSet;
import org.apache.spark.api.java.function.Function;

import scala.Tuple2;

public final class TpointsInNonPrunedCells implements Function<Tuple2<String, Point>, Boolean>
{
	private HashSet<String> npCells;
	
	public TpointsInNonPrunedCells(HashSet<String> cells)
	{
		this.npCells = cells;
	}
	
	@Override
	public final Boolean call(Tuple2<String, Point> tuple)
	{
		return this.npCells.contains(tuple._1);
	}
}