package Entities;

public class Ships {
    private int id;
    private String ship_name;
    private int passenger_capacity;
    private String route;
    private int ports_number;
    public Ships()
    {

    }
    public Ships(String ship_name, int passenger_capacity, String route, int ports_number) {
        this.ship_name = ship_name;
        this.passenger_capacity = passenger_capacity;
        this.route = route;
        this.ports_number = ports_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassenger_capacity() {
        return passenger_capacity;
    }

    public void setPassenger_capacity(int passenger_capacity) {
        this.passenger_capacity = passenger_capacity;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public int getPorts_number() {
        return ports_number;
    }

    public void setPorts_number(int ports_number) {
        this.ports_number = ports_number;
    }
}
