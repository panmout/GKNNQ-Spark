package gr.uth.ece.dsel.spark.util;

import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public final class PointToTupleCellOneGD implements PairFunction<Point, String, Integer>
{
	private final int N;
	
	public PointToTupleCellOneGD(int n)
	{
		this.N = n;
	}
	
	@Override
	public Tuple2<String, Integer> call(Point p)
	{
		String cell = GnnFunctions.pointToCellGD(p, this.N);
		return new Tuple2<>(cell, 1);
	}
}
