package graph;

import java.awt.*;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class Vertex {
    private int id;
    private Color color;

    public Vertex(int id) {
        this.id = id;
        this.color = Color.orange;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
