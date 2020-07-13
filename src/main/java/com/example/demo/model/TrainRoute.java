package com.example.demo.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "train_route")
public class TrainRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer trainId;
    private Integer startStationId;
    private Integer endStationId;
    private Integer currentStationId;
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private List<TrainRouteDetails> trainRouteDetails;


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

    public Integer getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(Integer startStationId) {
        this.startStationId = startStationId;
    }

    public Integer getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(Integer endStationId) {
        this.endStationId = endStationId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<TrainRouteDetails> getTrainRouteDetails() {
        return trainRouteDetails;
    }

    public void setTrainRouteDetails(List<TrainRouteDetails> trainRouteDetails) {
        this.trainRouteDetails = trainRouteDetails;
    }

    public Integer getCurrentStationId() {
        return currentStationId;
    }

    public void setCurrentStationId(Integer currentStationId) {
        this.currentStationId = currentStationId;
    }
}
