package Modelo.Dto;

public class PerfilDTO {
    private Integer id;
    private String  descrip;
    private String  comentario;

    public PerfilDTO() {
    }

    public PerfilDTO(Integer id) {
        this.id = id;
    }
    
    public PerfilDTO(Integer id, String descrip) {
        this.id = id;
        this.descrip = descrip;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
    
    
}
