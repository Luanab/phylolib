# phylolib

PhyloLib is a library of efficient algorithms for phylogenetic analysis in the form of a command line application. It was developed in the scope of a master thesis that was divided into two phases. The first phase was the project composed by a [report](https://www.overleaf.com/read/dxpfjfwtfdcs) and a [presentation](https://docs.google.com/presentation/d/1x_T11wbP_nEoqif2Tt05Od9tPjfYgre55OPe69C3I7k/edit?usp=sharing). And the second phase was the dissertation composed by a [report](http://arxiv.org/abs/2012.12697), an [article](https://www.overleaf.com/read/kmjyztpsknbp) with [supplementary data](https://www.overleaf.com/read/dkqtxfqyjqvm), a [presentation](https://docs.google.com/presentation/d/1qPudTnvzP8hGGGDKaR8n9iOIMUOoeEIu2nGxD7D9tUE/edit?usp=sharing), a [documentation](https://luanab.github.io/phylolib/index.html), and a [video](https://youtu.be/hr0iBjTeV1U) explaining how to deploy the application in a Docker image and run it. This second phase was accomplished bearing in mind an agile approach using [GitHub's project functionality](https://github.com/Luanab/phylolib/projects/1).

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

You can also run multiple commands by concatenating them with a ":" character like this:

```
phylolib algorithm upgma --out=newick:tree.txt : distance hamming --dataset=ml:dataset.txt
```

The order in which the commands are executed is dictated by the phylogenetic analysis workflow, making the order in which the commands are provided indifferent. Except for commands of the same type, that is, that can be executed multiple times, as is the case of the optimization command, in which case the order of execution between them will be dictated by the order in which they are provided.
For example, in the execution above, the order in which the commands would be executed would be distance and then algorithm and not algorithm and then distance.

### JAR

To compile this project into a JAR and execute it, you should:
1. Install Gradle and Java JDK13 or higher.
2. Open the terminal in the project's folder.
3. Run the command ```gradle clean``` to clean the project.
4. Run the command ```gradle jar``` to build the JAR.
5. Open the terminal in the folder *build/libs* of the project.
6. Run the command ```java -jar PhyloLib-1.0-SNAPSHOT.jar help``` to execute the JAR.

### Docker

To build a Docker image for this project and execute it, you should:
1. Install Docker and compile the JAR of this project.
2. Open the terminal in the project's folder.
3. Run the command ```docker build -t phylolib .``` to build the Docker image.
4. Run the command ```docker run --rm -v $HOME/<DIRECTORY>/files:/files -v $HOME/<DIRECTORY>/logs:/logs phylolib:latest help``` to execute the Docker image.
