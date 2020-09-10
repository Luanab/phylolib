# phylolib

PhyloLib is a library of efficient algorithms for phylogenetic analysis in the form of a command line application. It was developed in the scope of a master thesis that was divided into two phases. The first phase was the project composed by a [report](https://www.overleaf.com/read/dxpfjfwtfdcs) and a [presentation](https://docs.google.com/presentation/d/1x_T11wbP_nEoqif2Tt05Od9tPjfYgre55OPe69C3I7k/). And the second phase was the dissertation composed by a [report](https://www.overleaf.com/read/fyzzymzqmprz), an [extended abstract]() and a [presentation](https://docs.google.com/presentation/d/1qPudTnvzP8hGGGDKaR8n9iOIMUOoeEIu2nGxD7D9tUE/).

### Usage

To execute a command of this command line application you should type the name of the library followed by the command name, respective type and options. The usage of this command line application can be retrieved by running the command ```phylolib help``` and looks like the following:

```
Usage:
	phylolib help
	phylolib distance (hamming|grapetree|kimura) [options]
	phylolib correction (jukescantor) [options]
	phylolib algorithm (goeburst|edmonds|sl|cl|upgma|upgmc|wpgma|wpgmc|saitounei|studierkepler|unj) [options]
	phylolib optimization (lbr) [options]

Options:
	-o=<file>	--out=<file>		Output file as <format>:<location> with format being (asymmetric|symmetric|newick|nexus)
	-d=<file>	--dataset=<file>	Input dataset file as <format>:<location> with format being (fasta|ml|snp)
	-m=<file>	--matrix=<file>		Input distance matrix file as <format>:<location> with format being (asymmetric|symmetric)
	-t=<file>	--tree=<file>		Input phylogenetic tree file as <format>:<location> with format being (newick|nexus)
	-l=<number>	--lvs=<number>		Limit of locus variants to consider using goeBURST algorithm [default: 3]
```

You can also run multiple commands by concatenating them with a "|" character like this:

```
phylolib algorithm upgma --out=newick:C:\Desktop\tree.txt | distance hamming --dataset=ml:C:\Desktop\data.txt
```

The order in which the commands are executed is dictated by the phylogenetic analysis workflow, making the order in which the commands are provided indifferent.
For example, in the execution above, the order in which the commands would be executed would be distance and then algorithm and not algorithm and then distance.

### JAR

To compile this project into a JAR and execute it, you should:
1. Have Gradle and Java JDK13 or higher installed.
2. Open the terminal in the project's folder.
3. Clean the project by running the command ```gradle clean```.
4. Build the JAR by running the command ```gradle jar```.
5. Open the terminal in the folder *build/libs* of the project.
6. Execute the JAR by running the command ```java -jar PhyloLib-1.0-SNAPSHOT.jar help```.
