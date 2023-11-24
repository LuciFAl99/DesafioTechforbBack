package com.Techforb.DesafioTecnico.services.implement;

import com.Techforb.DesafioTecnico.DTOs.MenuDTO;
import com.Techforb.DesafioTecnico.repositories.MenuRepository;
import com.Techforb.DesafioTecnico.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MenuServiceImplements implements MenuService {
    @Autowired
    MenuRepository menuRepository;
    @Override
    public List<MenuDTO> getMenu() {
        return menuRepository.findAll().stream().map(menu -> new MenuDTO(menu)).collect(Collectors.toList());
    }
}
