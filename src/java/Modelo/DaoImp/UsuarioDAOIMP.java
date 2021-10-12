package Modelo.DaoImp;

import Base.Conexion;
import Modelo.Dao.UsuarioDAO;
import Modelo.Dto.PerfilDTO;
import Modelo.Dto.PersonaDTO;
import Modelo.Dto.UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAOIMP implements UsuarioDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public UsuarioDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(UsuarioDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.usuarios( usuario, clave, id_perfil, id_persona) VALUES ( ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getClave());
            ps.setInt(3, dto.getPerfil().getId());
            ps.setInt(4, dto.getPersona().getId());
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
    public boolean modificarRegistro(UsuarioDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.usuarios SET  usuario=?, clave=?,  id_perfil=?, id_persona=? WHERE id=? ;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getClave());
            ps.setInt(3, dto.getPerfil().getId());
            ps.setInt(4, dto.getPersona().getId());
            ps.setInt(5, dto.getId());
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
    public Boolean inactivarUsuario(Integer id) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.usuarios SET  estado = ? WHERE id=? ;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
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
    public UsuarioDTO recuperarRegistro(Integer id) {
        try {
            UsuarioDTO dto = null;
            sql = "SELECT A.id, A.usuario,  A.id_perfil, B.descrip AS perfil,  A.id_persona , C.nombres,  C.apellidos \n"
                + "FROM public.usuarios A INNER JOIN public.perfil B ON A.id_perfil = B.id\n"
                + "		          INNER JOIN public.personas C ON A.id_persona = C.id\n"
                + "WHERE A.id = ? AND estado = true ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new UsuarioDTO();
                dto.setId(rs.getInt("id"));
                dto.setUsuario(rs.getString("usuario"));
                dto.setPerfil( new PerfilDTO(rs.getInt("id_perfil"), rs.getString("perfil")));
                dto.setPersona(new PersonaDTO(rs.getInt("id_persona"), rs.getString("nombres"), rs.getString("apellidos")));
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
    public List<UsuarioDTO> recuperarRegistros() {
         try {
            UsuarioDTO dto;
            List<UsuarioDTO> lista ;
            sql = "SELECT A.id, A.usuario,  A.id_perfil, B.descrip AS perfil,  A.id_persona , C.nombres,  C.apellidos \n"
                + "FROM public.usuarios A INNER JOIN public.perfil B ON A.id_perfil = B.id\n"
                + "		          INNER JOIN public.personas C ON A.id_persona = C.id\n"
                + "WHERE  estado = true ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new UsuarioDTO();
                dto.setId(rs.getInt("id"));
                dto.setUsuario(rs.getString("usuario"));
                dto.setPerfil( new PerfilDTO(rs.getInt("id_perfil"), rs.getString("perfil")));
                dto.setPersona(new PersonaDTO(rs.getInt("id_persona"), rs.getString("nombres"), rs.getString("apellidos")));
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

    @Override
    public boolean eliminarRegistro(UsuarioDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
