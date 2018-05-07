package model;


public class Cliente
{

    private int idCliente;
    private String nome;
    private String telefono;
    private String mail;
    private String note;

    public Cliente(int idCliente, String nome, String telefono, String mail, String note)
    {
        this.idCliente = idCliente;
        this.nome = nome;
        this.telefono = telefono;
        this.mail = mail;
        this.note = note;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = prime * result + idCliente;
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente)obj;
        return idCliente == other.idCliente;
    }

    public String toString()
    {
        return (new StringBuilder("Cliente [nome=")).append(nome).append(", telefono=").append(telefono).append(", mail=").append(mail).append(", note=").append(note).append("]").toString();
    }

}
