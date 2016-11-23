package a1_p04_arn_vb;

import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.event.TraversalListener;



import a1_p04_arn_vb.impls.f2.BreadthFirstIteratorFactory;
import a1_p04_arn_vb.impls.f2.DirectedNeighbors;
import a1_p04_arn_vb.impls.f2.UndirectedNeighbors;
import a1_p04_arn_vb.impls.f3.NeighborChecker;
import a1_p04_arn_vb.impls.f3.PathFinder;
import a1_p04_arn_vb.impls.f3.SuccessorChecker;
import a1_p04_arn_vb.impls.f4.DirectedEdgeListFactory;
import a1_p04_arn_vb.impls.f4.GraphPathFactory;

public class PathFinders {

	private PathFinders() {
	}

	/**
	 * Gives a shortes Path with Breadth First Search
	 * 
	 * im speziellen baut diese Methode einen Pathfinder zusammen der erst in
	 * die Breite iteriert, die Iteration abbricht wenn dem Endknoten eine Länge
	 * zugewiesen wurde. Ausserdem interpretieren sowohl die EdgelistFactory als
	 * auch der Pathfinder "Kinder" als Nachfolger eines Knoten da hier auf
	 * Gerichteten Graphen gearbeitete wird
	 * 
	 * @param listener
	 * @return new GraphPath
	 */
	public static <V, E> PathFinder<DirectedGraph<V, E>, V, GraphPath<V, E>> newDirectedBreadthPathFinder(
			TraversalListener<V, E> listener) {

		SuccessorChecker<DirectedGraph<V, E>, V, E> successorCecker = new SuccessorChecker<>();

		DirectedEdgeListFactory<DirectedGraph<V, E>, V, E> edgeListFactory = new DirectedEdgeListFactory<>(
				successorCecker);

		GraphPathFactory<DirectedGraph<V, E>, V, E> graphPathFactory = new GraphPathFactory<>(
				edgeListFactory);


		BreadthFirstIteratorFactory<DirectedGraph<V, E>, V, E> iteratorFactory = new BreadthFirstIteratorFactory<>();

		DirectedNeighbors<DirectedGraph<V, E>, V, E> neighbors = new DirectedNeighbors<>();

		return new PathFinder<>(listener, graphPathFactory,
				iteratorFactory, neighbors);
	}

	/**
	 * erstellt einen PathFinder der mit Breitensuche einen ungerichteten
	 * Graphen nach einem Kürzesten Weg durchsucht
	 * 
	 * @param listener
	 * @return
	 */
	public static <V, E> Function3<UndirectedGraph<V, E>, V, V, GraphPath<V, E>> newUndirectedBreadthPathFinder(
			TraversalListener<V, E> listener) {

		NeighborChecker<UndirectedGraph<V, E>, V, E> neighborChecker = new NeighborChecker<>();

		DirectedEdgeListFactory<UndirectedGraph<V, E>, V, E> edgeListFactory = new DirectedEdgeListFactory<>(
				neighborChecker);

		GraphPathFactory<UndirectedGraph<V, E>, V, E> graphPathFactory = new GraphPathFactory<>(
				edgeListFactory);


		BreadthFirstIteratorFactory<UndirectedGraph<V, E>, V, E> iteratorFactory = new BreadthFirstIteratorFactory<>();

		UndirectedNeighbors<UndirectedGraph<V, E>, V, E> neighbors = new UndirectedNeighbors<>();

		return new PathFinder<>(listener, graphPathFactory,
				iteratorFactory, neighbors);
	}



}
