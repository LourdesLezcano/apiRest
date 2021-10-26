package Demo;

import Modelo.Dao.PerfilDAO;
import Modelo.Dao.UsuarioDAO;
import Modelo.DaoImp.PerfilDAOIMP;
import Modelo.Dto.MenuItemSistemaDTO;
import Modelo.Dto.PerfilDTO;
import Modelo.Dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;

public class Test {

//    private UsuarioDAO dao;
//    private UsuarioDTO dto;
    private PerfilDTO dto;
    private PerfilDAO dao;
    private MenuItemSistemaDTO menuItemDTO;
    private List<MenuItemSistemaDTO> permisos;

    public Test() {
//        //Para insertar datos en la BD -----------------------------------------------
//        dto = new UsuarioDTO(); //se instancia y transporta los datos
//        dto.setUsuario("prueba3");
//        dto.setClave("12");
//        dto.setPerfil(new PerfilDTO(2));
//        dto.setPersona(new PersonaDTO(2));
//
//        dao = new UsuarioDAOIMP(); //llega a la implementacion de ese dato
//
//        //encapsula en una nueva condicion
//        if (dao.agregarRegistro(dto)) {
//            System.out.println("Operación Exitosa");
//        } else {
//            System.out.println("Operación Fallida");
//        }
//
//        //Para modificar datos de la BD ------------------------------------------------
//        dto = new UsuarioDTO(); //se instancia y transporta los datos
//        dto.setId(5);
//        dto.setUsuario("prueba4");
//        dto.setClave("123");
//        dto.setPerfil(new PerfilDTO(2));
//        dto.setPersona(new PersonaDTO(2));
//
//        dao = new UsuarioDAOIMP(); //llega a la implementacion de ese dato
//
//        //encapsula en una nueva condicion
//        if (dao.modificarRegistro(dto)) {
//            System.out.println("Operación Exitosa");
//        } else {
//            System.out.println("Operación Fallida");
//        }
//
//        //Para eliminar o inactivar datos de la BD ------------------------------------------------
//        dto = new UsuarioDTO(); //se instancia y transporta los datos
//        dto.setId(5);
//        dto.setUsuario("prueba4");
//        dto.setClave("123");
//        dto.setPerfil(new PerfilDTO(2));
//        dto.setPersona(new PersonaDTO(2));
//
//        dao = new UsuarioDAOIMP(); //llega a la implementacion de ese dato
//
//        //encapsula en una nueva condicion
//        if (dao.inactivarUsuario(8)) {
//            System.out.println("Operación Exitosa");
//        } else {
//            System.out.println("Operación Fallida");
//        }
//
//        // para recuperar el registro
//        dto = dao.recuperarRegistro(5);
//        System.out.println("Id recuperado " + dto.getId());
//        System.out.println("Usuario recuperado " + dto.getUsuario());
//        System.out.println("Perfil Usuario " + dto.getPerfil().getDescrip());
//        System.out.println("Persona  " + dto.getPersona().getNombres() + " " + dto.getPersona().getApellidos());
//
//        for (UsuarioDTO x : dao.recuperarRegistros()) {
//            System.out.println("Id recuperado " + x.getId());
//            System.out.println("Usuario recuperado " + x.getUsuario());
//            System.out.println("Perfil Usuario " + x.getPerfil().getDescrip());
//            System.out.println("Persona  " + x.getPersona().getNombres() + " " + x.getPersona().getApellidos());
//            System.out.println("---------------------------------------------------------------------------");
//        }

        dto = new PerfilDTO();
        dto.setId(4);
        dto.setDescrip("Perfil de Prueba");
        dto.setComentario("Comentario Prueba");
        permisos = new ArrayList<>();
        permisos.add(new MenuItemSistemaDTO(1));
        permisos.add(new MenuItemSistemaDTO(2));
        permisos.add(new MenuItemSistemaDTO(3));
       
        dto.setPermisos(permisos);

        dao = new PerfilDAOIMP();
        if (dao.agregarRegistro(dto)) {
            System.out.println("Operación exitosa");
        } else {
            System.out.println("Operación erronea");
        }
    }

    public static void main(String[] args) {
        new Test();
    }

}
