package Modelo.DaoImp;

import Base.Conexion;
import Modelo.Dao.PermisoUsuarioDAO;
import Modelo.Dto.MenuSistemaDTO;
import Modelo.Dto.PerfilDTO;
import Modelo.Dto.PermisoUsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PermisoUsuarioDAOIMP implements PermisoUsuarioDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public PermisoUsuarioDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(PermisoUsuarioDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.permisos_usuario(id_perfil, menuSistema) VALUES (?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, dto.getPerfil().getId());
            ps.setInt(2, dto.getMenuSistema().getId());
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
    public boolean modificarRegistro(PermisoUsuarioDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.permisos_usuario SET id_perfil=?, id_item_sistema=? WHERE id=? ;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, dto.getPerfil().getId());
            ps.setInt(2, dto.getMenuSistema().getId());
            ps.setInt(3, dto.getId());
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
    public boolean eliminarRegistro(PermisoUsuarioDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM public.permisos_usuario WHERE id=?;";
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
    public PermisoUsuarioDTO recuperarRegistro(Integer id) {
        try {
            PermisoUsuarioDTO dto = null;
            sql = "SELECT id_perfil, id_item_sistema FROM public.permisos_usuario WHERE id = ?";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new PermisoUsuarioDTO();
                dto.setId(rs.getInt("id"));
                dto.setPerfil( new PerfilDTO(rs.getInt("id_perfil"), rs.getString("perfil")));
                dto.setMenuSistema( new MenuSistemaDTO(rs.getInt("id_item_sistema"), rs.getString("menuSistema")));
               
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
    public List<PermisoUsuarioDTO> recuperarRegistros() {
        try {
            List<PermisoUsuarioDTO> lista = null;
            PermisoUsuarioDTO dto = null;
            sql = "SELECT id_perfil, id_item_sistema FROM public.permisos_usuario";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new PermisoUsuarioDTO();
                dto.setId(rs.getInt("id"));
                dto.setPerfil( new PerfilDTO(rs.getInt("id_perfil"), rs.getString("perfil")));
                dto.setMenuSistema( new MenuSistemaDTO(rs.getInt("id_item_sistema"), rs.getString("menuSistema")));
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
