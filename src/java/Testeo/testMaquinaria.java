package Testeo;

import Base.Conexion;
import Modelo.Dao.MaquinariaDAO;
import Modelo.DaoImp.MaquinariaDAOIMP;
import Modelo.Dto.MaquinariaDTO;

public class testMaquinaria {

    private Conexion conexion;

    private MaquinariaDTO dto;
    private MaquinariaDAO dao;

    public testMaquinaria() {

//________________________________________________INSERTAR UN REGISTRO________________________________________________
//        dto = new MaquinariaDTO();
//        dto.setDescrip("MAQUINA PERFORADORA");
//        dto.setComentario("");
//        dao = new MaquinariaDAOIMP();
//
//        if (dao.agregarRegistro(dto)) {
//            System.out.println("El registro se ha insertado correctamente!");
//        } else {
//            System.out.println("Ha ocurrido un error en el proceso");
//        }
////_______________________________________________MODIFICAR UN REGISTRO____________-____________________________________  
//        dto = new MaquinariaDTO();
//        dto.setId(4);
//        dto.setDescrip("PRUEBA DE MODIFICACION");
//        dto.setComentario("mmmmmmmmmmmmmm");
//        dao = new MaquinariaDAOIMP();
//
//        if (dao.modificarRegistro(dto)) {
//            System.out.println("El registro se ha modificado correctamente!!");
//        } else {
//            System.out.println("Ha ocurrido un error en el proceso");
//        }

//________________________________________________ELIMINAR UN REGISTRO________________________________________________
//        dto = new MaquinariaDTO();
//        dto.setId(10);
//        dao = new MaquinariaDAOIMP();
//
//        if (dao.eliminarRegistro(dto)) {
//            System.out.println("El registro se ha eliminado correctamente!");
//        } else {
//            System.out.println("Ha ocurrido un error en el proceso");
//        }
//________________________________________________LISTAR UN REGISTRO________________________________________________  
//        dao = new MaquinariaDAOIMP();
//        dto = dao.recuperarRegistro(3);
//        System.out.println("Id: " + dto.getId());
//        System.out.println("Descripcion: " + dto.getDescrip());
//        System.out.println("Comentario: " + dto.getComentario());
//    
//____________________________________________LISTAR TODOS LOS REGISTROS____________________________________________
        dao = new MaquinariaDAOIMP();

        System.out.println("--------------------------LISTA DE TIPOS DE CONTRATO--------------------------");

        for (MaquinariaDTO dto : dao.recuperarRegistros()) {
            System.out.println("Id: " + dto.getId());
            System.out.println("Descripcion: " + dto.getDescrip());
            System.out.println("Comentario: " + dto.getComentario());
            System.out.println("---------------------------------------------------------------------------");
        }
//
    }

    public static void main(String[] args) {
        new testMaquinaria();

    }

}
