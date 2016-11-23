package a1_p04_arn_vb.impls.f4;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;


/**
 * GraphPathFactory ist eine Funktion die aus einem Graph einer Map und 2 Vertexen 
 * einen GraphPath erstellt
 * @author 
 *
 * @param <G> der Typ des Graph
 * @param <V> der Typ der Vertexe
 * @param <E> der Typ der Edges
 */
public class GraphPathFactory<G extends Graph<V, E>,M extends Map<V,Integer>, V, E,L extends List<E>>{

	/**
	 * edgeListFactory ist eine Funktion die aus einem Graph, einer Map(Vertex->Weglängen)
	 * und 2 Vertexen(Start-/ZielVertex) den kürzesten Weg als Liste von Edges findet
	 * Die Liste ist in umgedrehter Reihenfolge (Ziel zuerst)
	 */
	//@formatter:off
	private final EdgeListFactory<
		? super G,
		? super Map<V, Integer>,
		? super V,
		? super V,
		? extends List<E>> edgeListFactory;
	//@formatter:on

	public GraphPathFactory(
			EdgeListFactory<? super G, ? super Map<V, Integer>, ? super V, ? super V, ? extends List<E>> edgeListFactory) {
		this.edgeListFactory = edgeListFactory;
	}

	public GraphPath<V, E> apply(G graph, Map<V, Integer> lengths, V start,
			V end) {
		List<E> edgeList = edgeListFactory.apply(graph, lengths, start, end);

		double weight = 0;
		for (E eachEdge : edgeList) {
			weight += graph.getEdgeWeight(eachEdge);
		}

		Collections.reverse(edgeList);

		return new GraphPathImpl<V, E>(graph, start, end, edgeList, weight);
	}

}
