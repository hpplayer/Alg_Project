import java.awt.List;
import java.util.ArrayList;
import java.util.Hashtable;


public class Kruskal {
	private int BW = 0;
	private int sourceNumber; //range 0 to 4999
	private int DestiNumber;//range 0 to 4999
	private Heapsort heap;
	private Node[] nodelist;
	private int[] Dadlist;
	private int[] ranklist;
	private ArrayList edgelist;
	public ArrayList T;

	public Kruskal(int a, int b, Node[] c){
		nodelist = new Node[5000];
		heap = new Heapsort(); 
		sourceNumber = a; //range 0 to 5000
		DestiNumber = b;//range 0 to 5000
		nodelist = c;
		nodelist[sourceNumber].BW = 1000000;
		
	}
	

	
	private void  makeset(){
		Dadlist = new int[5000];
		ranklist = new int[5000];
		for (int i = 0; i < 5000; i++){
			Dadlist[i] = 0;
			ranklist[i] = 0;
		}

	}
	
	private int find(int v){
		int w = v;
		int[] s = new int[5000];
		int i = 0;
		int sizeOfs = 0;
		while (Dadlist[w] != 0){
			s[i] = w;
			w=Dadlist[w];
			i++;
			sizeOfs++;
		}
		int j = 0;
		int u = 0;
		while (sizeOfs != 0){
			u = s[j];
			Dadlist[u] = w;
			sizeOfs--;
			j++;
		}

		return w;
	}

	private void union(int r1, int r2){
		if (ranklist[r1] > ranklist[r2])
				Dadlist[r2] = r1;
		else if (ranklist[r1] < ranklist[r2])
				Dadlist[r1] = r2;
		else if (ranklist[r1] == ranklist[r2]){
			ranklist[r1] = ranklist[r1] + 1;
			Dadlist[r2] = r1;
		}

	}
	

	public ArrayList kruskal(){
		long startTime = System.nanoTime();
		for (int i = 0; i < 5000; i ++){
			for(int j = 0; j < nodelist[i].NumOfedges; j++){
	
				if(nodelist[i].edges.get(j).endPointV() <= nodelist[i].edges.get(j).endPointW()){
					Edge edge = nodelist[i].edges.get(j);	
					heap.insert(edge);

				}//insert edges only if v < w
			}//end inner for loop 
			
		}//end outer for loop
		
		 
		T = new ArrayList();
		makeset();

		while(heap.size() >0 ){

			Edge temp = heap.removeMax();

			int v = temp.endPointV();
			int w = temp.endPointW();
			int r1 = find(v);
			int r2 = find(w);
			if ( r1 != r2){
				//T[i] = temp;
				T.add(temp);
				//Numofedge ++;
				union(r1,r2);
			}//end if
		}//end for loop
		 long endTime = System.nanoTime();
		 long duration = endTime - startTime;
	  System.out.println("heap sort " + duration/1000000.0 + " ms");
			

			return T;
			
	}//end Kruskal algorithm
	
	
	public void update(){

		
		edgelist = kruskal();
		System.out.println("Done Kruskal algorithm on the calculation from source node " + sourceNumber + " to destination node " + DestiNumber + " !");
		
		for ( int j = 0; j < 5000; j++){
			nodelist[j].edges = new ArrayList<Edge>();
			nodelist[j].NumOfedges = nodelist[j].edges.size();
	
		}
	

		for (int i = 0; i < edgelist.size(); i++){

			Edge temp = (Edge) edgelist.get(i);

			int V = temp.endPointV();

			nodelist[V].edges.add(temp);
			nodelist[V].NumOfedges = nodelist[V].edges.size();

			int W = temp.endPointW();

			nodelist[W].edges.add(temp);
			nodelist[W].NumOfedges = nodelist[W].edges.size();

		}

	}
	
	public void update2(ArrayList edgelist){

		
		System.out.println("Done Kruskal algorithm on the calculation from source node " + sourceNumber + " to destination node " + DestiNumber + " !");

		for ( int j = 0; j < 5000; j++){
			nodelist[j].edges = new ArrayList<Edge>();
			nodelist[j].NumOfedges = nodelist[j].edges.size();
	
		}
	

		for (int i = 0; i < edgelist.size(); i++){

			Edge temp = (Edge) edgelist.get(i);

			int V = temp.endPointV();

			nodelist[V].edges.add(temp);
			nodelist[V].NumOfedges = nodelist[V].edges.size();

			int W = temp.endPointW();
	
			nodelist[W].edges.add(temp);
			nodelist[W].NumOfedges = nodelist[W].edges.size();

		}
		
	}
	
	public void BFS(){
		ArrayList Q = new ArrayList();
		int[] Marklist = new int[5000];//-1 visted
		
		Q.add(sourceNumber);
	
		
		Marklist[sourceNumber] = -1;

		Node sourceNode = nodelist[sourceNumber];

		for (int i = 0; i < sourceNode.NumOfedges; i++){
			Edge temp3 = sourceNode.edges.get(i);
			int nodeNum = temp3.endPointW();
			Marklist[nodeNum] = -1;
			nodelist[nodeNum].Dad = sourceNode;
			nodelist[nodeNum].BW = temp3.weight();
			Q.add(nodeNum);	

	
		}
	

		int o = 1;
		outerloop:
			
		while(Q.size() != 0){
			
			if (Q.get(0) == (Integer) sourceNumber){
				Q.remove(0);
			}else{
				
			int temp2 = (int) Q.get(0);
			Q.remove(0);
			for ( int i = 0; i < nodelist[temp2].NumOfedges; i ++ ){
				o++;
				
				
				int w = nodelist[temp2].edges.get(i).endPointW();
				int v = nodelist[temp2].edges.get(i).endPointV();
				
				if(temp2 == w){
					w = v;
				}else{
					w = w;
				}
					
				if (w == DestiNumber){
				
					Marklist[w] = -1;
					nodelist[w].Dad = nodelist[temp2];
					nodelist[w].BW = returnMin(nodelist[temp2].BW, nodelist[temp2].edges.get(i).weight());
					break outerloop;
				}
				if (Marklist[w] != -1){
					Marklist[w] = -1;
					nodelist[w].Dad = nodelist[temp2];
					nodelist[w].BW = returnMin(nodelist[temp2].BW, nodelist[temp2].edges.get(i).weight());
					Q.add(w);	
				}
			}//end for loop
			}//end if and else
	
		}//end while loop
	
	}//end BFS
	
	public int returnMin(int a, int b){
		//if (compare(a, b) <= 0)
		if (compare(a, b) < 0)
			return a;
		else 
			return b;
	}
	
	public int compare(Object x, Object y){
		return ((Integer) x).compareTo((Integer) y);
	}
	
	public int calpath(){
		BFS();
		Node temp = nodelist[DestiNumber];
		int totallength = temp.BW;
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
