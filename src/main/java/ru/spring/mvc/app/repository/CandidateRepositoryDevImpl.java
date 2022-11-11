package ru.spring.mvc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.spring.mvc.app.model.Candidate;
import ru.spring.mvc.app.model.WorkingMode;
import ru.spring.mvc.app.repository.interfaces.CandidateRepository;
import ru.spring.mvc.app.repository.interfaces.WorkingModeRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.spring.mvc.app.util.DreamJobUtils.generateId;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateRepositoryDevImpl implements CandidateRepository<Long, Candidate> {

    private final Map<Long, Candidate> candidates = new ConcurrentHashMap<>();

    private final WorkingModeRepository<Long, WorkingMode> workingModeRepository;

    @Override
    @PostConstruct
    public void init() {
        long firstCandidateId = generateId();
        long secondCandidateId = generateId();
        long thirdCandidateId = generateId();
        candidates.put(firstCandidateId, new Candidate(firstCandidateId, "Senior Java Developer", "350.000 RUB",
                "description", workingModeRepository.getById(2L), new byte[]{}, LocalDateTime.now()));
        candidates.put(secondCandidateId, new Candidate(secondCandidateId, "Product Owner", "650.000 RUB",
                "description", workingModeRepository.getById(3L), new byte[]{}, LocalDateTime.now()));
        candidates.put(thirdCandidateId, new Candidate(thirdCandidateId, "Project Manager", "410.000 RUB",
                "description", workingModeRepository.getById(1L), new byte[]{}, LocalDateTime.now()));
    }

    @Override
    public Candidate add(Candidate candidate) {
        long id = generateId();
        candidate.setId(id);
        candidate.setCreated(LocalDateTime.now());
        return candidates.putIfAbsent(id, candidate);
    }

    @Override
    public List<Candidate> findAll() {
        return new ArrayList<>(candidates.values());
    }

    @Override
    public Candidate getById(Long id) {
        return candidates.get(id);
    }

    @Override
    public void update(Long id, Candidate candidate) {
        candidates.computeIfPresent(id, (idSaved, candidateUpdated) -> {
            candidateUpdated.setId(id);
            candidateUpdated.setCreated(candidateUpdated.getCreated());
            candidateUpdated.setName(candidate.getName());
            candidateUpdated.setDescription(candidate.getDescription());
            candidateUpdated.setSalary(candidate.getSalary());
            candidateUpdated.setWorkingMode(candidate.getWorkingMode());
            candidateUpdated.setPhoto(candidate.getPhoto());
            return candidateUpdated;
        });
    }

    @Override
    public void delete(Long id) {
        candidates.remove(id);
    }
}
