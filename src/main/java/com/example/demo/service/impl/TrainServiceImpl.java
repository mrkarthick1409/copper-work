package com.example.demo.service.impl;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.TrainDto;
import com.example.demo.dto.TrainRouteDto;
import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.TrainScheduleException;
import com.example.demo.model.Train;
import com.example.demo.model.TrainRoute;
import com.example.demo.model.TrainRouteDetails;
import com.example.demo.repository.TrainRouteDetailsRepository;
import com.example.demo.repository.TrainRouteInterface;
import com.example.demo.repository.TrainRouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.TrainRepository;
import com.example.demo.service.TrainService;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private TrainRouteRepository trainRouteRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TrainRouteDetailsRepository trainRouteDetailsRepository;

    @Override
    public MessageDto saveTrainData(TrainDto trainDto) throws TrainScheduleException {
        MessageDto messageDto = null;
        if(trainDto.getTrainNumber() != null) {
            Train train = modelMapper.map(trainDto, Train.class);
            trainRepository.save(train);
            messageDto = new MessageDto(true, "Saved Train Data");
        } else {
            throw new TrainScheduleException(ErrorCode.TRAIN_SCHEDULER_1001);
        }
        return messageDto;
    }

  public static void main(String[] args) {
    String a = "abc";
    String b = new String("abc");
    if(a.equalsIgnoreCase(b)) {
      System.out.println("er");
    }
  }

    @Override
    @Transactional
    public MessageDto saveTrainRoute(TrainRouteDto trainRouteDto) {
        if(trainRouteDto.getTrainId() != null) {
            TrainRoute trainRoute = modelMapper.map(trainRouteDto, TrainRoute.class);
            trainRoute.setActive(true);
            trainRoute = trainRouteRepository.save(trainRoute);
            updateTrainRouteDetails(trainRoute);
            return new MessageDto(true, "Saved Train Route Data");
        }
        return new MessageDto(false, "Invalid Train Route data input");
    }

    private void updateTrainRouteDetails(TrainRoute trainRoute) {
        TrainRouteDetails trainRouteDetails = new TrainRouteDetails();
        trainRouteDetails.setTrainRouteId(trainRoute.getId());
        trainRouteDetails.setTrainId(trainRoute.getTrainId());
        trainRouteDetails.setArrivedTime(new Date());
        trainRouteDetails.setArrivedStationId(trainRoute.getCurrentStationId());
        trainRouteDetailsRepository.save(trainRouteDetails);
    }

    @Override
    public MessageDto updateTrainRoute(Integer trainId, Integer currentStationId) {
        TrainRoute trainRoute = trainRouteRepository.findByTrainIdAndIsActive(trainId,true);
        trainRoute.setCurrentStationId(currentStationId);
        trainRoute = trainRouteRepository.save(trainRoute);
        updateTrainRouteDetails(trainRoute);
        return new MessageDto(true, "Updated Train Route Data");
    }

    @Override
    public List<TrainRouteDto> getTrainRouteDetails(Integer sourceStationId, Integer destinationStationId) {
        List<TrainRouteDto> trainRouteDtoList = new ArrayList<>();
        List<TrainRouteInterface> trainRouteList;

        // single Journey
        if(sourceStationId < destinationStationId) {
            trainRouteList = trainRouteRepository.getTrainSingleRouteDetails(sourceStationId,destinationStationId);
        } else {
            // return journey
            trainRouteList = trainRouteRepository.getTrainReturnRouteDetails(sourceStationId,destinationStationId);
        }
        if (!CollectionUtils.isEmpty(trainRouteList)) {
            trainRouteDtoList = trainRouteList.stream().map(trainRouteInterface -> {
                TrainRouteDto trainRouteDto = new TrainRouteDto();
                trainRouteDto.setId(trainRouteInterface.getId());
                trainRouteDto.setTrainId(trainRouteInterface.getTrainId());
                trainRouteDto.setTrainName(trainRouteInterface.getTrainName());
                trainRouteDto.setTrainNumber(trainRouteInterface.getTrainNumber());
                trainRouteDto.setCurrentStationName(trainRouteInterface.getStationName());
                trainRouteDto.setStartStationId(trainRouteInterface.getStartStationId());
                trainRouteDto.setEndStationId(trainRouteInterface.getEndStationId());
                return trainRouteDto;
            }).collect(Collectors.toList());
        }
        return trainRouteDtoList;
    }

    @Override
    public List<TrainRouteDto> getTrainLiveFeed(Integer trainId) {
        List<TrainRouteDto> trainRouteDtoList;
        TrainRoute activeTrainRoute = trainRouteRepository.findByTrainIdAndIsActive(trainId,true);
        List<TrainRouteDetails> activeTrainRouteDetails = trainRouteDetailsRepository.findByTrainRouteId(activeTrainRoute.getId());
        trainRouteDtoList = activeTrainRouteDetails.stream().map(trainRouteDetails -> {
            TrainRouteDto trainRouteDto = new TrainRouteDto();
            trainRouteDto.setId(trainRouteDetails.getId());
            trainRouteDto.setStartStationId(activeTrainRoute.getStartStationId());
            trainRouteDto.setEndStationId(activeTrainRoute.getEndStationId());
            trainRouteDto.setActive(activeTrainRoute.getActive());
            trainRouteDto.setTrainId(activeTrainRoute.getTrainId());
            trainRouteDto.setArrivedTime(trainRouteDetails.getArrivedTime());
            trainRouteDto.setCurrentStationId(trainRouteDetails.getArrivedStationId());
            return trainRouteDto;
        }).collect(Collectors.toList());
        return trainRouteDtoList;
    }
}
