package Modelo.Dto;

public class PermisoUsuarioDTO {

    private Integer id;
    private PerfilDTO perfil;
    private MenuSistemaDTO menuSistema;

    public PermisoUsuarioDTO() {
    }

    public PermisoUsuarioDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PerfilDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDTO perfil) {
        this.perfil = perfil;
    }

    public MenuSistemaDTO getMenuSistema() {
        return menuSistema;
    }

    public void setMenuSistema(MenuSistemaDTO menuSistema) {
        this.menuSistema = menuSistema;
    }

}
