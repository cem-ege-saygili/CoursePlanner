package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Vertex, List<Vertex>> vertex_AdjacencyList_Mappings;

    public Graph(){

    }

    public void addVertex(Vertex v){
        if(!vertex_AdjacencyList_Mappings.containsKey(v))
            vertex_AdjacencyList_Mappings.put(v, new LinkedList<Vertex>());
        else
            System.out.println(v + " already exists, cannot add.");
    }

    public void addEdge(Vertex v1,
                        Vertex v2){
        addVertex(v1);
        addVertex(v2);

        List<Vertex> vertex1AdjacencyList = vertex_AdjacencyList_Mappings.get(v1); //bidirectional graph, add v1 to v2 and v2 to v1
        vertex1AdjacencyList.add(v2);

        List<Vertex> vertex2AdjacencyList = vertex_AdjacencyList_Mappings.get(v2);
        vertex2AdjacencyList.add(v1);


    }

    public int getVertexCount(){
        return vertex_AdjacencyList_Mappings.keySet().size();
    }

    public int getEdgesCount(){
        int count = 0;
        for(Vertex v:vertex_AdjacencyList_Mappings.keySet()){
            List<Vertex> curAdjList = vertex_AdjacencyList_Mappings.get(v);
            int sizeCurAdjList = curAdjList.size();
            count += sizeCurAdjList;
        }

        count /= 2; //eliminating double counts. for a bidirectional graph

        return count;
    }

    public boolean hasVertex(Vertex v){
        return vertex_AdjacencyList_Mappings.containsKey(v);
    }

    public boolean hasEdge(Vertex vertexSource, Vertex vertexDestination){
        List<Vertex> adjListOfVertexSource = vertex_AdjacencyList_Mappings.get(vertexSource);
        return adjListOfVertexSource.contains(vertexDestination);
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (Vertex v : vertex_AdjacencyList_Mappings.keySet()) {
            builder.append(v.toString() + ": ");
            for (Vertex w : vertex_AdjacencyList_Mappings.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        String strAdjacencyList = builder.toString();

        return "Graph: " +
                "contains " + getVertexCount() + " vertices, " +
                getEdgesCount() + " edges.\n\n" +
                "AdjacencyList is as follows:\n\n" +
                strAdjacencyList;
    }
}
