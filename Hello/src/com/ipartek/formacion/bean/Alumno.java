package com.ipartek.formacion.bean;

import com.ipartek.formacion.bean.excepciones.AlumnoException;
import com.ipartek.formacion.bean.excepciones.CursoException;
import com.ipartek.formacion.bean.interfaces.IMatriculable;
import com.ipartek.formacion.service.CursoService;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Clase de alumno que es la que puede asistir a los cursos.
 *
 * @author Administrador.
 */
public class Alumno extends Usuario implements IMatriculable, Comparable<Alumno> {
  /**
   * Constante para c�digo de alumno invalido.
   */
  public static final int CODIGOALUMNO = -1;
  /**
   * Constante para numero de hermanos validos.
   */
  public static final int NHERMANOS = 0;

  private String dni;
  private String nombre;
  private String apellidos;
  private Date fnacimiento;
  private int nhermanos;

  /**
   * Constructor en blanco de la clase Alumno.
   *
   * <code>@throws</code> una <code>Exception</code> de tipo <code>AlumnoException</code>
   */
  public Alumno() throws AlumnoException {
    setCodigoUsuario(Alumno.CODIGOALUMNO);
    setNombre("");
    setApellidos("");
    setfnacimiento(Calendar.getInstance().getTime());
    setnhermanos(0);
    setDni("");
  }

  /**
   * Es el constructor con paremetros de la clase Alumno.
   *
   * <p>
   * <code>int</code> @param codigoAlumno es el codigo del alumno.
   * </p>
   * <code>String</code> @param nombre es el nombre del alumno.
   * <p>
   * <code>String</code> @param apellidos es los apellidos del alumno.
   * </p>
   * <p>
   * <code>Date</code>@param fnacimiento es la fecha de nacimiento del alumnos.
   * </p>
   * </p>
   * <p>
   * <code>int</code> @param nhermanos son el numero de hermanos del alumno.
   * </p>
   *
   * <code>String</code>@param dni <code>@throws</code> una <code>Exception</code> de tipo
   * <code>AlumnoException</code>
   */
  public Alumno(final int codigoAlumno, final String nombre, final String apellidos,
      final Date fnacimiento, final int nhermanos, final String dni) throws AlumnoException {
    super();
    setCodigoUsuario(codigoAlumno);
    setNombre(nombre);
    setApellidos(apellidos);
    setfnacimiento(fnacimiento);
    setnhermanos(nhermanos);
    setDni(dni);
  }

  public String getDni() {
    return dni;
  }

  public void setDni(final String dni) {
    this.dni = dni;
  }

  /**
   *
   * @return <code>String</code> devuelve el nombre.
   */
  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setApellidos(final String apellidos) {
    this.apellidos = apellidos;
  }

  public Date getfnacimiento() {
    return fnacimiento;
  }

  /**
   * Metodo de poner la fecha de nacimiento del alumno.
   *
   * <code>Date</code>@param fnacimiento
   *
   * @throws <code>AlumnoException</code>
   */
  public void setfnacimiento(final Date fnacimiento) throws AlumnoException {

    Calendar cal = Calendar.getInstance();
    // fecha actual menos 18 a�os
    // GregorianCalendar cal2 = new GregorianCalendar();
    // cal2.add(Calendar.YEAR, -18);

    Date ahora = cal.getTime();
    // Date fechaMayoria = cal2.getTime();
    // compareTo
    // -1 ---> fNacimiento < ahora
    // 0 ---> fNacimiento == ahora
    // 1 ---> fNacimiento > ahora
    if (fnacimiento.compareTo(ahora) <= 0) {
      this.fnacimiento = fnacimiento;
    } else {
      throw new AlumnoException(AlumnoException.MSG_FECHANACIMIENTO_NO_VALIDA,
          AlumnoException.COD_FECHANACIMIENTO_NO_VALIDA);
    }
  }

  public int getnhermanos() {
    return nhermanos;
  }

  /**
   *
   * @param nhermanos
   * @throws AlumnoException
   */
  public void setnhermanos(final int nhermanos) throws AlumnoException {
    if (nhermanos >= Alumno.NHERMANOS) {
      this.nhermanos = nhermanos;
    } else {
      throw new AlumnoException(AlumnoException.MSG_NHERMANOS_NO_VALIDO,
          AlumnoException.COD_NHERMANOS_NO_VALIDO);
    }
  }

  /**
   *
   */
  @Override
  public void matricularCurso(final int codigoCurso) {
    CursoService cs = new CursoService();
    Curso curso = cs.getById(codigoCurso);
    Map<Integer, Alumno> listadoAlumnos = curso.getListadoAlumnos();
    listadoAlumnos.put(getCodigoUsuario(), this);
    curso.setListadoAlumnos(listadoAlumnos);
    cs.update(curso);
  }

  /**
   * Este metodo borra a un <code>Alumno</code> del <code>Map</code> de alumnos.
   */
  @Override
  public void desmatricularCurso(final int codigoCurso) {
    CursoService cs = new CursoService();
    Curso curso = cs.getById(codigoCurso);
    Map<Integer, Alumno> listadoAlumnos = curso.getListadoAlumnos();
    listadoAlumnos.remove(getCodigoUsuario());
    curso.setListadoAlumnos(listadoAlumnos);
    cs.update(curso);

  }

  /**
   * Clase <code>CursoMatriculado</code> hereda de
   * <code>Curso</curso>. Sirve para especificar los datos de la matricula del curso.
   *
   * @author va00
   *
   */
  public class CursoMatriculado extends Curso {
    Date fechaMatriculacion;
    Alumno alumno;

    /**
     *
     * @param alumno
     * @param fechaMatriculacion
     * @throws CursoException
     */
    public CursoMatriculado(final Alumno alumno, final Date fechaMatriculacion)
        throws CursoException {
      super();
      this.alumno = alumno;
      this.fechaMatriculacion = fechaMatriculacion;
    }

  }

  /**
   * Se implementa el metodo compara de la interfaz <code>Comparable</code> comparando nombres y
   * apellidos.
   */
  @Override
  public int compareTo(final Alumno alumno) {
    int resultado = 1;
    String apellido1 = alumno.getApellidos();
    String apellido2 = apellidos;

    resultado = apellido1.compareToIgnoreCase(apellido2);

    if (resultado != 0) {
      return resultado;
    } else {
      String nombre1 = alumno.getNombre();
      String nombre2 = getNombre();
      return nombre1.compareTo(nombre2);

    }
  }

  @Override
  public boolean equals(final Object obj) {
    Alumno al = null;
    boolean igual = false;
    if (obj instanceof Alumno) {
      al = (Alumno) obj;
      if (al.getCodigoUsuario() == getCodigoUsuario()) {
        igual = true;
      }

    } else {
      throw new UnsupportedOperationException();
    }
    return igual;
  }

}
