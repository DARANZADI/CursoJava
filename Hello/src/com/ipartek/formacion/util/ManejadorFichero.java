package com.ipartek.formacion.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public final class ManejadorFichero {

  private ManejadorFichero() {

  }

  /**
   * Procedimiento para leer un fichero caracter a caracter.
   * <p>
   * Devuelve @return <code>List</code> con texto leido.
   * </p>
   */
  public List<String> leerFichero() {
    List<String> texto = null;
    FileReader fr = null;
    // BufferedInputStream in = null;
    // FileInputStream fin = null;
    // c:\archivo.txt
    final String ruta = System.getProperty("user.dir") + System.getProperty("file.separator")
        + "file" + System.getProperty("file.separator") + "archivo.txt";
    File archivo = new File(ruta);
    String palabra = "";
    try {
      // caracter a caracter
      fr = new FileReader(archivo);
      int caracter;

      while ((caracter = fr.read()) != -1) {
        palabra += caracter;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fr != null) {
        try {
          fr.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    texto.add(palabra);
    return texto;
  }
  // TODO completar manejador fichero
}
