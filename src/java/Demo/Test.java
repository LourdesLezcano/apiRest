package Demo;

import Modelo.Dao.UsuarioDAO;
import Modelo.DaoImp.UsuarioDAOIMP;
import Modelo.Dto.PerfilDTO;
import Modelo.Dto.PersonaDTO;
import Modelo.Dto.UsuarioDTO;

public class Test {

    private UsuarioDAO dao;
    private UsuarioDTO dto;

    public Test() {
        //Para insertar datos en la BD -----------------------------------------------
        dto = new UsuarioDTO(); //se instancia y transporta los datos
        dto.setUsuario("prueba3");
        dto.setClave("12");
        dto.setPerfil(new PerfilDTO(2));
        dto.setPersona(new PersonaDTO(2));

        dao = new UsuarioDAOIMP(); //llega a la implementacion de ese dato

        //encapsula en una nueva condicion
        if (dao.agregarRegistro(dto)) {
            System.out.println("Operación Exitosa");
        } else {
            System.out.println("Operación Fallida");
        }

        //Para modificar datos de la BD ------------------------------------------------
        dto = new UsuarioDTO(); //se instancia y transporta los datos
        dto.setId(5);
        dto.setUsuario("prueba4");
        dto.setClave("123");
        dto.setPerfil(new PerfilDTO(2));
        dto.setPersona(new PersonaDTO(2));

        dao = new UsuarioDAOIMP(); //llega a la implementacion de ese dato

        //encapsula en una nueva condicion
        if (dao.modificarRegistro(dto)) {
            System.out.println("Operación Exitosa");
        } else {
            System.out.println("Operación Fallida");
        }

        //Para eliminar o inactivar datos de la BD ------------------------------------------------
        dto = new UsuarioDTO(); //se instancia y transporta los datos
        dto.setId(5);
        dto.setUsuario("prueba4");
        dto.setClave("123");
        dto.setPerfil(new PerfilDTO(2));
        dto.setPersona(new PersonaDTO(2));

        dao = new UsuarioDAOIMP(); //llega a la implementacion de ese dato

        //encapsula en una nueva condicion
        if (dao.inactivarUsuario(8)) {
            System.out.println("Operación Exitosa");
        } else {
            System.out.println("Operación Fallida");
        }

        // para recuperar el registro
        dto = dao.recuperarRegistro(5);
        System.out.println("Id recuperado " + dto.getId());
        System.out.println("Usuario recuperado " + dto.getUsuario());
        System.out.println("Perfil Usuario " + dto.getPerfil().getDescrip());
        System.out.println("Persona  " + dto.getPersona().getNombres() + " " + dto.getPersona().getApellidos());

        for (UsuarioDTO x : dao.recuperarRegistros()) {
            System.out.println("Id recuperado " + x.getId());
            System.out.println("Usuario recuperado " + x.getUsuario());
            System.out.println("Perfil Usuario " + x.getPerfil().getDescrip());
            System.out.println("Persona  " + x.getPersona().getNombres() + " " + x.getPersona().getApellidos());
            System.out.println("---------------------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        new Test();
    }

}
