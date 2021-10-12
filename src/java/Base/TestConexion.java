
package Base;

import Modelo.Dto.MenuSistemaDTO;


public class TestConexion {
    private Conexion conexion;
    private MenuSistemaDTO dto;
    
    
    public TestConexion() {
        conexion = new Conexion();
        dto = new MenuSistemaDTO();
        dto.setId(1233);
        dto.setDescrip("dasdas");
        dto.setComentario("adsasdsadas");
    }


    public static void main(String[] args) {
        new TestConexion();
    }
    
}
