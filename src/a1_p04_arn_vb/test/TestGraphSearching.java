package a1_p04_arn_vb.test;

import static a1_p04_arn_vb.GkaGraphReaders.newDirectedReader;
import static a1_p04_arn_vb.GkaGraphReaders.newUndirectedReader;
import static a1_p04_arn_vb.util.CountingProxys.asCounting;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.UnmodifiableDirectedGraph;
import org.jgrapht.graph.UnmodifiableUndirectedGraph;
import org.junit.Test;

import a1_p04_arn_vb.AttrVertex;
import a1_p04_arn_vb.PathFinders;
import a1_p04_arn_vb.Root;
import a1_p04_arn_vb.util.Function3;

@SuppressWarnings("unchecked")
public class TestGraphSearching {

	DirectedGraph<AttrVertex, DefaultEdge> directed;
	UndirectedGraph<AttrVertex, DefaultEdge> undirected;
	Map<String, Integer> directedcounts = new HashMap<>();
	Map<String, Integer> undirectedcounts = new HashMap<>();

	{
		try {
			directed =
					(DirectedGraph<AttrVertex, DefaultEdge>) asCounting(
							new UnmodifiableDirectedGraph<AttrVertex, DefaultEdge>(
									newDirectedReader().read(Root.pathToSource + "a1_p04_arn_vb/misc/graph1.gka")),
							directedcounts, DirectedGraph.class);
			undirected =
					(UndirectedGraph<AttrVertex, DefaultEdge>) asCounting(
							new UnmodifiableUndirectedGraph<AttrVertex, DefaultEdge>(
									newUndirectedReader().read(
											Root.pathToSource + "a1_p04_arn_vb/misc/graph2.gka")), undirectedcounts,
							UndirectedGraph.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBreadth() {
		PerfCount directedCounter = new PerfCount();
		//@formatter:off
		Function3<
		? super DirectedGraph<AttrVertex, DefaultEdge>,
		? super AttrVertex,
		? super AttrVertex,
		? extends GraphPath<AttrVertex, DefaultEdge>> directedFinder =
				PathFinders.newDirectedBreadthPathFinder(directedCounter);
		PerfCount undirectedCounter = new PerfCount();
		Function3<
		? super UndirectedGraph<AttrVertex, DefaultEdge>,
		? super AttrVertex,
		? super AttrVertex,
		? extends GraphPath<AttrVertex, DefaultEdge>> undirectedFinder =
				PathFinders.newUndirectedBreadthPathFinder(undirectedCounter);
		//@formatter:on

		GraphPath<AttrVertex, DefaultEdge> directedPath =
				directedFinder.apply(directed, new AttrVertex("a"), new AttrVertex("f"));
		System.out.println("breadth direct");
		System.out.println(directedCounter);
		System.out.println(directedcounts);
		System.out.println(directedPath);
		directedcounts.clear();

		GraphPath<AttrVertex, DefaultEdge> undirectedPath =
				undirectedFinder.apply(undirected, new AttrVertex("a"), new AttrVertex("f"));
		System.out.println("breadth undirect");
		System.out.println(undirectedCounter);
		System.out.println(undirectedcounts);
		System.out.println(undirectedPath);
		undirectedcounts.clear();

		checkPaths(directedPath, undirectedPath);
	}

	
	private void checkPaths(GraphPath<AttrVertex, DefaultEdge> directedPath,
			GraphPath<AttrVertex, DefaultEdge> undirectedPath) {
		List<String> directedVertexList = new ArrayList<String>();
		Collections.addAll(directedVertexList, "a", "k", "d", "e", "f");
		List<String> undirectedVertexList = new ArrayList<String>();
		Collections.addAll(undirectedVertexList, "a", "c", "f");

		check(directedPath, directedVertexList);
		check(undirectedPath, undirectedVertexList);
	}

	private void check(GraphPath<AttrVertex, DefaultEdge> path,
			List<String> vertices) {
		Iterator<String> vertexIterator = vertices.iterator();
		Iterator<DefaultEdge> edgeIterator = path.getEdgeList().iterator();

		String expectedStartVertex = vertexIterator.next();
		assertEquals("checke ersten Knoten", expectedStartVertex,
				path.getStartVertex().getName());

		String currentExpectedVertex = null;
		String currentActualVertex = null;
		Graph<AttrVertex, DefaultEdge> graph = path.getGraph();
		
		while (vertexIterator.hasNext() && edgeIterator.hasNext()) {
			currentExpectedVertex = vertexIterator.next();
			DefaultEdge dg = edgeIterator.next();
			currentActualVertex = graph.getEdgeTarget(dg).getName();
			if (graph instanceof UndirectedGraph
					&& !(currentExpectedVertex.equals(currentActualVertex)))
				currentActualVertex = graph.getEdgeSource(dg).getName();

			assertEquals("checke Knoten des Wegs", currentExpectedVertex,
					currentActualVertex);
			
		}

		assertTrue("es sollten keine weiteren Kanten gefunden worden sein",
				!edgeIterator.hasNext());
		assertTrue("es sollten keine weiteren Knoten erwartet werden",
				!vertexIterator.hasNext());

	}

}
