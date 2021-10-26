package Modelo.DaoImp;

import Base.Conexion;
import Modelo.Dao.NotificacionDAO;
import Modelo.Dto.NotificacionDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificacionDAOIMP implements NotificacionDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public NotificacionDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(NotificacionDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO notificaciones(descripcion, fecha, hora) VALUES (?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getDescripcion());
            ps.setDate(2, dto.getFecha());
            ps.setTime(3, dto.getHora());
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
    public boolean modificarRegistro(NotificacionDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.notificaciones SET id_notificacion=?, descripcion=?, fecha=?, hora=? WHERE id=? ;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getDescripcion());
            ps.setDate(2, dto.getFecha());
            ps.setTime(3, dto.getHora());
            ps.setInt(4, dto.getId());
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
    public boolean eliminarRegistro(NotificacionDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM public.notificaciones WHERE id=?;";
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
    public NotificacionDTO recuperarRegistro(Integer id) {
        try {
            NotificacionDTO dto = null;
            sql = "SELECT id_notificacion, descripcion, fecha, hora FROM public.notificaciones WHERE id = ?";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new NotificacionDTO();
                dto.setId(rs.getInt("id"));
                dto.setDescripcion(rs.getString("descripcion"));
                dto.setFecha(rs.getDate("fecha"));
                dto.setHora(rs.getTime("hora"));
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
    public List<NotificacionDTO> recuperarRegistros() {
        try {
            List<NotificacionDTO> lista = null;
            NotificacionDTO dto = null;
            sql = "SELECT id, descrip, comentario FROM public.perfil ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new NotificacionDTO();
                dto.setId(rs.getInt("id"));
                dto.setDescripcion(rs.getString("descripcion"));
                dto.setFecha(rs.getDate("fecha"));
                dto.setHora(rs.getTime("hora"));
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
