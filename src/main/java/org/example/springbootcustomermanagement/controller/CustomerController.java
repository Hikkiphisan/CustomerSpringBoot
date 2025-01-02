package org.example.springbootcustomermanagement.controller;

import org.example.springbootcustomermanagement.model.Customer;
import org.example.springbootcustomermanagement.model.Province;
import org.example.springbootcustomermanagement.service.ICustomerService;
import org.example.springbootcustomermanagement.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

//    @GetMapping("/create")
//    public ModelAndView showCreateForm() {
//        ModelAndView modelAndView = new ModelAndView("/customer/create");
//        modelAndView.addObject("customer", new Customer());
//        return modelAndView;
//    }



    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        // Lấy danh sách các tỉnh từ dịch vụ
        List<Province> provinces = (List<Province>) provinceService.findAll();

        // Tạo đối tượng ModelAndView với view là /customer/create
        ModelAndView modelAndView = new ModelAndView("/customer/create");

        // Thêm đối tượng customer mới vào model
        modelAndView.addObject("customer", new Customer());

        // Thêm danh sách các tỉnh vào model
        modelAndView.addObject("provinces", provinces);

        return modelAndView;
    }



    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }
    @GetMapping("/customers")
    public ModelAndView listCustomers() {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }


    @GetMapping("/update/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/update");

            // Lấy danh sách các tỉnh từ service (thay `provinceService.findAll()` bằng mã của bạn)
            List<Province> provinces = (List<Province>) provinceService.findAll(); // Giả sử bạn có `provinceService` để lấy danh sách các tỉnh

            // Thêm đối tượng customer và provinces vào model
            modelAndView.addObject("customer", customer.get());
            modelAndView.addObject("provinces", provinces);  // Thêm danh sách các tỉnh vào model

            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }



//    @GetMapping("/update/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Optional<Customer> customer = customerService.findById(id);
//        if (customer.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/customer/update");
//            modelAndView.addObject("customer", customer.get());
//            return modelAndView;
//        } else {
//            return new ModelAndView("/error_404");
//        }
//    }

    @PostMapping("/update")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/update");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }

}