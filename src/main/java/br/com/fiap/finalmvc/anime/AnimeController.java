package br.com.fiap.finalmvc.anime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    AnimeService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("animes", service.findAll());
        return "anime/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", "Anime/Mangá apagado com sucesso!");
        }else{
            redirect.addFlashAttribute("error", "Anime/Mangá não foi encontrado...");
        }
        return "redirect:/anime";
    }

    @GetMapping("new")
    public String form(Anime anime){
        return "anime/form";
    }

    @PostMapping
    public String create(@Valid Anime anime, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "anime/form";
        service.save(anime);
        redirect.addFlashAttribute("success", "Anime/Mangá cadastrado com sucesso!");
        return "redirect:/anime";
    }
    
}