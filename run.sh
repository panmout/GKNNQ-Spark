partitioning=gd # gd or qt
method=bf # bf or ps
K=10
N=1000
NameNode=Hadoopmaster
queryDir=input
trainingDir=input
queryDataset=linearwaterNNew_sub_2.8M.txt
trainingDataset=paskrsNNew_obj.txt
gnnDir=gnn
treeDir=sampletree
treeFileName=qtree.ser
phase15=centroid # mbr or centroid
heuristics=true
fastsums=true
systemType=L # 'L' for local or 'D' for distributed system

spark-submit \
--class gr.uth.ece.dsel.spark.main.Main \
./target/gknn-spark-0.0.1-SNAPSHOT.jar \
$partitioning $method $K $N $NameNode $queryDir $queryDataset $trainingDir $trainingDataset $gnnDir $treeDir $treeFileName $phase15 $heuristics $fastsums $partitions $systemType
