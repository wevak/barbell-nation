package com.barbellnation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dao.PackageDao;
import com.barbellnation.dao.TrainerDao;
import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.TrainerReqDTO;
import com.barbellnation.dto.TrainerRespDTO;
import com.barbellnation.entities.Package;
import com.barbellnation.entities.Trainer;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {

    private final TrainerDao trainerDao;
    private final PackageDao packageDao;
    private final ModelMapper modelMapper;

    public TrainerServiceImpl(TrainerDao trainerDao, PackageDao packageDao, ModelMapper modelMapper) {
        this.trainerDao = trainerDao;
        this.packageDao = packageDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse addTrainer(TrainerReqDTO trainerDTO) {
        Package pkg = packageDao.findById(trainerDTO.getPackageId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid package ID"));

        Trainer trainer = modelMapper.map(trainerDTO, Trainer.class);
        trainer.setPackageId(pkg); // ✅ match entity field name

        trainerDao.save(trainer);
        return new ApiResponse("Trainer added successfully to the package.");
    }


    @Override
    public List<TrainerRespDTO> getAllTrainers() {
        return trainerDao.findAll().stream().map(trainer -> {
            TrainerRespDTO resp = modelMapper.map(trainer, TrainerRespDTO.class);
            resp.setPackageId(trainer.getPackageId().getPackageId()); // ✅ correct getter
            return resp;
        }).collect(Collectors.toList());
    }


    @Override
    public TrainerRespDTO getTrainerById(Long trainerId) {
        Trainer trainer = trainerDao.findById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with ID: " + trainerId));

        TrainerRespDTO resp = modelMapper.map(trainer, TrainerRespDTO.class);
        resp.setPackageId(trainer.getPackageId().getPackageId()); // ✅ set by ID
        return resp;
    }


    @Override
    public ApiResponse deleteTrainer(Long id) {
        Trainer trainer = trainerDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        trainerDao.delete(trainer);
        return new ApiResponse("Trainer deleted successfully.");
    }

    @Override
    public ApiResponse updateTrainer(Long trainerId, TrainerReqDTO dto) {
        Trainer trainer = trainerDao.findById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with ID: " + trainerId));

        Package pkg = packageDao.findById(dto.getPackageId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid package ID"));

        modelMapper.map(dto, trainer);
        trainer.setPackageId(pkg);

        return new ApiResponse("Trainer updated successfully.");
    }

}
