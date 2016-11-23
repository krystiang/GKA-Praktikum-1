package a1_p04_arn_vb.impls.f3;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import a1_p04_arn_vb.impls.f2.BreadthFirstIteratorFactory;
import a1_p04_arn_vb.impls.f2.Neighbors;
import a1_p04_arn_vb.impls.f4.GraphPathFactory;

/**
 * Ein PathFinder ist eine Funktion die aus einem Graph
 * und zwei Vertexen einen GraphPath findet
 * PathFinder sind dazu gedacht per Breiten oder Tiefensuche
 * einen kürzesten Pfad im Graph zu finden.
 * 
 * Der PathFinder selbst findet nur die Längen der Wege zu Vertexes
 * vom Startvertex aus gesehen danach wird die resultierende Map
 * an die Pathfactory weitergereicht.
 * Zum Iterieren wird ein GraphIterator benutzt der entweder
 * erst in die Tiefe oder erst in die Breite traversiert.
 * Die Prüfung ob die Itearation beendet werden kann findet durch die
 * checkFinished Funktion statt.
 * Die Kinder des aktuellen Vertex werden über die findTolenghted Funktion ermittelt
 * Der Listener wird dem Iterator übergeben um daraus Zugriffe auf den Graphen
 * ableiten zu können
 * 
 * @author vincent
 *
 * @param <G> der Typ des Graph
 * @param <V> der Typ der Vertexe
 * @param <E> der Typ der Edges
 */
public class PathFinder<G , V, E> {
	
	/**
	 * listener ist ein TraversalListener der nach den Events horcht die von einem
	 * GraphIterator emitiert werden
	 */
	private final TraversalListener<V, E> listener;

	/**
	 * pathFactory ist eine Funktion die aus einem Graph einer Map und 2 Vertexen 
	 * einen GraphPath erstellt
	 * der Graph ist der Graph durch den der Pfad läuft
	 * die Map hat als Key Vertexes, die Values dazu sind die Längen vom Startvertex aus gesehen
	 * der erste Vertex ist der Anfang des Weges
	 * der zweite Vertex ist das Ende des Weges
	 * der GraphPath ist die Repräsentation des Weges
	 */
	private final GraphPathFactory<? super G, ? super Map<V, Integer>, ? super V, ? super V, ? extends GraphPath<V, E>> pathFactory;
	
	
	/**
	 * iteratorFactory ist eine Funktion die aus einem Graph und einem Vertex einen GraphIterator erstellt
	 * der Vertex sollte im Graph enthalten sein
	 * der Graph ist der Graph über den iteriert wird
	 * mit Iterator kann man die Iteration steuern
	 */
	private final BreadthFirstIterator<V,E> iterator;
	


	public PathFinder(
			TraversalListener<V, E> listener,
			GraphPathFactory<? super G, ? super Map<V, Integer>, ? super V, ? super V, ? extends GraphPath<V, E>> pathFactory,
			BreadthFirstIterator<V, E> iterator) {
		this.listener = listener;
		this.pathFactory = pathFactory;
		this.iterator = iterator;
	}

	/**
	 * Kennzeichnung/Nummerierung der Knoten
	 * 
	 * @param graph der Graph
	 * @param start startvertex
	 * @param end 	zielvertex
	 * 
	 * @return ein Graphpath
	 */
	public GraphPath<V, E> apply(G graph extends DirectedGraph, V start, V end) {

		if (start.equals(end)) {
			throw new IllegalArgumentException(
					"start and end should not be equal");
		}

		//Im lengths werden die nummerierten Knoten gespeichert
		boolean finished = false;
		Map<V, Integer> lengths = new HashMap<>();
		Set<V> toLenghten = null;
		V currentVertex = null;
		
		
		iterator.addTraversalListener(listener);
		//start wird mit 0 gekennzeichnet
		lengths.put(start, 0);

		
		 /** Schleife l�uft solange bis noch einen n�chsten Knoten hat und
		 * checkNotFinished wahr zur�ck gibt.
		 * Beim DFS ist es immer true.
		 * Beim BFS pr�ft es ob der Zielvertex in der Liste enthalten ist.,
		 * wenn ja erh�lt man ein false zur�ck.
		 */
		//while (iterator.hasNext() && !checkNotFinished.apply(end, currentVertex)) {
		while (iterator.hasNext() && !finished) {
			currentVertex = iterator.next();
			//gibt ein Set von Nachbarn des grade angeschauten Vertex
			Set<V> set = new HashSet<>();
			set.addAll(Graphs.successorListOf(graph, currentVertex));
			
			/**Schleife �ber die Nachbarn.
			*Ist Nachbar nummeriert wird gepr�ft ob die neuen Zahl kleiner das jetzige Zahl ist.
			*Wenn ja erh�lt der Knoten eine neue Nummerierung, 
			*sonst erh�lt es zum ersten mal eine Nummerierung.
			*/
			for (V each : toLenghten) {
				int currentLength = lengths.get(currentVertex);
				int newLength = currentLength + 1;
				if (lengths.containsKey(each)) {
					if (newLength < lengths.get(each)) {
						lengths.put(each, newLength);
					}
				} else {
					lengths.put(each, newLength);
				}
				finished = end.equals(currentVertex);
			}
		}
		//Aufruf von GraphPathFactory
		//Gibt ein Pfad zur�ck der den k�rzestens Weg von Start- zu Zielvertex zeigt.
		return pathFactory.apply(graph, lengths, start, end);
	}
}
