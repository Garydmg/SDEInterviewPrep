# SDEInterviewPrep (2020)
This repository is updated frequently to reflect my preparation for software engineering interviews in 2020. 

* [Data Structures](#dataStructures)
* [Algorithms](#algo)
* [Java Language](#java)
* [Object Oriented Design](#ood)
* [System Design](#system)
* [Full-Stack Development](#fullStack)

<a name="dataStructures"></a>
## Data Structures
### Internal Implementations
* [Resizable array](../master/algocode/src/implementation/ArrayList.java)
* Queue and stack ([Circular Array](../master/algocode/src/implementation/ArrayDeque.java) / linked list)
* HashMap (seperate chaining)
* Binary heap 

### Alternative Representations
* Serialize and deserialize trees (Regular / BST)

<a name="algo"></a>
## Algorithms
### Bit Manipulations
#### Building Blocks
```Java
// check k-th bit
(x >> k) & 1

// Set k-th bit to 1
x = x | (1 << k)

// Set k-th bit to 0
x = x & ~(1 << k)
```
#### Practical Usage of XOR
COMING SOON

### Binary Search
#### Classical Binary Search
##### Usage
##### Pitfall

#### Binary Search with Post-Processing
* Find first occurrence
* Find last occurrence 
* Search in shifted sorted array
* Search in bitonic array

#### Binary Search Advanced Applications
* Median of two sorted arrays
* K closest in sorted array
* K closest in two sorted arrays

### Sorting Algorithms
* Selection sort (array / two stacks)
* Merge sort (array / linked list)
* Quick sort

### Pointer and Pivot
* Move 0s to the end (order not maintained / maintained)
* Dutch national flag problem

### Linked List
* Reverse linked list (reverse all / two at a time / k at a time)
* Middle node
* Cycle (cycle existence / cycle position)
* Reorder list
* Partition list
* List palindromicity 
* Merge sorted lists (two lists / k lists)
* Linked list arithmetics (addition / subtraction)

### Stacks and Queues
* Queue by two stacks
* Stack by queues
* Min stack
* Deque by three stacks

### Trees 
* Tree properties (height / balanced / complete / symmetric / is binary search tree)
* Tree iterative traversals (preorder / inorder / postorder)
* Binary search tree basic operations (search / insert / delete)
* Build trees based on traversals
* Least Common Ancestor problems (without parent pointer / with parent pointer)

### Priority Queue and Best First Search
* Kth problem (Kth smallest in unsorted array, Kth closest points to origin in 3D)
* Bipartite graph

### Depth First Search
* Subsets
* Permutations (numbers / valid parentheses)
* Combinations
* Coin change
* N Queens

### Strings
* Top K frequent words
* [Remove Adjacent Repeated Characters](../master/algocode/src/algo/RemoveAdjacentRepeatedCharacters.java)
* Substring problem ([Check Substring Exists](../master/algocode/src/algo/StrStr.java))
* String Manipulation ([Compression / Decompression](../master/algocode/src/algo/StringCompressDecompress.java) / Abbreviation / [Replacement](../master/algocode/src/algo/StringReplace.java))
* [Anagram Problems](../master/algocode/src/algo/Anagrams.java)
* [Longest Substring Problems](../master/algocode/src/algo/LongestSubstring.java)

### Dynamic Programming
* Subarray and subsequence (ascending / sum)
* Cost of cutting rope
* Array hopper (can reach destination / minimum jumps)
* Word break problem
* Edit distance
* [Largest Shape Problem](../master/algocode/src/algo/LargestShape.java)

<a name="java"></a>
## Java Language
* Final, finalize, finally
* Static
* Abstract class vs. interface
* Overload vs. Override
* Comparable and Comparator
* Exception handling
* Concurrency

<a name="ood"></a>
## Object-Oriented Design
COMING SOON

<a name="system"></a>
## System Design
COMING SOON

<a name="fullStack"></a>
## Full-Stack Development
COMING SOON
