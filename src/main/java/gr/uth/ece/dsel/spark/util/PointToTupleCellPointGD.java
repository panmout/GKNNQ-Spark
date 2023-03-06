package gr.uth.ece.dsel.spark.util;

import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public final class PointToTupleCellPointGD implements PairFunction<Point, String, Point>
{
	private final int n;
	
	public PointToTupleCellPointGD (int n)
	{
		this.n = n;
	}
	
	@Override
	public Tuple2<String, Point> call(Point p)
	{
		final String cell = GnnFunctions.pointToCellGD(p, n);
		return new Tuple2<>(cell, p);
	}
}
