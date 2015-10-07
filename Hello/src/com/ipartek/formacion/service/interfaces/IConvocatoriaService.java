package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.bean.Convocatoria;

public interface IConvocatoriaService {
  /**
   * 
   * @return
   */
  public List<Convocatoria> getAll();

  /**
   * 
   * @param solicitud
   * @return
   */
  public int create(Convocatoria solicitud);

  public Convocatoria getById(int codigoSolicitud);

  public int update(Convocatoria solicitud);

  public boolean delete(int codigoSolicitud);

}
