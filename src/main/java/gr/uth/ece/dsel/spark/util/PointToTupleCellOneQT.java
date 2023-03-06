package gr.uth.ece.dsel.spark.util;

import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public final class PointToTupleCellOneQT implements PairFunction<Point, String, Integer>
{
	private final Node node;
	
	public PointToTupleCellOneQT(Node node)
	{
		this.node = node;
	}
	
	@Override
	public Tuple2<String, Integer> call(Point p)
	{
		String cell = GnnFunctions.pointToCellQT(p.getX(), p.getY(), this.node);
		return new Tuple2<>(cell, 1);
	}
}
