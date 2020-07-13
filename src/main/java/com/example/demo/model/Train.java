package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String trainName;

    private Integer trainNumber;

    @OneToMany
    @JoinColumn(name = "id")
    private List<TrainRoute>  trainRouteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrainRoute> getTrainRouteId() {
        return trainRouteId;
    }

    public void setTrainRouteId(List<TrainRoute> trainRouteId) {
        this.trainRouteId = trainRouteId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }
}
