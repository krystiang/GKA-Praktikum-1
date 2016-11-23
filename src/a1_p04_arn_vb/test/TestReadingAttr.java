package a1_p04_arn_vb.test;

import static a1_p04_arn_vb.GkaGraphReaders.newDirectedAttributedReader;
import static a1_p04_arn_vb.GkaGraphReaders.newUndirectedAttributedReader;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import a1_p04_arn_vb.AttrVertex;
import a1_p04_arn_vb.Root;
import a1_p04_arn_vb.util.GkaGraphReader;

public class TestReadingAttr {

	@Test
	public void testGraph1() throws IOException {
		GkaGraphReader<DirectedGraph<AttrVertex, DefaultEdge>> reader =
				newDirectedAttributedReader();
		DirectedGraph<AttrVertex, DefaultEdge> graph =
				reader.read(Root.pathToSource + "a1_p04_arn_vb/misc/graphAttr.gka");
		check(graph);
	}
	
	@Test
	public void testGraph2() throws IOException {
		GkaGraphReader<UndirectedGraph<AttrVertex, DefaultEdge>> reader =
				newUndirectedAttributedReader();
		UndirectedGraph<AttrVertex, DefaultEdge> graph =
				reader.read(Root.pathToSource + "a1_p04_arn_vb/misc/graphAttr2.gka");
		check(graph);
	}

	private <G extends Graph<AttrVertex, DefaultEdge>> void check(G graph) {
		Set<AttrVertex> expectedVertices = new HashSet<AttrVertex>();
		Collections.addAll(expectedVertices, new AttrVertex("a"),
				new AttrVertex("a"), new AttrVertex("b"), new AttrVertex("c"),
				new AttrVertex("d"), new AttrVertex("e"), new AttrVertex("f"),
				new AttrVertex("g"), new AttrVertex("h"), new AttrVertex("y"),
				new AttrVertex("x"),new AttrVertex("y"),new AttrVertex("z"),new AttrVertex("aa"));

		assertTrue("alle erwarteten Knoten gefunde", graph.vertexSet()
				.containsAll(expectedVertices));
		assertTrue("aber eben auch nicht mehr als diese",
				expectedVertices.containsAll(graph.vertexSet()));


		checkContains(graph, "a", "b");
		checkContains(graph, "a", "c");
		checkContains(graph, "a", "d");
		checkContains(graph, "b", "e");
		checkContains(graph, "c", "f");
		checkContains(graph, "d", "g");
		checkContains(graph, "e", "h");
		checkContains(graph, "f", "y");
		checkContains(graph, "f", "x");
		checkContains(graph, "y", "z");
		checkContains(graph, "g", "aa");
		checkContains(graph, "d", "z");
		
		checkValue(graph, "a", 10);
		checkValue(graph, "b", 20);
		checkValue(graph, "c", 30);
		checkValue(graph, "d", 40);
		checkValue(graph, "e", 50);
		checkValue(graph, "f", 60);
		checkValue(graph, "g", 70);
		checkValue(graph, "h", 80);
		checkValue(graph, "x", 100);
		checkValue(graph, "y", 90);
		checkValue(graph, "z", 110);
		checkValue(graph, "aa", 120);
		
	}

	private void checkContains(Graph<AttrVertex, DefaultEdge> graph,
			String sourceVertex, String targetVertex) {
		assertTrue("Graph sollte Kante (" + sourceVertex + "," + targetVertex
				+ ") enthalten", graph.containsEdge(
				new AttrVertex(sourceVertex), new AttrVertex(targetVertex)));
	}
	
	private void checkValue(Graph<AttrVertex, DefaultEdge> graph,
			String Vertex, int value) {
		Set<AttrVertex> vS = graph.vertexSet();
		Iterator<AttrVertex> ite = vS.iterator();
		while (ite.hasNext()) {
			AttrVertex nowVertex = ite.next();
			if (Vertex.equals(nowVertex.getName())) {
				assertTrue("Graph sollte Value " + value + " enthalten", nowVertex.getValue() == value);
			}
		}
	}
}
