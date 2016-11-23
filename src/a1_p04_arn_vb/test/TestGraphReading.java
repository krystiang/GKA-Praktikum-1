package a1_p04_arn_vb.test;

import static a1_p04_arn_vb.GkaGraphReaders.newDirectedReader;
import static a1_p04_arn_vb.GkaGraphReaders.newUndirectedReader;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import a1_p04_arn_vb.AttrVertex;
import a1_p04_arn_vb.Root;
import a1_p04_arn_vb.util.GkaGraphReader;

public class TestGraphReading {

	@Test
	public void testGraph1() throws IOException {
		GkaGraphReader<DirectedGraph<AttrVertex, DefaultEdge>> reader =
				newDirectedReader();
		DirectedGraph<AttrVertex, DefaultEdge> graph =
				reader.read(Root.pathToSource + "a1_p04_arn_vb/misc/graph1.gka");
		check(graph);
	}

	@Test
	public void testGraph2() throws IOException {
		GkaGraphReader<UndirectedGraph<AttrVertex, DefaultEdge>> reader =
				newUndirectedReader();
		UndirectedGraph<AttrVertex, DefaultEdge> graph =
				reader.read(Root.pathToSource + "a1_p04_arn_vb/misc/graph2.gka");
		check(graph);
	}

	private <G extends Graph<AttrVertex, DefaultEdge>> void check(G graph) {
		Set<AttrVertex> expectedVertices = new HashSet<AttrVertex>();
		Collections.addAll(expectedVertices, new AttrVertex("a"),
				new AttrVertex("b"), new AttrVertex("c"), new AttrVertex("d"),
				new AttrVertex("e"), new AttrVertex("f"), new AttrVertex("g"),
				new AttrVertex("h"), new AttrVertex("i"), new AttrVertex("j"),
				new AttrVertex("k"));

		assertTrue("alle erwarteten Knoten gefunde", graph.vertexSet()
				.containsAll(expectedVertices));
		assertTrue("aber eben auch nicht mehr als diese",
				expectedVertices.containsAll(graph.vertexSet()));

		checkContains(graph, "a", "b");
		checkContains(graph, "a", "c");
		checkContains(graph, "a", "h");
		checkContains(graph, "a", "k");
		checkContains(graph, "b", "b");
		checkContains(graph, "b", "j");
		checkContains(graph, "b", "k");
		checkContains(graph, "b", "i");
		checkContains(graph, "c", "a");
		checkContains(graph, "c", "d");
		checkContains(graph, "d", "a");
		checkContains(graph, "d", "e");
		checkContains(graph, "d", "k");
		checkContains(graph, "e", "b");
		checkContains(graph, "e", "c");
		checkContains(graph, "e", "e");
		checkContains(graph, "e", "f");
		checkContains(graph, "f", "c");
		checkContains(graph, "f", "g");
		checkContains(graph, "g", "g");
		checkContains(graph, "g", "e");
		checkContains(graph, "g", "b");
		checkContains(graph, "g", "d");
		checkContains(graph, "h", "b");
		checkContains(graph, "h", "c");
		checkContains(graph, "i", "a");
		checkContains(graph, "i", "c");
		checkContains(graph, "i", "i");
		checkContains(graph, "j", "k");
		checkContains(graph, "j", "c");
		checkContains(graph, "j", "a");
		checkContains(graph, "j", "b");
		checkContains(graph, "k", "c");
		checkContains(graph, "k", "g");
		checkContains(graph, "k", "d");
	}

	private void checkContains(Graph<AttrVertex, DefaultEdge> graph,
			String sourceVertex, String targetVertex) {
		assertTrue("Graph sollte Kante (" + sourceVertex + "," + targetVertex
				+ ") enthalten", graph.containsEdge(
				new AttrVertex(sourceVertex), new AttrVertex(targetVertex)));
	}
}
