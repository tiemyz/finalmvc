package br.com.fiap.finalmvc.task;

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
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("tasks", service.findAll());
        return "task/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if(service.delete(id)){
            redirect.addFlashAttribute("success", "Tarefa apagada com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Tarefa não foi encontrada");
        }
        return "redirect:/task";
    }

    @GetMapping("new")
    public String form(Task task){
        return "task/form";
    }

    @PostMapping
    public String create(@Valid Task task, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "task/form";
        service.save(task);
        redirect.addFlashAttribute("success", "Tarefa cadastrada com sucesso");
        return "redirect:/task";
    }
    
}