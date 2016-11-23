package a1_p04_arn_vb;


import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.WeightedPseudograph;

import a1_p04_arn_vb.util.impls.DirectedAttributedGraphBuilder;
import a1_p04_arn_vb.util.impls.DirectedGraphBuilder;
import a1_p04_arn_vb.util.impls.DirectedWeightedAttributedGraphBuilder;
import a1_p04_arn_vb.util.impls.DirectedWeightedGraphBuilder;
import a1_p04_arn_vb.util.impls.GkaGraphFileReader;
import a1_p04_arn_vb.util.impls.UndirectedAttributedGraphBuilder;
import a1_p04_arn_vb.util.impls.UndirectedGraphBuilder;
import a1_p04_arn_vb.util.impls.UndirectedWeightedAttributedGraphBuilder;
import a1_p04_arn_vb.util.impls.UndirectedWeightedGraphBuilder;


/**
 * creates a new Graph
 */
public class GkaGraphReaders {
	private GkaGraphReaders() {
	}

	/**
	 * 
	 * @return create a new DirectedGraph without "attributiert" and "gewichtet"
	 */
	public static GkaGraphFileReader<DirectedGraph<AttrVertex, DefaultEdge>> newDirectedReader() {
		return new GkaGraphFileReader<DirectedGraph<AttrVertex, DefaultEdge>>(
				new DirectedGraphBuilder());
	}
	
	/**
	 * 
	 * @return create a new DirectedGraph with  "attributiert" and without "gewichtet"
	 */
	public static GkaGraphFileReader<DirectedGraph<AttrVertex, DefaultEdge>> newDirectedAttributedReader() {
		return new GkaGraphFileReader<DirectedGraph<AttrVertex, DefaultEdge>>(
				new DirectedAttributedGraphBuilder());
	}
	
	/**
	 * 
	 * @return create a new DirectedWeightedMultigraph with "attributiert" and "gewichtet"
	 */
	public static GkaGraphFileReader<DirectedWeightedMultigraph<AttrVertex, DefaultWeightedEdge>> newDirectedWeightedAttributedReader() {
		return new GkaGraphFileReader<DirectedWeightedMultigraph<AttrVertex, DefaultWeightedEdge>>(
				new DirectedWeightedAttributedGraphBuilder());
	}
	
	/**
	 * 
	 * @return create a new DirectedWeightedMultigraph with "gewichtet" and  without "attributiert"
	 */
	public static GkaGraphFileReader<DirectedWeightedMultigraph<AttrVertex, DefaultWeightedEdge>> newDirectedWeightedReader() {
		return new GkaGraphFileReader<DirectedWeightedMultigraph<AttrVertex, DefaultWeightedEdge>>(
				new DirectedWeightedGraphBuilder());
	}

	/**
	 * 
	 * @return create a new UndirectedGraph without "attributiert" and "gewichtet"
	 */
	public static GkaGraphFileReader<UndirectedGraph<AttrVertex, DefaultEdge>> newUndirectedReader() {
		return new GkaGraphFileReader<UndirectedGraph<AttrVertex, DefaultEdge>>(
				new UndirectedGraphBuilder());
	}
	
	/**
	 * 
	 * @return create a new UndirectedGraph with "attributiert" and without "gewichtet"
	 */
	public static GkaGraphFileReader<UndirectedGraph<AttrVertex, DefaultEdge>> newUndirectedAttributedReader() {
		return new GkaGraphFileReader<UndirectedGraph<AttrVertex, DefaultEdge>>(
				new UndirectedAttributedGraphBuilder());
	}

	/**
	 * 
	 * @return create a new WeightedPseudograph with "attributiert" and "gewichtet"
	 */
	public static GkaGraphFileReader<WeightedPseudograph<AttrVertex, DefaultWeightedEdge>> newUndirectedWeightedAttributedReader() {
		return new GkaGraphFileReader<WeightedPseudograph<AttrVertex, DefaultWeightedEdge>>(
				new UndirectedWeightedAttributedGraphBuilder());
	}

	/**
	 * 
	 * @return create a new WeightedPseudograph with "gewichtet" and without "attributiert"
	 */
	public static GkaGraphFileReader<WeightedPseudograph<AttrVertex, DefaultWeightedEdge>> newUndirectedWeightedReader() {
		return new GkaGraphFileReader<WeightedPseudograph<AttrVertex, DefaultWeightedEdge>>(
				new UndirectedWeightedGraphBuilder());
	}
}
