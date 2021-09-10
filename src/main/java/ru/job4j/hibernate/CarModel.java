package ru.job4j.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "carModel")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "carBrand_id")
    private CarBrand carBrand;

    public CarModel() {
    }

    public CarModel(String name) {
        this.name = name;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarModel carModel = (CarModel) o;
        return id == carModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

