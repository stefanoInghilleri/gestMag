
package model;


public class Prodotto
{

    private int idProdotto;
    private String descrizione;
    private int qt;
    private float prezzoUni;
    private int venduto;
    private float valoreMag;
    private float valoreVenduto;
    private boolean updated;
    
    public Prodotto(int idProdotto, String descrizione, int qt, float prezzoUni, int venduto)
    {
        this.idProdotto = idProdotto;
        this.descrizione = descrizione;
        this.qt = qt;
        this.prezzoUni = prezzoUni;
        this.venduto = venduto;
        this.updated=false;
    }

    public int getIdProdotto()
    {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto)
    {
        this.idProdotto = idProdotto;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }

    public int getQt()
    {
        return qt;
    }

    public void setQt(int qt)
    {
        this.qt += qt;
    }

    public float getPrezzoUni()
    {
        return prezzoUni;
    }

    public void setPrezzoUni(float prezzoUni)
    {
        this.prezzoUni = prezzoUni;
    }

    public int getVenduto()
    {
        return venduto;
    }

    public void setVenduto(int venduto)
    {
        this.venduto += venduto;
    }

    public float getValoreMag()
    {
        valoreMag = prezzoUni * (float)qt;
        float result = arrotonda(valoreMag, 2);
        return result;
    }

    public void setValoreMag(float valoreMag)
    {
        this.valoreMag = valoreMag;
    }

    public float getValoreVenduto()
    {
        valoreVenduto = prezzoUni * (float)venduto;
        float result = arrotonda(valoreVenduto, 2);
        return result;
    }

    public void setValoreVenduto(float valoreVenduto)
    {
        this.valoreVenduto = valoreVenduto;
    }

    public float arrotonda(float numero, int nCifreDecimali)
    {
        return (float)((double)Math.round((double)numero * Math.pow(10D, nCifreDecimali)) / Math.pow(10D, nCifreDecimali));
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = prime * result + idProdotto;
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
        Prodotto other = (Prodotto)obj;
        return idProdotto == other.idProdotto;
    }

    public String toString()
    {
        return descrizione;
    }

    public void setQuantit‡Magazzino(Integer newValue)
    {
        qt = newValue.intValue();
    }

    public void setQuantit‡Venduto(Integer newValue)
    {
        venduto = newValue.intValue();
    }

	public void setUpdated(boolean updated) {
		this.updated=updated;
	}

	public boolean isUpdated() {
		return updated;
	}

}

