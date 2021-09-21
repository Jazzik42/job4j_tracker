package ru.job4j.hibernate.carowner;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)
    },
    inverseJoinColumns = {
            @JoinColumn(name = "driver_id", nullable = false, updatable = false)
    })

    private List<Driver> drivers;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Driver> getDriver() {
        return drivers;
    }

    public void setList(Driver driver) {
        if (drivers == null) {
            drivers = new ArrayList<>();
        }
        drivers.add(driver);
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
