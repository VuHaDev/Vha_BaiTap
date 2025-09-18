package com.example.baitap.controller;

import com.example.baitap.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.baitap.repository.EmployeeRepository;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Hiển thị tất cả nhân viên
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees/list";   // trỏ tới templates/employees/list.html
    }

    // Form thêm mới
    @GetMapping("/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/employee-form";  // trỏ tới templates/employees/employee-form.html
    }

    // Lưu nhân viên
    @PostMapping
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Integer id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("employee", employee);
        return "employees/employee-form";  // vẫn dùng form chung
    }

    // Đổi trạng thái
    @GetMapping("/toggle/{id}")
    public String toggleStatus(@PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employee.setStatus(!employee.isStatus());
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
}
