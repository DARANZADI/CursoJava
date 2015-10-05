package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.bean.Alumno;
import com.ipartek.formacion.bean.Usuario;
import com.ipartek.formacion.service.AlumnoService;
import com.ipartek.formacion.service.IAlumnoService;
import com.ipartek.formacion.util.Constantes;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = null;
		String password = null;
		//recogemos parametros
		
		user = request.getParameter(Constantes.PAR_USUARIO);
		password  = request.getParameter(Constantes.PAR_PASSWORD);
		
		
		//validarlos
		if(validarUsuarios(user, password)){
			//crearmos session
			HttpSession sesion = request.getSession(true);
			//redirigir
			Usuario usuario =  new Usuario();
			usuario.setUserName(user);
			usuario.setPassword(password);
			sesion.setAttribute(Constantes.ATT_USUARIO, usuario);
			//mostar el total de los alumnos
			IAlumnoService as = new AlumnoService();
			List<Alumno> alumnos = as.getAll();
			RequestDispatcher dispatcher = null;
			dispatcher = request.getRequestDispatcher(Constantes.PAGINA_INICIAL);
			request.setAttribute(Constantes.ATT_LISTADO_ALUMNOS, alumnos);
			//response.sendRedirect(Constantes.PAGINA_INICIAL);
			dispatcher.forward(request, response);
		}else{
			//redirigir
			RequestDispatcher dispatcher = null;
			dispatcher = request.getRequestDispatcher(Constantes.PAGINA_LOGIN);
			request.setAttribute(Constantes.ATT_MENSAJE, "usuario o contrase�a incorrectos");
			//redirecionamos
			dispatcher.forward(request, response);
		}
		
		
	}
	private boolean validarUsuarios(String user, String password){
		boolean valido = false;
		//user.equals("urko");
		if(user!=null&&password!=null&&user.equals("urko")&&password.equals("urko")){
			valido = true;
		}
		
		return valido;
	}
	
	
	
	
	
	
	
	
	
}