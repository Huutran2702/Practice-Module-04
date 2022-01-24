package com.codegym.managercity.controller;

import com.codegym.managercity.model.City;
import com.codegym.managercity.service.ICityService;
import com.codegym.managercity.service.INationService;
import com.codegym.managercity.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private INationService nationService;

    @GetMapping("/all")
    public ModelAndView getAllCity() {
        ModelAndView modelAndView = new ModelAndView("/list");
        List<City> cities = cityService.findAll();
        modelAndView.addObject("cities",cities);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView addNewCity() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("nations", nationService.findAll());
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView handlerAddNewCity(@Valid @ModelAttribute City city, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()) {
            modelAndView.setViewName("/create");
            modelAndView.addObject("city",city);
            modelAndView.addObject("nations", nationService.findAll());
            modelAndView.addObject("messages","Các trường dữ liệu không hợp lệ");
            return modelAndView;
        } else {
            cityService.save(city);
            modelAndView.setViewName("/list");
            List<City> cities = cityService.findAll();
            modelAndView.addObject("cities",cities);
            modelAndView.addObject("messages","Thêm thành phố mới thành công");
            return modelAndView;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        City city = cityService.findById(id);
        modelAndView.addObject("nations", nationService.findAll());
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateCity(@Valid @ModelAttribute City city, BindingResult bindingResult,@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()) {
            modelAndView.setViewName("/edit/"+id);
            modelAndView.addObject("city",city);
            modelAndView.addObject("nations", nationService.findAll());
            modelAndView.addObject("messages","Các trường dữ liệu không hợp lệ");
            return modelAndView;
        } else {
            city.setId(id);
            cityService.save(city);
            modelAndView.setViewName("/list");
            List<City> cities = cityService.findAll();
            modelAndView.addObject("cities",cities);
            modelAndView.addObject("messages","Sửa thành phố mới thành công");
            return modelAndView;
        }


    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeletePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/delete");
        City city = cityService.findById(id);
        modelAndView.addObject("nations", nationService.findAll());
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView updateCity(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
            cityService.remove(id);
            modelAndView.setViewName("/list");
            List<City> cities = cityService.findAll();
            modelAndView.addObject("cities",cities);
            modelAndView.addObject("messages","Đã xóa thành phố thành công");
            return modelAndView;

    }
}
