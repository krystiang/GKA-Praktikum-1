package a1_p04_arn_vb.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.DirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class CountingProxys {

	
	/**
	 * Er stellt einen Proxy der alle aufrufe von Methoden aus sich selbst zählt und an
	 * wrapee weiterleitet.
	 * dem Varargs Class<?> Argument sollten nur Interfaces übergegen werden, weil Proxys
	 * nur Interfaces implementieren können.
	 * 
	 * @param wrapee Das Objekt um das der Proxy gewickelt wird
	 * @param counts die Map in denen die Anzahl der Aufrufe gespeichert wird
	 * @param interfaces die Interfaces, die der Proxy implementieren soll
	 * @return
	 */
	public static Object asCounting(final Object wrapee,
			final Map<String, Integer> counts, Class<?>... interfaces) {
		return Proxy.newProxyInstance(wrapee.getClass().getClassLoader(),
				interfaces, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						String methode = method.getName();
						Integer prev = counts.put(methode, 1);
						if (prev != null) {
							int woops = prev.intValue();
							counts.put(methode, woops + 1);
						}
						return method.invoke(wrapee, args);
					}
				});
	}

	public static void main(String[] args) {
		SimpleDirectedWeightedGraph<String,DefaultEdge> graph =
				new SimpleDirectedWeightedGraph<String,DefaultEdge>(DefaultWeightedEdge.class);
		Map<String, Integer> count = new HashMap<>();
		@SuppressWarnings("unchecked")
		DirectedGraph<String, DefaultEdge> asDir =
				(DirectedGraph<String, DefaultEdge>) asCounting(graph,
						count, DirectedGraph.class, WeightedGraph.class);
		@SuppressWarnings("unchecked")
		WeightedGraph<String, DefaultEdge> asWei =
				(WeightedGraph<String, DefaultEdge>) asDir;
		asDir.addVertex("a");
		asWei.addVertex("b");
		DefaultEdge edge = asDir.addEdge("a", "b");
		asWei.setEdgeWeight(edge, 500);
		System.out.println(asWei);
		System.out.println(asDir);
		System.out.println(count);
	}
}
