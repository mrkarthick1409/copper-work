package com.example.demo.repository;

import com.example.demo.model.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRouteRepository extends JpaRepository<TrainRoute, Integer> {


    TrainRoute findByIsActive(boolean b);

    @Query("select tr.id as id,tr.trainId as trainId,tr.startStationId as startStationId,tr.endStationId as endStationId,t.trainName as trainName," +
            "t.trainNumber as trainNumber,s.stationName as stationName from TrainRoute tr " +
            "inner join Train as t on t.id = tr.trainId " +
            "inner join Station  as s on s.id = tr.currentStationId " +
            "where tr.isActive=true and tr.endStationId >= :destinationStationId " +
            "and tr.currentStationId <= :sourceStationId")
    List<TrainRouteInterface> getTrainSingleRouteDetails(@Param("sourceStationId") Integer sourceStationId,
                                                @Param("destinationStationId")Integer destinationStationId);

    @Query("select tr.id as id,tr.trainId as trainId,tr.startStationId as startStationId,tr.endStationId as endStationId,t.trainName as trainName," +
            "t.trainNumber as trainNumber,s.stationName as stationName from TrainRoute tr " +
            "inner join Train as t on t.id = tr.trainId " +
            "inner join Station  as s on s.id = tr.currentStationId " +
            "where tr.isActive=true and tr.endStationId >= :destinationStationId " +
            "and tr.currentStationId >= :sourceStationId")
    List<TrainRouteInterface> getTrainReturnRouteDetails(@Param("sourceStationId") Integer sourceStationId,
                                                @Param("destinationStationId")Integer destinationStationId);

    TrainRoute findByTrainIdAndIsActive(Integer trainId, boolean isActive);



}
