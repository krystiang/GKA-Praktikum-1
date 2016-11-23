package a1_p04_arn_vb.util.impls;


import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;

import a1_p04_arn_vb.AttrVertex;
import a1_p04_arn_vb.util.GkaGraphBuilder;

public class UndirectedGraphBuilder
		extends
		AbstractBaseGraphBuilder<UndirectedGraph<AttrVertex, DefaultEdge>, AttrVertex, DefaultEdge>
		implements GkaGraphBuilder<UndirectedGraph<AttrVertex, DefaultEdge>> {

	public UndirectedGraphBuilder() {
		state = EXPECT_HEADER;
		graph = new Pseudograph<AttrVertex, DefaultEdge>(DefaultEdge.class);
	}

	private final BuilderBehavior EXPECT_HEADER = new BuilderBehavior("") {

		@Override
		boolean readLine(String line) {
			if (line.equals("#ungerichtet")) {
				state = EXPECT_EDGE;
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

	private final BuilderBehavior EXPECT_EDGE = new BuilderBehavior("") {

		@Override
		boolean readLine(String line) {
			String[] vertices = line.split(",");
			if (vertices.length == 2) {
				AttrVertex source = makeVertexFrom(vertices[0]);
				AttrVertex target = makeVertexFrom(vertices[1]);
				graph.addVertex(source);
				graph.addVertex(target);
				graph.addEdge(source, target);
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
		graph = new Pseudograph<AttrVertex, DefaultEdge>(DefaultEdge.class);
	}

	@Override
	protected AttrVertex makeVertexFrom(String str) {
		return new AttrVertex(str);
	}

}
