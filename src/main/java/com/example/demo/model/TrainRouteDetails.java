package com.example.demo.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "train_route_details")
public class TrainRouteDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer trainId;
    private Integer trainRouteId;
    private Integer arrivedStationId;
    private Date arrivedTime;

    public Integer getTrainRouteId() {
        return trainRouteId;
    }

    public void setTrainRouteId(Integer trainRouteId) {
        this.trainRouteId = trainRouteId;
    }

    public Integer getArrivedStationId() {
        return arrivedStationId;
    }

    public void setArrivedStationId(Integer arrivedStationId) {
        this.arrivedStationId = arrivedStationId;
    }

    public Date getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Date arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }


}
