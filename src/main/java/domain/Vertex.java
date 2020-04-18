package domain;

public class Vertex {

    private int vertexWeight;
    private ClassBundle vertexClassBundle;

    public Vertex(int vertexWeight,
            ClassBundle vertexClassBundle){
        this.vertexWeight = vertexWeight;
        this.vertexClassBundle = vertexClassBundle;
    }

    public int getVertexWeight(){
        return vertexWeight;
    }

    public ClassBundle getClassBundle(){
        return vertexClassBundle;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vertex))
            return false;

        Vertex v = (Vertex) obj;
        ClassBundle cb1 = vertexClassBundle;
        ClassBundle cb2 = v.vertexClassBundle;

        return(
                cb1.equals(cb2)

                );
    }
}
