package ru.spring.mvc.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.mvc.app.model.Candidate;
import ru.spring.mvc.app.service.CandidateService;
import ru.spring.mvc.app.service.WorkingModeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/candidates")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateController {

    private final CandidateService candidateService;
    private final WorkingModeService workingModeService;

    @GetMapping
    public ModelAndView getAllCandidates() {
        return new ModelAndView("candidates", Map.of("candidates", candidateService.getAllCandidates()));
    }

    @GetMapping("/addCandidate")
    public ModelAndView addCandidate() {
        return new ModelAndView("add-candidate", Map.of(
                "candidate", new Candidate(),
                "modes", workingModeService.getAllWorkingModes()));
    }

    @PostMapping("/addCandidate")
    public void addCandidateSubmit(@ModelAttribute("candidate") Candidate candidate,
                                   @RequestParam("file") MultipartFile multipartFile,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {

        var mode = workingModeService.getById(candidate.getWorkingMode().getId());
        candidate.setWorkingMode(mode);
        candidate.setPhoto(multipartFile.getBytes());
        candidateService.addCandidate(candidate);
        response.sendRedirect(String.format("%s/candidates", request.getContextPath()));
    }

    @GetMapping("/photoCandidate/{candidateId}")
    public ResponseEntity<Resource> loadPhoto(@PathVariable("candidateId") Long id) {
        var rsl = candidateService.getCandidateById(id);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(rsl.getPhoto().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(rsl.getPhoto()));
    }

    @GetMapping("/updateCandidate/{candidateId}")
    public ModelAndView updateCandidate(@PathVariable("candidateId") Long id) {
        var candidate = candidateService.getCandidateById(id);
        return new ModelAndView("update-candidate", Map.of(
                "candidate", candidate,
                "modes", workingModeService.getAllWorkingModes()));
    }

    @PostMapping("/updateCandidate/{candidateId}")
    public void updateCandidateSubmit(@ModelAttribute("candidate") Candidate candidate,
                                      @PathVariable("candidateId") Long id,
                                      @RequestParam("file") MultipartFile multipartFile,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException {

        candidate.setPhoto(multipartFile.getBytes());
        candidate.setWorkingMode(workingModeService.getById(candidate.getWorkingMode().getId()));
        candidateService.updateCandidate(id, candidate);
        response.sendRedirect(String.format("%s/candidates", request.getContextPath()));
    }

    @GetMapping("/deleteCandidate/{candidateId}")
    public void deleteCandidate(@PathVariable("candidateId") Long id,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        candidateService.deleteCandidate(id);
        response.sendRedirect(String.format("%s/candidates", request.getContextPath()));
    }
}
