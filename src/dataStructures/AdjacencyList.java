package dataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.internal.runners.model.EachTestNotifier;

import AuxiliarDataStructures.IQueue;
import AuxiliarDataStructures.Queue;

public class AdjacencyList<V> implements Graph<V>{

	private List<List<List<Double>>> graph;
	private List<Vertex<V>> vertex;
	private boolean directed ;
	private int size;
	
	/**
	 * @param graph
	 * @param directed
	 * @param size
	 */
	public AdjacencyList(boolean directed) {

		graph = new ArrayList<List<List<Double>>>();
		this.vertex = new ArrayList<Vertex<V>>();
		this.directed = directed;
		this.size = 0;
	}


	@Override
	public boolean addVertex(Vertex<V> vertex) {
		
		boolean added = false;
		
		if(searchIndex(vertex) == -1) {
			
			this.vertex.add(vertex);
			
			added = true;
			
			this.size++;
		}
		
		return added;
	}

	@Override
	public boolean removeVertex(Vertex<V> vertex) {
		
		boolean removed = false;

		int index = searchIndex(vertex);
		
		if(index != -1) {
			
			removed = true;
			
			for(int i = 0; i < this.graph.size(); i++) {
				for (int j = 0; j < this.graph.get(i).size(); j++) {
					
					if(this.graph.get(i).get(j).get(0) == ((double) index)) {
						
						this.graph.get(i).remove(j);
					}
				}
			}
			
			this.vertex.remove(index);
			
			this.size --;
		}
		
		return removed;
	}

	@Override
	public boolean addEdge(Vertex<V> vertex_1, Vertex<V> vertex_2) {
		
		boolean added = false;
		
		int a = searchIndex(vertex_1);
		int b = searchIndex(vertex_2);
				
		if(a != -1 && b != -1) {
			
			ArrayList<Double> temp = new ArrayList<Double>();
			temp.add((double) b);
			this.graph.get(a).add(temp);
			
			if(!this.isDirected()) {
				
				ArrayList<Double> temp2 = new ArrayList<Double>();
				temp2.add((double) a);
				this.graph.get(b).add(temp2);
			}
			
			
			added = true;

		}
		
		return added;
	}

	@Override
	public boolean addEdge(Vertex<V> vertex_1, Vertex<V> vertex_2, double weight) {
		
		boolean added = false;
		
		int a = searchIndex(vertex_1);
		int b = searchIndex(vertex_2);
				
		if(a != -1 && b != -1) {
			
			ArrayList<Double> temp = new ArrayList<Double>();
<<<<<<< HEAD
			
			temp.add((double) a);
=======
>>>>>>> 7d785451e01f5b829fc7536ccdb0440e3b6cff26
			temp.add((double) b);
			temp.add(weight);
			
			this.graph.get(a).add(temp);
			
			if(!this.isDirected()) {
				
				ArrayList<Double> temp2 = new ArrayList<Double>();
				temp2.add((double) a);
				temp2.add(weight);
				this.graph.get(b).add(temp2);
			}
			
			
			added = true;

		}
		
		return added;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean isDirected() {
		return directed;
	}


	public int searchIndex(Vertex<V> vertex) {
		
		int index = -1;
		
		int hashcode = vertex.getElement().hashCode();
		
		boolean stop = false;
		
		for (int i = 0; i < this.vertex.size() && !stop ; i++) {
			
			if(this.vertex.get(i).getElement().hashCode() == hashcode) {
				
				index = i;
				stop = true;
			}
		}
		
		return index;
	}
	
	
	public List<Integer> bfs(Vertex<V> origin){
		
		Integer index = searchIndex(origin);
		
		ArrayList<Integer> path = new ArrayList<Integer>();

		IQueue<Integer> q = new Queue<Integer>();
		q.add(index);
		
		while(q.size() > 0) {
			
			index = q.poll();
			if(!q.contains(index))
				path.add(index);
			
			ArrayList<Integer> adjacents = adjacents(index);
			
			for (int i = 0; i < adjacents.size(); i++) {
				
				Integer temp = adjacents.get(i);
				
				if(!q.contains(temp))
					q.add(temp);
			}
			
		}
		
		return path;
	}

	private ArrayList<Integer> adjacents(Integer index) {
		
		Double inde = (double) index.intValue();
		
		ArrayList<Integer> adjacents = new ArrayList<Integer>();
		
		for (int i = 0; i < this.vertex.size(); i++) {
			
			List<Double> temp = this.graph.get(i);
			
			if(this.directed == true) {
				
				if(temp.get(0) == inde) {
					
					Integer element = (int) temp.get(1).doubleValue();
					
					if(!contains(adjacents, element))
						adjacents.add(element);
					
				}
			}
			else {
				
				if(temp.get(0) == inde) {
					
					Integer element = (int) temp.get(1).doubleValue();
					
					if(!contains(adjacents, element))
						adjacents.add(element);
				}
				
				if(temp.get(1) == inde) {
					
					Integer element = (int) temp.get(0).doubleValue();
					
					if(!contains(adjacents, element))
						adjacents.add(element);
				}
				
			}
				
		}
		
		return adjacents;
	}
	
	public boolean contains (ArrayList<Integer> a, Integer b) {
		
		boolean contains = false;
		boolean stop = false;
		
		for (int i = 0; i < a.size() && !stop; i++) {
			if(a.get(i) == b) {
				
				contains = true;
				stop = true;
			}
		}
		
		return contains;
	}
	
	
	/**
	 * @return the graph
	 */
	List<List<Double>> getGraph() {
		return graph;
	}


	/**
	 * @return the vertex
	 */
	List<Vertex<V>> getVertex() {
		return vertex;
	}
	
	
	
}
