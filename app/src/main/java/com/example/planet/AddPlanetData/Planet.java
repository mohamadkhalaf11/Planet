package com.example.planet.AddPlanetData;

public class Planet {
    private String name;
    private String size;
    private String orbit;
    private String population;

    public Planet() {
    }

    public Planet(String name, String size, String orbit, String population) {
        this.name = name;
        this.size = size;
        this.orbit = orbit;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getOrbit() {
        return orbit;
    }

    public String getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setOrbit(String orbit) {
        this.orbit = orbit;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", orbit='" + orbit + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
