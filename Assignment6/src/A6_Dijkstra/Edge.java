package A6_Dijkstra;

public class Edge {
	public String srcStr;
	public String destStr;
	public long iden;
	public long weight;
	
	public Vertex srcVert;
	public Vertex destVert;
	
	public Edge(long id, String src, String dest, long w, String elab){
		iden=id;
		srcStr=src;
		destStr=dest;
		weight=w;
	}
	public String getSrc(){
		return srcStr;
	}
	public String getDest(){
		return destStr;
	}

}
