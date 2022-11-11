package ru.spring.mvc.app.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.spring.mvc.app.model.WorkingMode;
import ru.spring.mvc.app.repository.interfaces.WorkingModeRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.spring.mvc.app.repository.interfaces.Repository.isUnsupported;

@Repository
public class WorkingModeRepositoryDevImpl implements WorkingModeRepository<Long, WorkingMode> {

    @Value("${dev.store.size.capacity}")
    private int capacity;

    private final Map<Long, WorkingMode> modes = new ConcurrentHashMap<>(capacity);


    @Override
    @PostConstruct
    public void init() {
        modes.put(1L, new WorkingMode(1L, WorkingMode.Type.HYBRID));
        modes.put(2L, new WorkingMode(2L, WorkingMode.Type.IN_THE_OFFICE));
        modes.put(3L, new WorkingMode(3L, WorkingMode.Type.REMOTELY));
    }

    @Override
    public WorkingMode add(WorkingMode workingMode) {
        isUnsupported();
        return null;
    }

    @Override
    public List<WorkingMode> findAll() {
        return new ArrayList<>(modes.values());
    }

    @Override
    public WorkingMode getById(Long id) {
        return modes.get(id);
    }

    @Override
    public void update(Long id, WorkingMode workingMode) {
        isUnsupported();
    }

    @Override
    public void delete(Long aLong) {
        isUnsupported();
    }
}
