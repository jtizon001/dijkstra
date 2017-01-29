package A6_Dijkstra;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class DiGraph implements DiGraph_Interface {
	// in here go all your data and methods for the graph
	// and the topo sort operation
	HashMap<Long, String> vertMap = new HashMap<Long, String>();  //edited these throughout
	HashMap<Long, String> edgMap = new HashMap<Long, String>();  // this one
	ArrayList<Vertex> vertList = new ArrayList<Vertex>();
	ArrayList<Edge> edgList = new ArrayList<Edge>();
	ArrayList<String>sortVert= new ArrayList<String>();
	boolean need2Break=false;
	LinkedList<Vertex> topSort;
	//HashMap<Long, Vertex> vertMap1= new HashMap<Long,Vertex>();
	Map<String, Vertex> vertMap1= new HashMap<String, Vertex>();
	Map<String, Edge> edgMap1= new HashMap<String, Edge>();

	HashSet<Long> edgeIds= new HashSet<Long>();
	HashSet<Long> vertIds= new HashSet<Long>();




	public DiGraph ( ) { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
	}

	// rest of your code to implement the various operations

	@Override
	public boolean addNode(long idNum, String label) {
		// TODO Auto-generated method stub
		if(idNum<0){
			return false;
		}
		if(label==null)
			return false;

		if (vertMap.containsKey(idNum)||vertMap.containsValue(label)){
			return false;
		}
		else{
			Vertex v= new Vertex(idNum, label);//was label
			vertMap1.put(label, v);
			vertMap.put(idNum, label);//was label
			vertList.add(v);
			return true;
		}
	}


	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		// TODO Auto-generated method stub
		Edge eddy= new Edge(idNum,sLabel,dLabel,weight,eLabel);
		if (idNum<0)
			return false;
		if(edgMap.containsKey(idNum))
			return false;
		if((!vertMap.containsValue(dLabel))||(!vertMap.containsValue(sLabel)))
			return false;
		if(edgMap1.containsKey(sLabel+"blah"+dLabel))
			return false;
		if(vertMap1.get(sLabel).data.equals(sLabel)||vertMap1.get(dLabel).data.equals(dLabel)){
			eddy.destVert=vertMap1.get(dLabel);
			eddy.srcVert=vertMap1.get(sLabel);
			eddy.destVert.srcEdge.add(eddy);
			eddy.srcVert.destEdge.add(eddy);

			edgMap.put(idNum,eLabel);
			edgList.add(eddy);
			edgMap1.put(sLabel+"blah"+dLabel, eddy);

			return true;
		}
		return false;
	}



	@Override
	public boolean delNode(String label) {
		// TODO Auto-generated method stub
		if(vertMap.containsValue(label)==false)
			return false;
		else{
			Vertex delVert = vertMap1.get(label);
			vertMap.remove(delVert.iden);
			vertList.remove(delVert);
			vertMap1.remove(label);

			for(Edge eddy:delVert.srcEdge){
				eddy.srcVert.destEdge.remove(eddy);
				edgeIds.remove(eddy.iden);
				edgMap.remove(eddy.iden);
				edgList.remove(eddy);
				edgMap1.remove(eddy.srcVert.data+"blah"+eddy.destVert.data);
			}
			for(Edge eddy:delVert.destEdge){
				eddy.destVert.srcEdge.remove(eddy); //change back to destEdge
				edgeIds.remove(eddy.iden);
				edgMap.remove(eddy.iden);
				edgList.remove(eddy);
				edgMap1.remove(eddy.srcVert.data+"blah"+eddy.destVert.data);
			}
			return true;

		}
	}


	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// TODO Auto-generated method stub

		if(edgMap1.containsKey(sLabel+"blah"+dLabel)){
			Edge del=edgMap1.get(sLabel+"blah"+dLabel);
			del.srcVert.destEdge.remove(del);
			del.destVert.srcEdge.remove(del);
			edgMap.remove(del.iden);
			edgList.remove(del.iden);
			edgMap1.remove(sLabel+"blah"+dLabel);
			del.srcStr=null;
			del.destStr=null;


			return true;
		}
		return false;
	}


	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return vertMap.size();
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return edgMap.size();
	}


	@Override
	public String[] topoSort() {
		String [] sorted=new String[vertList.size()];
		ArrayList<Vertex> sortedAL=new ArrayList<Vertex>();
		HashSet<Vertex> vertSet= new HashSet<Vertex>();
		for (Vertex verts: vertList){
			if(verts.srcEdge.isEmpty())
				vertSet.add(verts);
			//sortedAL.add(verts);
		}
		while(!vertSet.isEmpty()){
			Vertex vert=vertSet.iterator().next();
			vertSet.remove(vert);
			sortedAL.add(vert);

			for(Iterator<Edge> eddy=vert.destEdge.iterator();eddy.hasNext();){
				Edge e=eddy.next();
				Vertex dest= e.destVert;
				eddy.remove();
				dest.srcEdge.remove(e);
				if(dest.srcEdge.isEmpty())
					vertSet.add(dest);
			}
		}
		for(Vertex verts: vertList){
			if(!verts.srcEdge.isEmpty())
				return null;
		}
		int i=0;
		for(Vertex verts: sortedAL){
			sorted[i]=verts.data;
			i++;
		}
		return sorted;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
//		// TODO Auto-generated method stub
		MinBinHeap mbh= new MinBinHeap();
		Vertex diVert=vertMap1.get(label);
		diVert.dist=0;
		EntryPair ep=new EntryPair(diVert.data, diVert.dist);
		mbh.insert(ep);
		while(mbh.size()>0){
			Vertex delVert=vertMap1.get(mbh.getMin().value);
			mbh.delMin();
			delVert.visited=true;
			 for(Edge eddy: delVert.destEdge){
				 if(eddy.destVert.dist>delVert.dist+eddy.weight){
					 eddy.destVert.dist=((int)(delVert.dist)+(int)(eddy.weight));
					 mbh.insert(new EntryPair(eddy.destVert.data, eddy.destVert.dist));
				 }
				 
			 }
		}
		ShortestPathInfo[] pathInfo= new ShortestPathInfo[vertList.size()];
		int i=0;
		
		for(Vertex verts: vertList){
			if (!verts.visited)
				verts.dist=-1;
			pathInfo[i]=new ShortestPathInfo(verts.data,verts.dist);
			i++;
		}
		return pathInfo;
		

	}

}


