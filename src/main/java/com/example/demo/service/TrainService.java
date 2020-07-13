package com.example.demo.service;


import com.example.demo.dto.MessageDto;
import com.example.demo.dto.TrainDto;
import com.example.demo.dto.TrainRouteDto;
import com.example.demo.exception.TrainScheduleException;

import java.util.List;

public interface TrainService {

    MessageDto saveTrainData(TrainDto trainDto) throws TrainScheduleException;

    MessageDto saveTrainRoute(TrainRouteDto trainRouteDto);


    MessageDto updateTrainRoute(Integer trainId, Integer currentStationId);

    List<TrainRouteDto> getTrainRouteDetails(Integer sourceStationId, Integer destinationStationId);

    List<TrainRouteDto> getTrainLiveFeed(Integer trainId);

}
