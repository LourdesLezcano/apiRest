package Modelo.DaoImp;

import Base.Conexion;
import Modelo.Dao.PerfilDAO;
import Modelo.Dto.MenuItemSistemaDTO;
import Modelo.Dto.PerfilDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerfilDAOIMP implements PerfilDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private int idPerfil;

    public PerfilDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(PerfilDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.perfil( descrip, comentario) VALUES ( ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getDescrip());
            ps.setString(2, dto.getComentario());
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    idPerfil = rs.getInt("id");
                    for (MenuItemSistemaDTO permiso : dto.getPermisos()) {
                        sql = "INSERT INTO public.permisos_usuario( id_perfil, id_item_sistema) VALUES ( ?, ?);";
                        ps = conexion.obtenerConexion().prepareStatement(sql);
                        ps.setInt(1, idPerfil);
                        ps.setInt(2, permiso.getId());
                        if (ps.executeUpdate() <= 0) {
                            conexion.Transaccion(Conexion.TR.CANCELAR);
                            return false;
                        }
                    }
                    conexion.Transaccion(Conexion.TR.CONFIRMAR);
                    return true;
                } else {
                    conexion.Transaccion(Conexion.TR.CANCELAR);
                    return false;
                }
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
    public boolean modificarRegistro(PerfilDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.perfil SET  descrip=?, comentario=?  WHERE id=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getDescrip());
            ps.setString(2, dto.getComentario());
            ps.setInt(3, dto.getId());
            if (ps.executeUpdate() > 0) {
                sql = "DELETE FROM public.permisos_usuario  WHERE id_perfil=?; ";
                ps = conexion.obtenerConexion().prepareStatement(sql);
                ps.setInt(1, dto.getId());
                if (ps.executeUpdate() > 0) {
                    for (MenuItemSistemaDTO permiso : dto.getPermisos()) {
                        sql = "INSERT INTO public.permisos_usuario( id_perfil, id_item_sistema) VALUES ( ?, ?);";
                        ps = conexion.obtenerConexion().prepareStatement(sql);
                        ps.setInt(1, dto.getId());
                        ps.setInt(2, permiso.getId());
                        if (ps.executeUpdate() <= 0) {
                            conexion.Transaccion(Conexion.TR.CANCELAR);
                            return false;
                        }
                    }
                    conexion.Transaccion(Conexion.TR.CONFIRMAR);
                    return true;
                } else {
                    conexion.Transaccion(Conexion.TR.CANCELAR);
                    return false;
                }
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

//    @Override
//    public boolean anularPerfil(Integer idPerfil) {
//        try {
//            conexion.Transaccion(Conexion.TR.INICIAR);
//            sql = "UPDATE public.perfil SET  estado=?  WHERE id=?;";
//            ps = conexion.obtenerConexion().prepareStatement(sql);
//            ps.setBoolean(1, false);
//            ps.setInt(2, idPerfil);
//            if (ps.executeUpdate() > 0) {
//                conexion.Transaccion(Conexion.TR.CONFIRMAR);
//                return true;
//            } else {
//                conexion.Transaccion(Conexion.TR.CANCELAR);
//                return false;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
//            conexion.Transaccion(Conexion.TR.CANCELAR);
//            return false;
//        } finally {
//            try {
//                conexion.cerrarConexion();
//                ps.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

    @Override
    public PerfilDTO recuperarRegistro(Integer id) {
        try {
            PerfilDTO dto = null;
            sql = "SELECT id, descrip, comentario FROM public.perfil WHERE id = ?";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new PerfilDTO();
                dto.setId(rs.getInt("id"));
                dto.setDescrip(rs.getString("descrip"));
                dto.setComentario(rs.getString("comentario"));
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
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existePerfilADMIN() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarRegistro(PerfilDTO perfil) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean anularPerfil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PerfilDTO> recuperarRegistros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
