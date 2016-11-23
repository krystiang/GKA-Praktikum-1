//package a1_p04_arn_vb.impls.f2;
//
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.jgrapht.DirectedGraph;
//import org.jgrapht.Graphs;
//
//
//
//public class DirectedNeighbors<G extends DirectedGraph<V,E>, V, E> implements Neighbors <G,V,Set<V>>{
//
//	public Set<V> apply(G a, V b) {
//		Set<V> set = new HashSet<>();
//		set.addAll(Graphs.successorListOf(a, b));
//		return set;
//	}
//
//}
