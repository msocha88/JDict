package pl.micsoc.dictionary.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.micsoc.dictionary.model.Category;
import pl.micsoc.dictionary.service.CategoryService;

import java.util.Comparator;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String modPanel() {

        return "moderator/modPanel";
    }

    @GetMapping("/categories")
    public String categories(ModelMap modelMap) {

        modelMap.put("categories", categoryService.allCategories());
        modelMap.put("newCat", new Category());
        modelMap.put("insertCategory", new Category());

        return "moderator/modCategories";
    }

    @PostMapping("/categories/addnew")
    public String addNewCategory(@ModelAttribute(name = "insertCategory") Category category) {

        categoryService.save(category);
        return "redirect:/moderator/categories";
    }

    @PostMapping("/categories/change/{id}")
    public String changeCategory(@ModelAttribute("newCat") Category category, @PathVariable String id) {



        categoryService.update(id,category);

        return "redirect:/moderator/categories";
    }
}
