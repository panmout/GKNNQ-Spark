package gr.uth.ece.dsel.spark.util;

import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public final class PointToTupleCellPointQT implements PairFunction<Point, String, Point>
{
	private Node node;
	
	public PointToTupleCellPointQT (Node node)
	{
		this.node = node;
	}
	
	@Override
	public final Tuple2<String, Point> call(Point p)
	{
		final String cell = GnnFunctions.pointToCellQT(p.getX(), p.getY(), this.node);
		return new Tuple2<String, Point>(cell, p);
	}
}
