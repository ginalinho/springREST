package com.demo.springbootemployeeapp.rest;

import com.demo.springbootemployeeapp.entity.Employee;
import com.demo.springbootemployeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getAll(Model model){

        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees", employees);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model model){
        Employee employee = employeeService.findById(theId);

        model.addAttribute("employee", employee);

        return "employee-form";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }
}
