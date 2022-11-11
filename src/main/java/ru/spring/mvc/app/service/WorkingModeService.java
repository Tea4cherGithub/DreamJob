package ru.spring.mvc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.mvc.app.model.WorkingMode;
import ru.spring.mvc.app.repository.interfaces.WorkingModeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkingModeService {

    private final WorkingModeRepository<Long, WorkingMode> workingModeRepository;

    public List<WorkingMode> getAllWorkingModes() {
        return workingModeRepository.findAll();
    }

    public WorkingMode getById(Long id) {
        return workingModeRepository.getById(id);
    }
}
