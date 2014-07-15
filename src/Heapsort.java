
import java.util.*;



public class Heapsort {
	int last;//range 0 to 4999

	HeapNode lastNode;
	
	List<Edge> H;//one value = one node
	

	
	public Heapsort(){
	H = new ArrayList<Edge>();
	last = 0;

	}
	

	
	public int size(){
		return last;
	}
	
	/**
	 *  Insert an Interger into the heap
	 *
	 *  
	 **/
	public Edge removeMax() {

			Edge max =  max();
			H.set(0, H.get(size()-1));
			H.remove(size() -1);
			last --;
			bubbledown();
			return max;

	}
		
	
	public void insert(Edge e){

			last ++;
			H.add(e);
			bubbleup();
			//System.out.println("added:" + H.get(last-1));
	
	}
	

	private void bubbleup(){
		double index2 = size() - 1;
		//System.out.print(index + "" + " index: " + index2);
		while((int) index2 > 0){
			int parentIndex = (int) (Math.ceil(index2 /2) - 1);
			//System.out.println("parentindex: " + parentIndex + "index: " + index2);

			if (compare(H.get((int) index2).weight(), H.get(parentIndex).weight()) > 0 ){ 
			swap((int) index2, parentIndex);
			index2 = parentIndex;
		

			}
			else if (compare(H.get((int) index2).weight(), H.get(parentIndex).weight()) <= 0 ){
				break;//break as long as the last node is okay since we only add at the end
				
			}

		}//end while loop
	}
	
	public void bubbledown(){
		int index = 1;
		while (true){
			int child = index*2;
			if (child > size())
				break;
			if(child + 1 <= size()){
				child = findMax(child, child+1);
			}
			if (compare(H.get(index-1).weight(), H.get(child -1).weight()) > 0)
				break;
			else if (compare(H.get(index-1).weight(), H.get(child -1).weight()) <= 0){
				swap(index-1, child-1);
				index = child;
			}
		}
			
			

	}
	
	
	private int findMax(int leftChild, int rightChild){
		if (compare(H.get(leftChild-1).weight(), H.get(rightChild-1).weight()) <= 0)
			return rightChild;
		else
			return leftChild;
	}
	
	
	
	private int compare(Object x, Object y){
		return ((Integer) x).compareTo((Integer) y);
	}

	
	/**
     * Swaps two integers i and j
     * @param i
     * @param j
     */

	private void swap(int i, int j){
		Edge temp = H.get(i);
		H.set(i, H.get(j));
		H.set(j, temp);

		
	}



	public Edge max() {
	
			return H.get(0);
		
	}
	
    public String toString() {
        String s = "[";
        for (int i = 0; i < size() ; i++) {
            s += H.get(i);
            if (i != last-1)
                s += ",";
        }
        
        s = s + "]";
        
        System.out.println(s);
        
        return s;
    }
}
	