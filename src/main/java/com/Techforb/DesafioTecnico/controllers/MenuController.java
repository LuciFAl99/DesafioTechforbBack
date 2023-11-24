package com.Techforb.DesafioTecnico.controllers;

import com.Techforb.DesafioTecnico.DTOs.MenuDTO;
import com.Techforb.DesafioTecnico.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    MenuService menuService;
    @GetMapping("/api/menu")
    public List<MenuDTO> getMenu(){
        return menuService.getMenu();
    }
}
