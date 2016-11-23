package a1_p04_arn_vb.util.impls;


import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import a1_p04_arn_vb.AttrVertex;
import a1_p04_arn_vb.util.GkaGraphBuilder;

public class UndirectedWeightedAttributedGraphBuilder
		extends
		AbstractBaseGraphBuilder<WeightedPseudograph<AttrVertex, DefaultWeightedEdge>, AttrVertex, DefaultEdge>
		implements GkaGraphBuilder<WeightedPseudograph<AttrVertex, DefaultWeightedEdge>> {

	public UndirectedWeightedAttributedGraphBuilder() {
		state = EXPECT_HEADER;
		graph = new WeightedPseudograph<AttrVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}

	private final BuilderBehavior EXPECT_HEADER = new BuilderBehavior("") {

		@Override
		boolean readLine(String line) {
			if (line.equals("#ungerichtet")) {
				state = EXPECT_HEADER2;
				return true;
			} else {
				state =
						new BuilderBehavior("#ungerichtet erwartet, " + line
								+ " gefunden") {
						};
				return false;
			}
		}

	};

	private final BuilderBehavior EXPECT_HEADER2 = new BuilderBehavior("") {

		@Override
		boolean readLine(String line) {
			if (line.equals("#attributiert,gewichtet")) {
				state = EXPECT_EDGE;
				return true;
			} else {
				state =
						new BuilderBehavior("#attributiert,gewichtet erwartet, " + line
								+ " gefunden") {
						};
				return false;
			}
		}

	};

	private final BuilderBehavior EXPECT_EDGE = new BuilderBehavior("") {

		@Override
		boolean readLine(String line) {
			String[] vertices = line.split(",");
			DefaultWeightedEdge addedEdge = null;
			if (vertices.length == 5) {
				AttrVertex source =
						makeVertexFrom(vertices[0] + "," + vertices[1]);
				AttrVertex target =
						makeVertexFrom(vertices[2] + "," + vertices[3]);
				graph.addVertex(source);
				graph.addVertex(target);
				addedEdge = graph.addEdge(source, target);
				graph.setEdgeWeight(addedEdge, Double.parseDouble(vertices[4]));
				return true;
			} else {
				state = new BuilderBehavior("not the right Edge format") {
				};
				return false;
			}
		}

	};

	@Override
	public void reset() {
		state = EXPECT_HEADER;
		graph = new WeightedPseudograph<AttrVertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}

	@Override
	protected AttrVertex makeVertexFrom(String str) {
		String[] parts = str.split(",");
		return new AttrVertex(parts[0], Integer.parseInt(parts[1]));
	}

}
