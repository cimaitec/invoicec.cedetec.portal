package com.cimait.invoicec.portal.core.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cimait.invoicec.core.entity.Role;
import com.cimait.invoicec.core.entity.RoleMenu;
import com.cimait.invoicec.core.entity.RoleMenuPK;
import com.cimait.invoicec.core.repository.MenuRepository;
import com.cimait.invoicec.core.repository.RoleMenuRepository;
import com.cimait.invoicec.core.repository.RoleRepository;

@Controller
public class RoleController {

	@Autowired
	protected RoleRepository roleRepository;
	
	@Autowired
	protected MenuRepository menuRepository;
	
	@Autowired
	protected RoleMenuRepository roleMenuRepository;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/role")
	public @ResponseBody Role get(@RequestParam(value="id") String id) {
		Role tmpRole = roleRepository.findOne(id);
		if (tmpRole != null) {
			return tmpRole;
		} else {
			System.out.println("Error role no encontrado " + id);
			throw new EntityNotFoundException();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/role/list")
	public @ResponseBody List<Role> getAll() {
		return (List<Role>) roleRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/role")
	public @ResponseBody String saveRole(@RequestBody Role role, HttpServletRequest request){
		Role tmpRole = roleRepository.findOne(role.getCodRol());
		try {
		if (tmpRole != null) {
			tmpRole.setDescripcion(role.getDescripcion());
			tmpRole.setIsActive(role.getIsActive());
			roleRepository.save(tmpRole);
		} else {
			tmpRole = new Role();
			tmpRole.setCodRol(role.getCodRol());
			tmpRole.setDescripcion(role.getDescripcion());
			tmpRole.setIsActive(role.getIsActive());
			roleRepository.save(tmpRole);
		}
		} catch (Exception e) {
			System.out.println("Error al grabar role " + role.getCodRol());
			e.printStackTrace();
			//throw new RoleInfoException();
		}
		return "OK";
			
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/api/v1/role")
	public @ResponseBody String deleteRole(@RequestParam(value="id") String id) {
		Role tmpRole = roleRepository.findOne(id);
		if (tmpRole != null) {
			//TODO borra solo si no tiene asignado ningun menu. 
			roleRepository.delete(tmpRole);
		} else {
			throw new EntityNotFoundException();
		}
		return "OK";
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/role/menu")
	public void saveRoleMenu(@RequestBody RoleMenu roleMenu) {
		RoleMenuPK roleMenuPK = new RoleMenuPK();
		roleMenuPK.setCodRol(roleMenu.getCodRol());
		roleMenuPK.setCodOpcionMenu(roleMenu.getCodOpcionMenu());
		
		RoleMenu tmpRoleMenu = roleMenuRepository.findOne(roleMenuPK);
		
		if (tmpRoleMenu != null) {
			System.out.println("Error asociacion Rol - menu ya existe" + roleMenu.getCodRol() + " " + roleMenu.getCodOpcionMenu());
			//throw new RoleInfoException();
		} else {
			roleMenuRepository.save(tmpRoleMenu);
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/api/v1/role/menu")
	public void deleteRoleMenu(@RequestBody RoleMenu roleMenu) {
		RoleMenuPK roleMenuPK = new RoleMenuPK();
		roleMenuPK.setCodRol(roleMenu.getCodRol());
		roleMenuPK.setCodOpcionMenu(roleMenu.getCodOpcionMenu());
		
		RoleMenu tmpRoleMenu = roleMenuRepository.findOne(roleMenuPK);
		
		if (tmpRoleMenu != null) {
			//TODO : eliminar role solo si no tiene usuarios asignados.
			
			roleMenuRepository.delete(tmpRoleMenu);
		} else {
			System.out.println("Error asociacion Rol - Menu no existe " + roleMenu.getCodRol() + " " + roleMenu.getCodOpcionMenu());
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/role/menu")
	public @ResponseBody List<RoleMenu> getRoleMenuAll() {
		return (List<RoleMenu>) roleMenuRepository.findAll();
	}
	
	
}
