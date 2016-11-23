//package a1_p04_arn_vb.impls.f3;
//
//
//import org.jgrapht.DirectedGraph;
//import org.jgrapht.Graphs;
//
//
//
//public class SuccessorChecker<G extends DirectedGraph<V,E>, V, E> implements NeighborOrSuccessorChecker<G, V, V, Boolean> {
//
//
//	public Boolean apply(G graph, V source, V target) {
//		return Graphs.successorListOf(graph, source).contains(target);
//	}
//
//}
