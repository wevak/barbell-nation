package com.barbellnation.service;


import java.util.List;

import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.TrainerReqDTO;
import com.barbellnation.dto.TrainerRespDTO;

public interface TrainerService {
    List<TrainerRespDTO> getAllTrainers();

    ApiResponse addTrainer(TrainerReqDTO dto);

    ApiResponse updateTrainer(Long trainerId, TrainerReqDTO dto);

    ApiResponse deleteTrainer(Long trainerId);

    TrainerRespDTO getTrainerById(Long trainerId);
}
