package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.TrainDto;
import com.example.demo.dto.TrainRouteDto;
import com.example.demo.exception.TrainScheduleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.TrainService;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/create")
    public MessageDto saveTrainData(@RequestBody TrainDto trainDto) throws TrainScheduleException {
        return trainService.saveTrainData(trainDto);
    }

    @GetMapping("/details")
    public List<TrainRouteDto> getTrainRouteDetails(@RequestParam("sourceStationId") Integer sourceStationId, @RequestParam("destinationStationId") Integer destinationStationId) throws TrainScheduleException {
        return trainService.getTrainRouteDetails(sourceStationId,destinationStationId);
    }

    @GetMapping("/live/feed")
    public List<TrainRouteDto> getTrainLiveFeed(@RequestParam("trainId") Integer trainId) throws TrainScheduleException {
        return trainService.getTrainLiveFeed(trainId);
    }


    @PostMapping("/create/route")
    public MessageDto saveTrainRoute(@RequestBody TrainRouteDto trainRouteDto) throws TrainScheduleException {
        return trainService.saveTrainRoute(trainRouteDto);
    }

    @PostMapping("/update/route")
    public MessageDto updateTrainRoute(@RequestParam("trainId") Integer trainId,
                                       @RequestParam("currentStationId") Integer currentStationId) throws TrainScheduleException {
        return trainService.updateTrainRoute(trainId,currentStationId);
    }




}
