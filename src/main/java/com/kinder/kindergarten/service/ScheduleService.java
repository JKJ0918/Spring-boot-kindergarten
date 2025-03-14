package com.kinder.kindergarten.service;

import com.kinder.kindergarten.DTO.ScheduleDTO;
import com.kinder.kindergarten.entity.ScheduleEntity;
import com.kinder.kindergarten.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;
  private final ModelMapper modelMapper;

  // 일정 목록 조회
  @Transactional(readOnly = true)
  public List<ScheduleDTO> getAllSchedules() {
    List<ScheduleEntity> schedules = scheduleRepository.findAll();
    return schedules.stream()
            .map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
            .collect(Collectors.toList());
  }

  // 일정 단건 조회
  @Transactional(readOnly = true)
  public ScheduleDTO getSchedule(String id) {
    ScheduleEntity schedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));
    return modelMapper.map(schedule, ScheduleDTO.class);
  }

  // 일정 생성
  public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
    ScheduleEntity schedule = modelMapper.map(scheduleDTO, ScheduleEntity.class);
    schedule.setScheduleId(UUID.randomUUID().toString());

    ScheduleEntity savedSchedule = scheduleRepository.save(schedule);
    return modelMapper.map(savedSchedule, ScheduleDTO.class);
  }

  // 일정 수정
  public ScheduleDTO updateSchedule(String id, ScheduleDTO scheduleDTO) {
    ScheduleEntity schedule = scheduleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다."));

    // 기존 일정 정보 업데이트
    schedule.setScheduleTitle(scheduleDTO.getScheduleTitle());
    schedule.setScheduleContent(scheduleDTO.getScheduleContent());
    schedule.setLocation(scheduleDTO.getLocation());
    schedule.setStart(scheduleDTO.getStart());
    schedule.setEnd(scheduleDTO.getEnd());

    ScheduleEntity updatedSchedule = scheduleRepository.save(schedule);
    return modelMapper.map(updatedSchedule, ScheduleDTO.class);
  }

  // 일정 삭제
  public void deleteSchedule(String id) {
    scheduleRepository.deleteById(id);
  }
}
