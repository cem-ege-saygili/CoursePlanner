package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Vertex, List<Vertex>> vertex_AdjacencyList_Mappings;

    public Graph(){

    }

    public void addVertex(Vertex v){
        vertex_AdjacencyList_Mappings.put(v, new LinkedList<Vertex>());
    }

    public void addEdge(Vertex sourceVertex,
                        Vertex destinationVertex){
        if(!vertex_AdjacencyList_Mappings.containsKey(sourceVertex))
            addVertex(sourceVertex);
        if(!vertex_AdjacencyList_Mappings.containsKey(destinationVertex))
            addVertex(destinationVertex);

        List<Vertex> sourceVertexAdjacencyList = vertex_AdjacencyList_Mappings.get(sourceVertex);
        sourceVertexAdjacencyList.add(destinationVertex);
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

}
