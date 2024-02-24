package com.flashcard.controller;

import com.flashcard.dto.StudySetDTO;
import com.flashcard.entity.StudySet;
import com.flashcard.service.studyset.StudySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/study-sets")
public class StudySetController {
    @Autowired
    private StudySetService studySetService;

    @GetMapping("")
    public String listStudySetsByUserId(@RequestParam(value = "userId") Long userId ,Model model) {
        model.addAttribute("study_sets", studySetService.findByUserId(userId));
        return "study_sets";
    }

    @GetMapping("/add")
    public String createStudySetForm(Model model) {
        StudySet studySet = new StudySet();
        model.addAttribute("study-set", studySet);
        return "add_study_set";

    }

    @GetMapping("/edit")
    public String editStudySetForm(@RequestParam(value = "studySetId") Long studySetId, Model model) {
        StudySetDTO studySet = studySetService.findById(studySetId);
        model.addAttribute("study-set", studySet);
        return "edit_study_set";
    }

    @GetMapping("/{id}")
    public String deleteStudySet(@PathVariable Long id) {
        StudySetDTO studySet = studySetService.findById(id);
        studySetService.delete(studySet);
        return "redirect:/study-sets";
    }

    @PostMapping("")
    public String saveStudySet(@ModelAttribute("studySet") StudySetDTO studySet) {
        studySetService.add(studySet);
        return "redirect:/study-sets";
    }

    @PostMapping("/{id}")
    public String updateStudySet(@PathVariable Long id,
                                 @ModelAttribute("study_set") StudySet studySet,
                                 Model model) {
        StudySetDTO existingStudySet = studySetService.findById(id);
        existingStudySet.setDescription(studySet.getDescription());

        studySetService.update(existingStudySet);
        return "redirect:/study-sets";
    }
}
