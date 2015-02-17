package com.cimait.invoicec.portal.core.controller;

import java.util.ArrayList;
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

import com.cimait.invoicec.core.config.GlobalConfig;
import com.cimait.invoicec.core.entity.General;
import com.cimait.invoicec.core.entity.User;
import com.cimait.invoicec.core.entity.UserRole;
import com.cimait.invoicec.core.entity.UserRolePK;
import com.cimait.invoicec.core.repository.CustomerRepository;
import com.cimait.invoicec.core.repository.GeneralRepository;
import com.cimait.invoicec.core.repository.UserRepository;
import com.cimait.invoicec.core.repository.UserRoleRepository;
import com.cimait.invoicec.portal.core.exception.UnAuthorizedException;
import com.cimait.invoicec.portal.core.exception.UserInfoException;
import com.cimait.invoicec.portal.core.exception.UserRoleInfoException;
import com.cimait.invoicec.portal.core.helpers.IdentificationTypeInfo;
import com.cimait.invoicec.portal.core.helpers.LoginCredentials;
import com.cimait.invoicec.portal.core.helpers.UserInfo;
import com.cimait.invoicec.portal.core.helpers.UserTypeInfo;
import com.cimait.invoicec.portal.core.security.AuthHelper;
import com.nimbusds.jose.JOSEException;

@Controller
public class UserController {
	
	@Autowired 
	protected GlobalConfig globalConfig;
	
	@Autowired
    protected UserRepository userRepository;

	//@Autowired
	//protected GeneralRepository generalRepository;
	
	@Autowired
	protected UserRoleRepository userRoleRepository;
	
	@Autowired
	protected CustomerRepository customerRepository;
	
	@Autowired
	protected AuthHelper authHelper;
	
	@Autowired
	protected GeneralRepository generalRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/user/list")
	public @ResponseBody List<UserInfo> getUserList(){
		List<UserInfo> lUsers = new ArrayList<UserInfo>();
		for (User user : userRepository.findAll()) {
			UserInfo uInfo = new UserInfo();
			uInfo.setCodUsuario(user.getCodUsuario());
			uInfo.setIsActive(user.getIsActive());
			uInfo.setNombre(user.getNombre());
			uInfo.setRuc(user.getRuc());
			uInfo.setRucEmpresa(user.getRucEmpresa());
			uInfo.setTipoUsuario(user.getTipoUsuario());
			//obtener role
			UserRole uRole = userRoleRepository.findByRucAndCodUsuario(user.getRuc(), user.getCodUsuario());
			if (uRole != null) {
				uInfo.setRole(uRole.getCodRol()); 
			} else {
				uInfo.setRole("");
			}
			lUsers.add(uInfo);
		}
		return lUsers;
	}
	
	/**
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/user")
	public UserInfo getUser( @RequestParam(value="id") String id, @RequestParam(value="type") String type) {
		User tmpUser = userRepository.findByRucAndCodUsuarioAndTipoUsuario(globalConfig.getGlobalId(), id, type);
		UserInfo userInfo = new UserInfo();
		//remover dato 
		if (tmpUser != null) { 
			userInfo.setCodUsuario(tmpUser.getCodUsuario());
			userInfo.setEmail(tmpUser.getEmail());
			userInfo.setIsActive(tmpUser.getIsActive());
			userInfo.setNombre(tmpUser.getNombre());
			userInfo.setRuc(tmpUser.getRuc());
			userInfo.setRucEmpresa(tmpUser.getRucEmpresa());
			userInfo.setTipoUsuario(tmpUser.getTipoUsuario());
			//capturo los correos de empresa
			
			CustomerPK custPK = new CustomerPK();
			custPK.setRuc(globalConfig.getGlobalId());
			custPK.setRucCliente(userInfo.getRucEmpresa().trim());
			Customer cust = customerRepository.findOne(custPK);
			
			if (cust == null) throw new EntityNotFoundException();
			
			userInfo.setEmails(cust.getEmail());
			
		}
		else {
			throw new EntityNotFoundException();
		}
		return userInfo;
	}
	**/
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/user")
	public void saveUser(@RequestBody UserInfo user) {
		try {
			//si el usuario existe actualizarlo
			User tmpUser = null;
			if (user.getRuc() == null) {
				tmpUser = userRepository.findByRucAndCodUsuarioAndTipoUsuario(globalConfig.getGlobalId(), user.getCodUsuario(), user.getTipoUsuario());
			} else {
				tmpUser = userRepository.findByRucAndCodUsuarioAndTipoUsuario(user.getRuc(), user.getCodUsuario(), user.getTipoUsuario());
			}
			
			if (tmpUser != null) {
				
				tmpUser.setEmail(user.getEmail());
				if (user.getIsActive() != null) tmpUser.setIsActive(user.getIsActive());
				if (user.getNombre() != null) tmpUser.setNombre(user.getNombre());
				
				tmpUser.setTipoUsuario(user.getTipoUsuario());
				//manejo de password
				if (user.getNewPassword()!= null) {
					if (!user.getNewPassword().trim().equals(user.getNewRepeatPassword().trim())) {
						System.out.println("Error password nuevo distinto!.");
						throw new UserInfoException();
					} else {
						if (!user.getPassword().trim().equals(tmpUser.getPassword().trim())) {
							System.out.println("Error password distinto!.");
							throw new UserInfoException();
						} else {
							tmpUser.setPassword(user.getNewPassword());
						}
					}	
				}
				userRepository.save(tmpUser);
				//verificar role 
				if (user.getRole() != null ) {
					//TODO: efectuar solo si la grabacion viene de "empresa"
					UserRole uRole = userRoleRepository.findByRucAndCodUsuario(user.getRuc(), user.getCodUsuario());
					//se elimina y se agrega, solo si 
					if (uRole != null) userRoleRepository.delete(uRole);
					
					uRole = new UserRole();
					uRole.setCodRol(user.getRole());
					uRole.setCodUsuario(user.getCodUsuario());
					uRole.setIsActive("Y");
					uRole.setRuc(user.getRuc());
					
					userRoleRepository.save(uRole);
				}
				
			} else {
				tmpUser = new User();
				tmpUser.setRuc(globalConfig.getGlobalId());
				tmpUser.setRucEmpresa(user.getRucEmpresa());
				tmpUser.setCodUsuario(user.getCodUsuario());
				tmpUser.setEmail(user.getEmail());
				tmpUser.setIsActive("Y");
				tmpUser.setNombre(user.getNombre());
				//TODO encriptar password
				if (user.getPassword() != null && user.getNewPassword() != null)
				if (user.getPassword().trim().equals(user.getNewPassword().trim())) {
					tmpUser.setPassword(user.getPassword());
					tmpUser.setRuc(user.getRuc());
					tmpUser.setRucEmpresa(user.getRucEmpresa());
					tmpUser.setTipoUsuario(user.getTipoUsuario());
					userRepository.save(tmpUser);
					if (user.getRole() != null ) {
						//TODO: efectuar solo si la grabacion viene de "empresa"
						UserRole uRole = userRoleRepository.findByRucAndCodUsuario(user.getRuc(), user.getCodUsuario());
						//se elimina y se agrega, solo si 
						if (uRole != null) userRoleRepository.delete(uRole);
						
						uRole = new UserRole();
						uRole.setCodRol(user.getRole());
						uRole.setCodUsuario(user.getCodUsuario());
						uRole.setIsActive("Y");
						uRole.setRuc(user.getRuc());
						
						userRoleRepository.save(uRole);
					}
					
					
				} else {
					System.out.println("Error passwords distintos!.");
					throw new UserInfoException();
				}	
			}
		}catch (Exception e){
			System.out.println("Error al grabar usuario :  " +  user.getCodUsuario());
			e.printStackTrace();
			throw new UserInfoException();
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/api/v1/user")
	public void deleteUser(@RequestParam(value="emitterId") String emitterId, @RequestParam(value="id") String id , @RequestParam(value="type") String type){
		User tmpUser = userRepository.findByRucAndCodUsuarioAndTipoUsuario(emitterId, id, type);
		if (tmpUser != null) {
			//se borra de la tabla usuarios_roles
			UserRole uRole = userRoleRepository.findByRucAndCodUsuario(emitterId, id);
			if (uRole != null) userRoleRepository.delete(uRole);

			//se borra usuario
			userRepository.delete(tmpUser);
		} else throw new EntityNotFoundException();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/api/v1/user/login")
	public @ResponseBody LoginCredentials get(@RequestBody LoginCredentials login, HttpServletRequest request) {
		String token ="";
		String inUserName = login.getUserName();
		String inPassword = login.getPassword();
		if (inUserName == null && inPassword == null) {
			throw new UnAuthorizedException();
		} else {
			if (inUserName != null && inPassword != null ) {
				User user = validateUserDB(login);
				if (user != null) {
					try {
						token = authHelper.createJsonWebToken(inUserName, request.getRemoteHost()+"(" + request.getRemoteAddr()+")",1L);
					} catch (JOSEException e) {
						e.printStackTrace();
					}
					//si login es de un empleado devuelvo su rol
					String role = null;
					if (login.getUserType().equals("E")) {
						UserRole usrRole = userRoleRepository.findByRucAndCodUsuarioAndIsActive(globalConfig.getGlobalId(), inUserName, "Y");
						if (usrRole != null) role = usrRole.getCodRol();
					}
					return new LoginCredentials(inUserName, "", token, user.getNombre(),login.getUserType(), role);
				} else {
					throw new UnAuthorizedException();
				}
			} else { 
				throw new UnAuthorizedException();
			}
		}
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/api/v1/user/role")
	public void saveUserRole(@RequestBody UserRole userRole) {
		UserRolePK userRolePK = new UserRolePK();
		userRolePK.setCodRol(userRole.getCodRol());
		userRolePK.setRuc(userRole.getRuc());
		userRolePK.setCodUsuario(userRole.getCodUsuario());
		
		UserRole tmpUserRole = userRoleRepository.findOne(userRolePK);
		try {
		if (tmpUserRole != null) {
			tmpUserRole.setIsActive(userRole.getIsActive());
			userRoleRepository.save(tmpUserRole);
		} else {
			tmpUserRole = new UserRole();
			tmpUserRole.setCodRol(userRole.getCodRol());
			tmpUserRole.setCodUsuario(userRole.getCodUsuario());
			tmpUserRole.setIsActive(userRole.getIsActive());
			tmpUserRole.setRuc(userRole.getRuc());
			userRoleRepository.save(tmpUserRole);
		}
		} catch (Exception e) {
			System.out.println("Error grabando UserRole");
			throw new UserRoleInfoException();
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/api/v1/user/role")
	public void deleteUserRole(@RequestBody UserRole userRole) {
		UserRolePK userRolePK = new UserRolePK();
		userRolePK.setCodRol(userRole.getCodRol());
		userRolePK.setRuc(userRole.getRuc());
		userRolePK.setCodUsuario(userRole.getCodUsuario());
		
		UserRole tmpUserRole = userRoleRepository.findOne(userRolePK);
		if (tmpUserRole != null ) {
			userRoleRepository.delete(tmpUserRole);
		} else {
			System.out.println("Error al eliminar asociacion usuario - role " + userRole.getCodUsuario() + " " + userRole.getCodRol());
			throw new UserRoleInfoException();
		}
	}
	
	/*
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/user/types")
	public @ResponseBody List<UserTypeInfo> getUserTypes() {
	
		/**List<UserTypeInfo> lUserTypes = new ArrayList<UserTypeInfo>();
		List<General> lGeneral = generalRepository.findByCodTabla("1");
		for (General general : lGeneral) {
			UserTypeInfo idType = new UserTypeInfo();
			idType.setCodigo(general.getCodUnico());
			idType.setDescripcion(general.getDescripcion());
			lUserTypes.add(idType);
		}
		return lUserTypes;**/
		
	/*	List<UserTypeInfo> lUserTypes = new ArrayList<UserTypeInfo>();
		List<General> lGeneral = generalRepository.findByCodTabla("1");
		//List<General> lGeneral = (List<General>)generalRepository.findAll();
		for (General general : lGeneral) {
			IdentificationTypeInfo idType = new IdentificationTypeInfo();
			idType.setCodigo(general.getValue());
			idType.setDescripcion(general.getDescripcion());
			lUserTypes.add(idType)
		
		UserTypeInfo idType1 = new UserTypeInfo();
		idType1.setCodigo("E");
		idType1.setDescripcion("EMPLEADO");
		UserTypeInfo idType2 = new UserTypeInfo();
		idType2.setCodigo("C");
		idType2.setDescripcion("CLIENTE");
		UserTypeInfo idType3 = new UserTypeInfo();
		idType3.setCodigo("P");
		idType3.setDescripcion("PROVEEDOR");
		lUserTypes.add(idType1);
		lUserTypes.add(idType2);
		lUserTypes.add(idType3);
		return lUserTypes;
	}*/
	
	
	public User validateUserDB(LoginCredentials login) {
		try {
			User usr = userRepository.findByRucAndCodUsuarioAndTipoUsuario(globalConfig.getGlobalId(), login.getUserName(), login.getUserType());
			if (usr == null) {
				System.out.println("Usuario :" + login.getUserName() + " no encontrado.");
				return null;
			} else {
				//TODO encriptar password
				if (usr.getPassword().equals(login.getPassword()) && usr.getIsActive().equals("Y")) {
					return usr;
				} else { 
					System.err.println("password incorrecto o usuario inactivo : " + login.getUserName());
					return null;
				}
			}
	    }catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
