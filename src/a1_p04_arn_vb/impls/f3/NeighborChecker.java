//package a1_p04_arn_vb.impls.f3;
//
//
//import org.jgrapht.Graph;
//import org.jgrapht.Graphs;
//
//
//public class NeighborChecker<G extends Graph<V,E>, V, E> implements NeighborOrSuccessorChecker<G, V, V, Boolean> {
//
//	public Boolean apply(G graph, V source, V target) {
//		return Graphs.neighborListOf(graph, source).contains(target);
//	}
//
//}
