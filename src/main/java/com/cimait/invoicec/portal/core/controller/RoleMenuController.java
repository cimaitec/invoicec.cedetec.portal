package com.cimait.invoicec.portal.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Customer;
import com.cimait.invoicec.core.entity.Menu;
import com.cimait.invoicec.core.entity.Role;
import com.cimait.invoicec.core.entity.RoleMenu;
import com.cimait.invoicec.core.entity.RoleMenuPK;
import com.cimait.invoicec.core.repository.MenuRepository;
import com.cimait.invoicec.core.repository.RoleMenuRepository;
import com.cimait.invoicec.portal.core.dto.CustomerDto;
import com.cimait.invoicec.portal.core.dto.MenuDto;
import com.cimait.invoicec.portal.core.helpers.Formatting;

@Controller
public class RoleMenuController {

	@Autowired
	protected RoleMenuRepository roleMenuRepository;
	
	@Autowired
	protected MenuRepository menuRepository;
	
	protected Role role;
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/menu/list")
	public @ResponseBody List<MenuDto> getAll() {
		List<Menu> menues = (List<Menu>)menuRepository.findAll();		
		//List<RoleMenu> roleMenulist = (List<RoleMenu>)roleMenuRepository.findAll();	
		List<MenuDto> menuDtos = new ArrayList<MenuDto>();
		for (Menu menu : menues) {
			menuDtos.add(convertToDto(menu,null));
		}
		return menuDtos;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/loadRoleMenu")
	public @ResponseBody List<MenuDto> getRoleMenu(@RequestBody Role role) {
		List<Menu> menues = (List<Menu>)menuRepository.findAll();		
		List<RoleMenu> roleMenulist = findListRoleMenu(role.getCodRol());	
		List<MenuDto> menuDtos = new ArrayList<MenuDto>();
		for (Menu menu : menues) {
			menuDtos.add(convertToDto(menu,roleMenulist));
		}
		return menuDtos;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/menu/listRoleMenu")
	public @ResponseBody List<RoleMenu> findListRoleMenu(@RequestParam(value="codRol") String codRol){
		List<RoleMenu> listRoleMenu =  roleMenuRepository.findByCodRoleMenu(codRol);
		return listRoleMenu;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/saveListRoleMenu")
	public @ResponseBody String saveMenu(@RequestBody List<MenuDto> menuDtos, HttpServletRequest request) {
		try {
			List<RoleMenu> roleMenulist = findListRoleMenu(role.getCodRol());
			roleMenuRepository.delete(roleMenulist);
			List<RoleMenu> roleMenu = new ArrayList<RoleMenu>();
			
			for (MenuDto menuDto : menuDtos) {
				if(menuDto.isSeleccionado()==true){
					RoleMenu roleMenuActive = new RoleMenu();
					roleMenuActive.setCodOpcionMenu(menuDto.getCodOpcionMenu());
					roleMenuActive.setCodRol(role.getCodRol());
					roleMenu.add(roleMenuActive);
				}
			}
			roleMenuRepository.save(roleMenu);
			
		} catch (Exception e) {/*
			System.out.println("Error al grabar role " + role.getCodRol());*/
			e.printStackTrace();
			// throw new RoleInfoException();
		}
		
		return "OK";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/setRole")
	public @ResponseBody String setRole(@RequestBody Role role, HttpServletRequest request) {
		try {
			this.role = role;
			
		} catch (Exception e) {
			System.out.println("Error al setear variable role con " + role.getCodRol());
			e.printStackTrace();
		}
		
		return "OK";
	}

	
	
	
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/menu/listCodPadre")
	public @ResponseBody List<Menu> findListMenu(@RequestParam(value="codMenuPadre") String codMenuPadre){
		List<Menu> listMenu = menuRepository.findByCodPadre(codMenuPadre);
		return listMenu;
	}
	
	
	private MenuDto convertToDto(Menu in, List<RoleMenu> roleMenuList) {
		MenuDto menuDto = new MenuDto();
		menuDto.setCodOpcionMenu(in.getCodOpcionMenu());
		menuDto.setCodOpcionMenuPadre(in.getCodOpcionMenuPadre());
		menuDto.setDescripcion(in.getDescripcion());
		menuDto.setSeleccionado(false);
		for (RoleMenu roleMenu : roleMenuList) {
			if(roleMenu.getCodOpcionMenu().trim().equalsIgnoreCase(in.getCodOpcionMenu().trim())){
				menuDto.setSeleccionado(true);
				break;
			};
		}
		return menuDto;
	}
	
	
	
}
