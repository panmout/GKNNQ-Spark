# GKNNQ-Spark

## Spark implementation of a parallel and distributed algorithm for efficient solving of the Group K Nearest Neighbor query involving Big Data

## Code used in paper: https://doi.org/10.3390/ijgi10110763

### Description
The algorithm needs two user provided spatial datasets of point objects in the form {int, double, double} with their coordinates normalized in the area (0,1).
The first dataset is called *query* and the second dataset is called *training*. The *query* must be small enough to fit in the memory of a single machine, while the *training* can belong in the Big Data category.

The algorithm uses two partitioning methods (*grid* and *quad tree*), two computational methods (*brute force* and *plane sweep*) and two refining methods (*MBR* and *centroid*). It makes extensive use of selected pruning heuristics from the literature for fast pruning of distant cells.
The ability to switch on or off *pruning heuristics* and *fast sums* computational method, for testing purposes, is also provided.

The algorithm consists of three distributed phases and four local phases:
1. Preliminary (local): Local calculations needed by subsequent phases.
2. Phase 1 (distributed): Count the number of *training* points in every cell.
3. Phase 1.5 (local): Discovery of a group of cells that contain at least *K* *training* points in total, using *MBR* or *centroid* refining methods.
4. Phase 2 (distributed): Create a preliminary list of *K* nearest neighbors per cell, within the group of cells from Phase 1.5.
5. Phase 2.5 (local): Merge all lists from Phase 2 into one.
6. Phase 3 (distributed): Try to discover neighbors in distant cells, not in Phase 1.5 group. Pruning heuristics applied.
7. Phase 3.5 (local): Merge all neighbor lists from Phases 2.5 and 3 into the final one.

### How to run
First of all, user must create the quad tree, if quad tree partitioning is selected, by running the appropriate script file (see next section).

If *plane sweep* method is selected, then the script file *SortQueryPoints.sh* must be edited and executed, to sort the *query* dataset and store it in HDFS.

The algorithm also needs to find the boundaries of the *query* dataset MBR and the coordinates of its centroid. Edit and run the *mbrCentroid.sh* script file.

Finally, user must edit script file *run.sh* and provide the appropriate parameters:
- partitioning: *gd* or *qt* for *grid* or *quad tree* partitioning, respectively
- method: *bf* or *ps* for *brute force* or *plane sweep* computational methods, respectively
- K: the desired number of neighbors
- N: the grid space decomposition parameter, it creates N\*N equal sized square cells
- NameNode: the name of the machine used as Namenode of the Hadoop cluster
- queryDir: the HDFS directory containing the *query* dataset
- trainingDir: the HDFS directory containing the *training* dataset
- queryDataset: the file name of the *query* dataset
- trainingDataset: the file name of the *training* dataset
- gnnDir: the HDFS directory containing files *mbrCentroidFile*, *overlapsFile* and *gnn25File*
- treeDir: the HDFS directory containing the *treeFile*
- treeFileName: the file name of the quad tree binary file, created by *createQTree.sh* or *createQTreeArray.sh* scripts
- phase15: *MBR* or *centroid*, selects the desired refining method
- heuristics: *true* or *false* to turn the pruning heuristics on or off
- fastSums: *true* or *false* to turn the fast sums computational method on or off
- systemType: *L* for local and *D* for distributed system

After that, execute *run.sh*

### How to create a quad tree binary file
There are two different script files, *createQTree.sh* and *createQTreeArray.sh* that create quad tree files using different methods. The first one is recommended and activated by default.
User must edit script file *createQTree.sh* and provide the appropriate parameters:
- nameNode: (same as *run.sh*)
- trainingDir: (same as *run.sh*)
- treeDir: (same as *run.sh*)
- trainingDataset: (same as *run.sh*)
- samplerate: desired sample rate of the *training* dataset. Give an integer between 1 - 100
- capacity: the maximum desired number of *training* points in each cell
- type: *1* (recommended) for simple capacity based quadtree, *2* for all children split method, *3* for average width method

After that, run the script file and a copy of the created quad tree, as *qtree.ser*, will be stored both locally and in the appropriate HDFS directory.

### Delete MapReduce output HDFS directories
Run the *delete-hdfs-dirs.sh* script file

### Delete local files
To delete all locally created files, such as preliminary and intermediate phases output, run *delete-local-files.sh* script file
