package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Prodotto;

// Referenced classes of package dao:
//            DBConnect

public class DAO
{

    public DAO()
    {
    }

    public List<Prodotto> getAllProdotti()
    {
        String sql = "select  * from magazzino";
        try
        {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Prodotto> list = new ArrayList<>();
            
            while(rs.next()){
            list.add(new Prodotto(rs.getInt("idProdotto"), rs.getString("descrizione"), 
            		rs.getInt("qt"), rs.getFloat("prezzo_unitario"), rs.getInt("venduto")));
            }
            conn.close();
            return list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Cliente> getAllClienti()
    {
        String sql = "select  * from clienti";
        try
        {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Cliente> list = new ArrayList<>();
            
            while(rs.next()){
            list.add(new Cliente(rs.getInt("idCliente"), rs.getString("nome"), 
            		rs.getString("telefono"), rs.getString("mail"), rs.getString("note")));
            }
            conn.close();
            return list;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public int inserisciProdotto(List<Prodotto> items)
    {
        String sql = "INSERT INTO magazzino VALUES ";
        for(int i = 0; i < items.size(); i++){
            if(i == items.size() - 1)
                sql += "(?,?,?,?,?);";
            else
                sql +="(?,?,?,?,?),";
        }
        try
        {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            for(int i = 0; i < items.size(); i++)
            {
                st.setInt(1 + i * 5, (items.get(i)).getIdProdotto());
                st.setInt(2 + i * 5, (items.get(i)).getQt());
                st.setString(3 + i * 5, (items.get(i)).getDescrizione());
                st.setFloat(4 + i * 5, (items.get(i)).getPrezzoUni());
                st.setInt(5 + i * 5, (items.get(i)).getVenduto());
            }

            st.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

  /*  public void updateCaricoProdotto(Prodotto prodotto)
    {
        String sql = "UPDATE magazzino SET qt=? WHERE idProdotto=?";
        try
        {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, prodotto.getQt());
            st.setInt(2, prodotto.getIdProdotto());
            st.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateScaricoProdotto(Prodotto prodotto)
    {
        String sql = "UPDATE magazzino SET qt=? , venduto=? WHERE idProdotto=? and idprodotto=?";
        try
        {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, prodotto.getQt());
            st.setInt(2, prodotto.getVenduto());
            st.setInt(3, prodotto.getIdProdotto());
            st.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }*/

    public void inserisciCliente(Cliente cl)
    {
        String sql = "INSERT INTO clienti VALUES(?,?,?,?,?)";
        try
        {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, cl.getIdCliente());
            st.setString(2, cl.getNome());
            st.setString(3, cl.getTelefono());
            st.setString(4, cl.getMail());
            st.setString(5, cl.getNote());
            st.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateCliente(Cliente c)
    {
        String sql = "UPDATE clienti SET telefono=? , mail=? , note=? WHERE idCliente=?";
        try
        {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, c.getTelefono());
            st.setString(2, c.getMail());
            st.setString(3, c.getNote());
            st.setInt(4, c.getIdCliente());
            st.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void eliminaProdotto(Prodotto p)
    {
        String sql = "DELETE FROM magazzino WHERE idProdotto=?";
        try
        {
            Connection conn = DBConnect.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, p.getIdProdotto());
            st.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

	public void updateItems(List<Prodotto> items) {
		
		Connection conn = DBConnect.getConnection();
		for(Prodotto p: items){
			 String sql = "UPDATE magazzino SET qt=? , venduto=? WHERE idProdotto=?";
		        try
		        {
		            PreparedStatement st = conn.prepareStatement(sql);
		            st.setInt(1, p.getQt());
		            st.setInt(2, p.getVenduto());
		            st.setInt(3, p.getIdProdotto());
		            st.executeUpdate();
		        }
		        catch(SQLException e)
		        {
		            e.printStackTrace();
		        }
		}
		
	}
}

