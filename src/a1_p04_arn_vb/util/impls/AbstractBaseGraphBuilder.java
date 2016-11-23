package a1_p04_arn_vb.util.impls;


import org.jgrapht.Graph;

import a1_p04_arn_vb.util.GkaGraphBuilder;
/**
 * Ein abstrakter GraphBuilder mit einem wechselnden verhalten
 * das als BuilderBehavior implementiert wird
 * reset ist abstract weil es vom Typ des tatsächlich zu befüllenden Graph abhängt
 * makeVertex ist abstrakt weil es vom Typ des zu lesenden Vertex abhängt
 * 
 * Idee ist das Builder in einem bestimmten Zustand sind in dem sie
 * Eingaben erwarten, die Sie dazu veranlassen den Zustand zu wechseln
 * und andere Eingaben zu erwarten.
 * 
 * z.B. staretet der DirectedGraphBuilder im Zustand das er erwartet
 * #gerichtet in readLine übergeben zu bekommen.
 * Trifft das ein erwartet er Zeichenketten die ungefähr so aussehen: "aasad,asrgg"
 * Erfüllt jemals ein readLine aufruf nicht die erwartungen wechselt er in einen
 * Fehlerzustand aus den an ihn mit reset() befreien müsste.
 *
 * @param <G>
 * @param <V>
 * @param <E>
 */
public abstract class AbstractBaseGraphBuilder<G extends Graph<V, ? extends E>, V, E>
		implements GkaGraphBuilder<G> {

	protected BuilderBehavior state = null;
	protected G graph = null;

	/**
	  * {@inheritDoc}
	  */
	@Override
	public boolean readLine(String line) {
		return state.readLine(line);
	}
	
	/**
	  * {@inheritDoc}
	  */
	@Override
	public G getGraph() {
		return graph;
	}
	
	/**
	  * {@inheritDoc}
	  */
	@Override
	public boolean isInErrorState() {
		return !state.error.equals("");
	}

	/**
	  * {@inheritDoc}
	  */
	@Override
	public String getErrorMessage() {
		return state.error;
	}

	/**
	  * {@inheritDoc}
	  */
	@Override
	abstract public void reset();
	
	/**
	  * {@inheritDoc}
	  */
	abstract protected V makeVertexFrom(String str);


	/**
	 * Standard implementation des internen BuilderBehavior
	 * plan ist das erstellen des Fehlerzustands
	 * trivial mit new BuilderBehavior("ErrorMessage") abzukürzen
	 *
	 */
	public static abstract class BuilderBehavior {
		final String error;
		
		BuilderBehavior(String error){
			this.error = error;
		}

		boolean readLine(String line) {
			throw new Error(
					"readLine() should not have been called at this Point because: " + error);
		}
	}

}
