package pl.micsoc.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.micsoc.dictionary.model.Entry;
import pl.micsoc.dictionary.repository.EntryRepository;
import pl.micsoc.dictionary.repository.UserRepository;
import pl.micsoc.dictionary.service.CategoryService;

import java.sql.Date;

@Controller
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/add")
    public String addEntry(ModelMap modelMap) {

        modelMap.put("entry", new Entry());
        modelMap.put("categories", categoryService.allCategories());

        return "entryform";
    }


    @PostMapping("/add")
    public String entryAdded(@ModelAttribute("entry") Entry entry) {

        entry.setCategory(categoryService.findFromThymeleaf(entry.getSelectedCategory()));
        entry.setDate(new Date(System.currentTimeMillis()));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            entry.setUserEntry(userRepository.findByUserName(((UserDetails) principal).getUsername()));

        } else {
            entry.setUserEntry(userRepository.findByUserName(principal.toString()));

        }
        entryRepository.save(entry);

        return "entryadded";
    }


    @GetMapping("/show")
    public String showEntries(ModelMap modelMap) {

        modelMap.put("entries", entryRepository.findAll());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            modelMap.put("user", userRepository.findByUserName(((UserDetails) principal).getUsername()));
        } else {
            modelMap.put("user", userRepository.findByUserName(principal.toString()));
        }

        return "gallery";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntry(@PathVariable String id) {

        Long longId = Long.parseLong(id);

        entryRepository.delete(entryRepository.findEntryById(longId));

        return "redirect:/entry/show";

    }

    @GetMapping("/edit/{id}")
    public String editEntry(@PathVariable String id, ModelMap modelMap) {

        Long longid = Long.parseLong(id);

        modelMap.put("entry", entryRepository.findEntryById(longid));
        modelMap.put("categories", categoryService.allCategories());

        return "editEntry";
    }

    @PostMapping("/edit/{id}")
    public String entryEdited(@ModelAttribute Entry newEntry) {

        Entry entry = entryRepository.findEntryById(newEntry.getId());

        entry.setTitle(newEntry.getTitle());
        entry.setContent(newEntry.getContent());
        entry.setCategory(categoryService.findFromThymeleaf(newEntry.getSelectedCategory()));

        entryRepository.save(entry);

        return "redirect:/entry/show";
    }
}
