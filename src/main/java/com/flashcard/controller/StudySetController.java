package com.flashcard.controller;

import com.flashcard.dto.StudySetDTO;
import com.flashcard.dto.UserDTO;
import com.flashcard.service.studyset.StudySetService;
import com.flashcard.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/study-sets")
public class StudySetController {
    @Autowired
    private StudySetService studySetService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listStudySets(Model model) {
        Long userId = 1L; // handle later
        model.addAttribute("studySets", studySetService.findByUserId(userId));
        return "study-sets";
    }

    @GetMapping("/add")
    public String createStudySetForm(Model model) {
        Long userId = 1L; // handle later
        UserDTO user = userService.findById(userId);
        StudySetDTO studySet = StudySetDTO.builder().user(user).build();
        model.addAttribute("studySet", studySet);
        return "add-study-set";
    }

    @PostMapping("/add")
    public String saveStudySet(@Valid @ModelAttribute("studySet") StudySetDTO studySet,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "add-study-set";
        }
        studySetService.add(studySet);
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String editStudySetForm(@RequestParam(value = "studySetId") Long studySetId, Model model) {
        Long userId = 1L; // handle later
        StudySetDTO studySet = studySetService.findById(studySetId);
        if (userId == studySet.getUser().getUserId()) {
            model.addAttribute("studySet", studySet);
            return "edit-study-set";
        }
        return "error-page";
    }

    @PostMapping("/edit")
    public String updateStudySet(@Valid @ModelAttribute("studySet") StudySetDTO studySet,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "edit-study-set";
        }
        studySetService.update(studySet);
        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String deleteStudySet(@RequestParam(value = "studySetId") Long studySetId) {
        StudySetDTO studySet = studySetService.findById(studySetId);
        studySetService.delete(studySet);
        return "redirect:/list";
    }
}
