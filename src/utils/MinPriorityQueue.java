package utils;

import java.util.ArrayList;

public class MinPriorityQueue<T extends Comparable<T>> {
	
	// MinPriorityQueue field
	private ArrayList<T> heap;

    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue() {
    	heap = new ArrayList<T>(); 
    	heap.add(null); 	// fill pos 0 with null
	}

    /**
     * Returns the number of elements currently in the queue.
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {
    	heap.add(elem);
    	int currentIndex = heap.size()-1;
    	int parIndex;
    	
    	// set parent/child index if at tree root
    	if(currentIndex == 1){
    		parIndex = currentIndex;
    	} else {
    		parIndex = currentIndex/2;
    	}
    	
    	// move the element up the tree to maintain binary heap structure
    	while(currentIndex > 1 && heap.get(parIndex).compareTo(heap.get(currentIndex)) == 1){
    		T parent = heap.get(parIndex);
    		heap.set(parIndex, heap.get(currentIndex));
    		heap.set(currentIndex, parent);
    		currentIndex = parIndex;
    		parIndex = currentIndex/2;
    	}
    }
    
    /**
     * Removes, and returns, the element at the front of the queue.
     */
    public T remove() {
    	T nextEvent = heap.get(1);				// store the next event
    	
    	heap.set(1, heap.get(heap.size()-1));	// copy the rightmost leaf to root
    	
    	heap.remove(heap.size()-1);				// remove rightmost leaf from leaf pos. 	
    	reorderTree(1);							// reorder tree from top down
		return nextEvent;						// return highest priority event
    }
    
    private void reorderTree(int positionOfMovingNode){
    	// define the index of the parent and two children
    	int left = positionOfMovingNode * 2;
    	int right = positionOfMovingNode * 2 + 1;
    	int lowestPriorityIndex = positionOfMovingNode;
    	
    	T movingNode = heap.get(positionOfMovingNode);
    	
    	// initialise lowest priority node to moving node
    	T lowestPriority = heap.get(positionOfMovingNode);
    	
    	// find lowest priority node between three nodes
    	if (left < heap.size()) {
    		T leftChild = heap.get(left);
    		if (leftChild.compareTo(movingNode) == -1) {
    			lowestPriorityIndex = left;
    			lowestPriority = heap.get(lowestPriorityIndex);
    		}
    	}
    	if (right < heap.size()) {
    		T rightChild = heap.get(right);
    		if (rightChild.compareTo(lowestPriority) == -1) {
    			lowestPriorityIndex = right;
    			lowestPriority = heap.get(lowestPriorityIndex);
    		}
    	}
    	
    	// if lowest priority node is a child, swap the nodes and recurse with
    	// new moving node 
    	if(lowestPriority != movingNode){
    		T store = movingNode;
    		heap.set(positionOfMovingNode, lowestPriority);
    		heap.set(lowestPriorityIndex, store);
    		reorderTree(lowestPriorityIndex);
    	}
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() <= 1;
    }
}
