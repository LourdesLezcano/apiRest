
package Modelo.Dao;

import Base.BaseSQL;
import Modelo.Dto.PerfilDTO;


public interface PerfilDAO extends BaseSQL<PerfilDTO>{
    public boolean existePerfilADMIN();
   public boolean anularPerfil();
    
    
}
