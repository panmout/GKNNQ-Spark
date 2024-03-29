package gr.uth.ece.dsel.spark.util;

// class Point (int id, double x, double y) with constructor and set-get methods

import java.io.Serializable;

public final class Point implements Serializable
{
	private int id;
	private double x;
	private double y;
	
	public Point(int id1, double x1, double y1)
	{
		setId(id1);
		setX(x1);
		setY(y1);
	}
	
	public void setId(int id1)
	{
		this.id = id1;
	}
	
	public void setX(double x1)
	{
		this.x = x1;
	}
	
	public void setY(double y1)
	{
		this.y = y1;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
}
