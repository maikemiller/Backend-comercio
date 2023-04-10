package br.unibh.sdm.backend_comercio.entidades;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "usuario")
public class Usuario {

    private String id;
    private String codigo;
    private String nome;
    private String email;
    private String senha;
    
    


    public Usuario() {
        super();
    }


    public Usuario(String id,String codigo, String nome, String email, String senha){
        super();
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        
    }

    

    

    
    @DynamoDBHashKey
    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @DynamoDBAttribute
    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    @DynamoDBAttribute
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute
    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Usuario [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", email=" + email + ", senha=" + senha
                + "]";
    }


   

    




    
    
    

    


    
    



    



   



    


    

    
}
