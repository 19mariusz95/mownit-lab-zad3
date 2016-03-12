package graph;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class Edge {
    private double resistance;
    private double voltage;

    public Edge(double resistance) {
        this.resistance = resistance;
        this.voltage = 0.0;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    @Override
    public String toString() {
        return String.valueOf(resistance);
    }
}
