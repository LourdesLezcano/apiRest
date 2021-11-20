package Modelo.DaoImp;

import Base.Conexion;
import Modelo.Dao.PersonaDAO;
import Modelo.Dto.PersonaDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAOIMP implements PersonaDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public PersonaDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(PersonaDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.personas(nro_documento, nombres, apellidos, fecha_nac, direccion, correo, nro_telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getNroDocumento());
            ps.setString(2, dto.getNombres());
            ps.setString(3, dto.getApellidos());
            ps.setDate(4, dto.getFechaNac());
            ps.setString(5, dto.getDireccion());
            ps.setString(6, dto.getCorreo());
            ps.setInt(7, dto.getNroTelefono());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean modificarRegistro(PersonaDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE personas SET id=?, nro_documento=?, nombres=?, apellidos=?, fecha_nac=?, direccion=?, correo=?, nro_telefono=? WHERE id=? ;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getNroDocumento());
            ps.setString(2, dto.getNombres());
            ps.setString(3, dto.getApellidos());
            ps.setDate(4, dto.getFechaNac());
            ps.setString(5, dto.getDireccion());
            ps.setString(6, dto.getCorreo());
            ps.setInt(7, dto.getNroTelefono());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean eliminarRegistro(PersonaDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM public.personas WHERE id=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, dto.getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public PersonaDTO recuperarRegistro(Integer id) {
        try {
            PersonaDTO dto = null;
            sql = "SELECT id, nro_documento, nombres, apellidos, fecha_nac, direccion, correo, nro_telefono FROM public.personas WHERE id = ?";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new PersonaDTO();
                dto.setId(rs.getInt("id"));
                dto.setNroDocumento(rs.getString("nro_documento"));
                dto.setNombres(rs.getString("nombres"));
                dto.setApellidos(rs.getString("apellidos"));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<PersonaDTO> recuperarRegistros() {
        try {
            List<PersonaDTO> lista = null;
            PersonaDTO dto = null;
            sql = "SELECT id, nro_documento, nombres, apellidos, fecha_nac, direccion, correo, nro_telefono FROM public.personas WHERE id = ? FROM public.personas ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new PersonaDTO();
                dto.setId(rs.getInt("id"));
                dto.setNroDocumento(rs.getString("nro_documento"));
                dto.setNombres(rs.getString("nombres"));
                dto.setApellidos(rs.getString("apellidos"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
