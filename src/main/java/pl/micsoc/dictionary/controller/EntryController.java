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
import pl.micsoc.dictionary.service.EntryService;
import pl.micsoc.dictionary.service.UserService;

import java.sql.Date;

@Controller
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    EntryService entryService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/add")
    public String addEntry(ModelMap modelMap) {

        modelMap.put("entry", new Entry());
        modelMap.put("categories", categoryService.allCategories());

        return "entry/entryform";
    }


    @PostMapping("/add")
    public String entryAdded(@ModelAttribute("entry") Entry entry) {

        entryService.addEntry(entry);

        return "entry/entryadded";
    }


    @GetMapping("/show")
    public String showEntries(ModelMap modelMap) {

        modelMap.put("categories", categoryService.allCategories());
        modelMap.put("entries", entryRepository.findAll());
        modelMap.put("user", userService.currentUser());

        return "entry/allEntries";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntry(@PathVariable String id) {


        entryRepository.delete(entryRepository.findEntryById(Integer.valueOf(id)));

        return "redirect:/entry/show";

    }

    @GetMapping("/edit/{id}")
    public String editEntry(@PathVariable String id, ModelMap modelMap) {


        modelMap.put("entry", entryRepository.findEntryById(Integer.valueOf(id)));
        modelMap.put("categories", categoryService.allCategories());

        return "entry/editEntry";
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

    @GetMapping("/category/{id}")
    public String entriesByCategory(@PathVariable String id, ModelMap modelMap) {

        Long longid = Long.parseLong(id);

        modelMap.put("categories", categoryService.allCategories());
        modelMap.put("entries", entryService.findByCategoryId(longid));


        return "entry/entryFromCategory";
    }
}
