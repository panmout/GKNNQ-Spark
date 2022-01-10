# execute (<namenode name> <hdfs dir name> <query dataset> <hdfs GNN dir name> <step> <mindist> <counter_limit> <pointdist>)

hadoop jar ./target/spark-0.0.1-SNAPSHOT.jar gr.uth.ece.dsel.spark.preliminary.MbrCentroid \
nameNode=panagiotis-lubuntu \
queryDir=input \
queryDataset=query-dataset.txt \
gnnDir=gnn \
step=0.00001 \
minDist=10 \
counter_limit=100 \
diff=0.0000001
