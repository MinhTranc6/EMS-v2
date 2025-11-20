package com.example.EMS_v2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    //refers to the employees_dashboard.html
    @GetMapping({"/", "/dashboard"})
    public String employeesDashboard (Model model) {
        return "employees_dashboard";
    }
}
