package com.example.demo.repository;

import com.example.demo.model.TrainRouteDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainRouteDetailsRepository extends JpaRepository<TrainRouteDetails, Integer> {


    List<TrainRouteDetails> findByTrainRouteId(Integer id);
}
