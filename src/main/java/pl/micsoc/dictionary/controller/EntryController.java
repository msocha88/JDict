package pl.micsoc.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.micsoc.dictionary.model.Entry;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.repository.EntryRepository;
import pl.micsoc.dictionary.repository.UserRepository;

@Controller
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @GetMapping("/add")
    public String addEntry(ModelMap modelMap) {

        modelMap.put("entry", new Entry());
        return "entryform";

    }


    @PostMapping("/add")
    public String entryAdded(@ModelAttribute("entry") Entry entry) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            entry.setUserEntry(userRepository.findByUserName(((UserDetails) principal).getUsername()));
        } else {
            String username = principal.toString();
        }

        entryRepository.save(entry);
        return "entryadded";
    }


    @GetMapping("/show")
    public String showEntries(ModelMap modelMap) {

        modelMap.put("entries", entryRepository.findAll());


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            modelMap.put("user",userRepository.findByUserName(((UserDetails) principal).getUsername()));
        } else {
            modelMap.put("user",userRepository.findByUserName(principal.toString()));
        }

        return "gallery";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntry(@PathVariable String id) {

//        System.out.println(id);
        Long longid = Long.parseLong(id);

        entryRepository.delete(entryRepository.findEntryById(longid));

        return "redirect:/entry/show";

    }
}