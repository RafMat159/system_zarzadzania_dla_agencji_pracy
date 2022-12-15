package com.company.system_zarzadzania_dla_agencji_pracy.controller;

import com.company.system_zarzadzania_dla_agencji_pracy.model.entity.Administrator;
import com.company.system_zarzadzania_dla_agencji_pracy.model.entity.Employer;
import com.company.system_zarzadzania_dla_agencji_pracy.model.request.EmployeeRQ;
import com.company.system_zarzadzania_dla_agencji_pracy.model.request.OrderRQ;
import com.company.system_zarzadzania_dla_agencji_pracy.repository.EmployerRepository;
import com.company.system_zarzadzania_dla_agencji_pracy.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/pracodawca")
public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }


    @GetMapping("/home")
    public String getHomePage(){
        return "home-page";
    }

    @GetMapping("/dodaj-zlecenie-form")
    public String getOrderForm(Model model){
        OrderRQ orderRQ = new OrderRQ();
        model.addAttribute("orderRQ",orderRQ);
        return "employer/add-order";
    }

    @PostMapping("/dodaj-zlecenie")
    public String addNewOrder(@Valid @ModelAttribute("orderRQ") OrderRQ orderRQ, BindingResult bindingResult, Model model, Principal principal){

        if(bindingResult.hasErrors()){
            return "employer/add-order";
        }

        Optional<Employer> employerOpt = employerService.checkIfEmployerIsPresent(principal.getName());

        if(employerOpt.isPresent()){               //sprawdzenie czy istnieje pracodawca, jesli nie to nie mozna dodac zlecenia
            Employer employer = employerOpt.get();
            employerService.addNewOrder(orderRQ,employer);
            return "redirect:/pracodawca/dodaj-zlecenie-form";
        }

        return "employer/add-order";
    }


}
