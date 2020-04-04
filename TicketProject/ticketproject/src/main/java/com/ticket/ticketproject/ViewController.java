package com.ticket.ticketproject;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping("/client-form")
    public String LoadForm(Model model) {
        model.addAttribute("form", new FormData());
        return "client-form";
    }

    @PostMapping("/client-form")
    public String submitForm(@ModelAttribute("form") FormData form) {
        return "confirmation";
    }
}
