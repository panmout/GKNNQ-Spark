# execute (<namenode name> <hdfs dir name> <query dataset> <hdfs GNN dir name> <step> <mindist> <counter_limit> <pointdist>)

hadoop jar ./target/gknn-spark-0.0.1-SNAPSHOT.jar gr.uth.ece.dsel.spark.preliminary.MbrCentroid \
<<<<<<< HEAD
nameNode=panagiotis-lubuntu \
=======
nameNode=Hadoopmaster \
>>>>>>> 850060c91e0c6bcd5b8031644f54d6c0b9caf333
queryDir=input \
queryDataset=linearwaterNNew_sub_2.8M.txt \
gnnDir=gnn \
step=0.00001 \
minDist=10 \
counter_limit=100 \
diff=0.0000001
