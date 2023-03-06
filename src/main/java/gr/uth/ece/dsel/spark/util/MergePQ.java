package gr.uth.ece.dsel.spark.util;

import java.util.PriorityQueue;
import org.apache.spark.api.java.function.Function2;

public final class MergePQ implements Function2<PriorityQueue<IdDist>, PriorityQueue<IdDist>, PriorityQueue<IdDist>>
{
	private final int k;
	
	public MergePQ(int k)
	{
		this.k = k;
	}
	
	@Override
	public PriorityQueue<IdDist> call(PriorityQueue<IdDist> pq1, PriorityQueue<IdDist> pq2)
	{
		return GnnFunctions.joinPQ(pq1, pq2, k);
	}
}
