package gr.uth.ece.dsel.spark.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.util.LongAccumulator;

public class BfNeighbors2 implements Function2<PriorityQueue<IdDist>, Point, PriorityQueue<IdDist>>
{
	private final int k;
	private final ArrayList<Point> qpoints;
	private final double[] mbrCentroid;
	private final PriorityQueue<IdDist> neighbors;
	private final PriorityQueue<IdDist> oldNeighbors;
	private final boolean fastsums;
	private final LongAccumulator dpc_count;
	
	public BfNeighbors2(int K, double[] mbrC, ArrayList<Point> qp, PriorityQueue<IdDist> pq, boolean fs, LongAccumulator dpc_count)
	{
		this.k = K;
		this.qpoints = new ArrayList<>(qp);
		this.mbrCentroid = Arrays.copyOf(mbrC, mbrC.length);
		this.neighbors = new PriorityQueue<>(pq);
		this.oldNeighbors = new PriorityQueue<>(pq);
		this.fastsums = fs;
		this.dpc_count = dpc_count;
	}
	
	public final PriorityQueue<IdDist> call(PriorityQueue<IdDist> neighbors, Point tpoint)
	{
	    // read centroid coordinates
		final double xc = this.mbrCentroid[4];
		final double yc = this.mbrCentroid[5];
	    // read sumDistCQ
		final double sumDistCQ = this.mbrCentroid[6];
	    
    	final double xt = tpoint.getX();
    	final double yt = tpoint.getY();
    	
    	// if PriorityQueue not full, add new tpoint (IdDist)
    	if (this.neighbors.size() < this.k)
    	{
    		final double sumdist = GnnFunctions.calcSumDistQ(tpoint, this.qpoints, false, 0); // distance calculation
    		final IdDist neighbor = new IdDist(tpoint.getId(), sumdist);
			if (!GnnFunctions.isDuplicate(this.neighbors, neighbor))
				this.neighbors.offer(neighbor); // insert to queue
    	}
    	else // if queue is full, run some checks and replace elements
    	{
    		final double dm = this.neighbors.peek().getDist(); // get (not remove) distance of neighbor with maximum distance
    		final double dpc = GnnFunctions.distance(xt, yt, xc, yc); // tpoint-centroid distance
    		
    		if (!GnnFunctions.heuristic4(this.qpoints.size(), dpc, dm, sumDistCQ)) // if |Q|*dist(p,c) >= MaxHeap.root.dist + dist(centroid, Q) then prune point
	    		//increment DPC_COUNT metrics variable
    			this.dpc_count.add(1);
    		else
  		  	{
    			final double sumdist = GnnFunctions.calcSumDistQ(tpoint, this.qpoints, this.fastsums, dm); // distance calculation
  				if (sumdist < dm) // compare distance
  				{
  					final IdDist neighbor = new IdDist(tpoint.getId(), sumdist);
  					if (!GnnFunctions.isDuplicate(this.neighbors, neighbor))
  					{
  						this.neighbors.poll(); // remove top element
  						this.neighbors.offer(neighbor); // insert to queue
  					}
  				} // end if
  			} // end if
    	} // end else
	    
	    if (this.oldNeighbors.isEmpty()) // phase 2
	    	return this.neighbors;
	    else // phase 3, remove elements from phase 2
	    	return GnnFunctions.pqDifference(this.neighbors, this.oldNeighbors);
    }
} // end gdBfNeighbors
