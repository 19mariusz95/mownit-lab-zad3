package factory;

/**
 * Created by Mariusz on 12.03.2016.
 */
public interface VertexFactory<T> {

    T getVertex(int id);
}
