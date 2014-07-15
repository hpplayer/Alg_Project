import java.util.ArrayList;


public class Dijstra_with_heap {
	private int BW = 0;
	private int sourceNumber; //range 0 to 5000
	private int DestiNumber;//range 0 to 5000
	private Heap heap;
	private Node[] nodelist;
	
	public Dijstra_with_heap(int a,int b,Node[] c){//a = sourceNumber, b= DestiNumber, c= nodelist1
		nodelist = new Node[5000];
		nodelist = c;
		heap = new Heap(); 
		nodelist[a].BW = 100000000;//
		sourceNumber = a; //range 0 to 5000
		DestiNumber = b;//range 0 to 5000
	}
	
	public void NEWdijstra(){
		int nodeNum;
		Edge temp;
		Node sourceNode = nodelist[sourceNumber];
		//long startTime = System.nanoTime();
			for (int i = 0; i < sourceNode.NumOfedges; i++){
				temp = sourceNode.edges.get(i);
				nodeNum = temp.endPointW();
				nodelist[nodeNum].Dad = sourceNode;
				nodelist[nodeNum].BW = temp.weight();

				heap.insertBW(nodeNum, nodelist[nodeNum].BW);//store i value and BW value
			}

			while(heap.size() != 0){

				int valueOfh_i = (Integer) heap.max();
	
				Node Maxfringe = nodelist[valueOfh_i];
				heap.removeMax();

				for ( int j = 0; j <Maxfringe.NumOfedges; j++){
					int tempNodeNum = Maxfringe.edges.get(j).endPointW();
						if (nodelist[tempNodeNum].BW == -1){//when node is unseen
							
							nodelist[tempNodeNum].Dad = Maxfringe;
							nodelist[tempNodeNum].BW = returnMin(Maxfringe.BW, Maxfringe.edges.get(j).weight());

							heap.insertBW(tempNodeNum, nodelist[tempNodeNum].BW);

						}//end case1 if
						else if (heap.contains(tempNodeNum) && nodelist[tempNodeNum].BW < returnMin(Maxfringe.BW, Maxfringe.edges.get(j).weight())){
	
							 nodelist[tempNodeNum].Dad = Maxfringe;
							 nodelist[tempNodeNum].BW = returnMin(Maxfringe.BW, Maxfringe.edges.get(j).weight());
	
							 heap.update(tempNodeNum, nodelist[tempNodeNum].BW);
							
						}//end case2 if 
				}//end for loop
	
					if ((int)heap.D.get(DestiNumber) != -1 && !heap.contains(DestiNumber)){
					break; 
				}
			
		}//end while loop
			System.out.println("Done Dijstra algorithm with heap on the calculation from source node " + sourceNumber + " to destination node " + DestiNumber + " !");
			}
			


public int returnMin(int a, int b){
	if (compare(a, b) <= 0)
		return a;
	else 
		return b;
}

public int compare(Object x, Object y){
	return ((Integer) x).compareTo((Integer) y);
}

public int calpath(){
	
	Node temp = nodelist[DestiNumber];
	int totallength = temp.BW;;
	int numofnodes = 0;
	
	while(temp.name != sourceNumber){
		if (totallength > temp.BW){
			totallength = temp.BW;}
		temp = temp.Dad;	
		numofnodes++;
	}
	System.out.println("The Bandwidth is " + totallength + ", through " + numofnodes + " nodes" );
	return totallength;
}
	
}
