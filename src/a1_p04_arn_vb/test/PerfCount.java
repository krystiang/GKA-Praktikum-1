package a1_p04_arn_vb.test;

import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;

import a1_p04_arn_vb.AttrVertex;


public class PerfCount implements TraversalListener<AttrVertex, DefaultEdge>{

	public int connectedComponentFinished;
	public int connectedComponentStarted;
	public int edgeTraversed;
	public int vertexTraversed;
	public int vertexFinished;

	@Override
	public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
		connectedComponentFinished++;
	}

	@Override
	public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
		connectedComponentStarted++;
	}

	@Override
	public void edgeTraversed(EdgeTraversalEvent<AttrVertex, DefaultEdge> e) {
		edgeTraversed++;
	}

	@Override
	public void vertexTraversed(VertexTraversalEvent<AttrVertex> e) {
		vertexTraversed++;
	}

	@Override
	public void vertexFinished(VertexTraversalEvent<AttrVertex> e) {
		vertexFinished++;
	}

	@Override
	public String toString() {
		return "PerfCount [edgeTraversed="
				+ edgeTraversed + ", vertexTraversed=" + vertexTraversed
				+  "]";
	}
	
	public void clear(){
		connectedComponentFinished = 0;
		connectedComponentStarted = 0;
		edgeTraversed = 0;
		vertexTraversed = 0;
		vertexFinished = 0;
	}

}
