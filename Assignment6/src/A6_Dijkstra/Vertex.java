package A6_Dijkstra;

import java.util.HashSet;

public class Vertex {
	public String data;
	public long iden;
	public Vertex srcVert;
	public boolean visited=false;
	public Vertex destVert;
	public HashSet<Edge> srcEdge;
	public HashSet<Edge> destEdge;
	public int dist;
	//boolean visited;

	public Vertex(long iden, String data){
		this.data=data;
		this.iden=iden;
		srcEdge=new HashSet<Edge>();
		destEdge=new HashSet<Edge>();
		visited=false;
		dist=Integer.MAX_VALUE;
	}
	public long getKey(){
		return iden;
	}


}
