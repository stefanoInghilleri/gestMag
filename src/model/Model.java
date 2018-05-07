package model;

import dao.DAO;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;


public class Model
{
    private DAO dao;
    private List<Prodotto> prodotti;
    private List<Cliente> clienti;

    public Model()
    {
        dao = new DAO();
        prodotti = new LinkedList<>(dao.getAllProdotti());
        clienti = new LinkedList<>(dao.getAllClienti());
    }

    public List<Prodotto> getAllProdotti()
    {
        return prodotti;
    }

    public List<Cliente> getClienti()
    {
        return clienti;
    }

    public void caricaQt(int carica, Prodotto prodotto)
    {
        prodotto.setQt(carica);
        prodotto.setUpdated(true);
        
        
        
       // dao.updateCaricoProdotto(prodotto);
        
    }

    public void scaricaQt(int scarica, Prodotto prodotto, TextArea txtInfo)
    {
        if(prodotto.getQt() - scarica >= 0)
        {
            prodotto.setQt(-scarica);
            prodotto.setVenduto(scarica);
            prodotto.setUpdated(true);
            
            
            
       //     dao.updateScaricoProdotto(prodotto);
        } else
        {
            txtInfo.appendText("Attenzione la quantità selezionata \nè maggiore della quantità npresente in magazzino");
        }
    }

    public List<Prodotto> cercaProdotto(String cerca, List<Prodotto> prodotti)
    {
        List<Prodotto> result = new LinkedList<>();
        for(Prodotto p: prodotti)
        {
         
            if(p.getDescrizione().toLowerCase().equals(cerca) || p.getDescrizione().toLowerCase().contains(cerca) || p.getDescrizione().equals(cerca) || p.getDescrizione().contains(cerca))
                result.add(p);
        }

        return result;
    }

    public Prodotto inserisciProdotto(String descrizione, String qt, String prezzoU)
    {
        int idProdotto = prodotti.get(prodotti.size() - 1).getIdProdotto() + 1;
        int qtx = Integer.parseInt(qt);
        float pr = Float.parseFloat(prezzoU);
        Prodotto p = new Prodotto(idProdotto, descrizione, qtx, pr, 0);
        prodotti.add(p);
        return p;
    }

    public String caricaDb(List<Prodotto> itemToInsert)
    {
        int info = dao.inserisciProdotto(itemToInsert);
        return (new StringBuilder(String.valueOf(info))).toString();
    }

    public Cliente inserisciCliente(String nome, String telefono, String mail, String note)
    {
        int idCliente = ((Cliente)clienti.get(clienti.size() - 1)).getIdCliente() + 1;
        System.out.println(idCliente);
        Cliente cl = new Cliente(idCliente, nome, telefono, mail, note);
        dao.inserisciCliente(cl);
        return cl;
    }

    public void esportaTabella(TextArea txtInfo, ObservableList<Prodotto> prodotti) throws Exception
    {
        WritableWorkbook magazzino;
        String EXCEL_FILE_LOCATION = "C:\\Users\\Concept\\Desktop\\concept\\MAGAZZINO.xls";
        magazzino = null;
        magazzino = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
        WritableSheet excelSheet = magazzino.createSheet("Sheet 1", 0);
        Number number = null;
        Label title = new Label(1, 0, (new StringBuilder("MAGAZZINO ")).append(LocalDate.now().getMonthValue()).append("/").append(LocalDate.now().getYear()).toString());
        excelSheet.addCell(title);
        Label label = new Label(0, 1, "QUANTITA'");
        excelSheet.addCell(label);
        for(int i = 0; i < prodotti.size(); i++)
        {
            number = new Number(0, i + 2, ((Prodotto)prodotti.get(i)).getQt());
            excelSheet.addCell(number);
        }

        label = new Label(1, 1, "DESCRIZIONE");
        excelSheet.addCell(label);
        for(int i = 0; i < prodotti.size(); i++)
        {
            label = new Label(1, i + 2, ((Prodotto)prodotti.get(i)).getDescrizione());
            excelSheet.addCell(label);
        }

        label = new Label(2, 1, "PRZ UN");
        excelSheet.addCell(label);
        for(int i = 0; i < prodotti.size(); i++)
        {
            number = new Number(2, i + 2, ((Prodotto)prodotti.get(i)).getPrezzoUni());
            excelSheet.addCell(number);
        }

        label = new Label(3, 1, "VENDUTO");
        excelSheet.addCell(label);
        for(int i = 0; i < prodotti.size(); i++)
        {
            number = new Number(3, i + 2, ((Prodotto)prodotti.get(i)).getVenduto());
            excelSheet.addCell(number);
        }

        label = new Label(4, 1, "VAL MAGAZZINO");
        excelSheet.addCell(label);
        for(int i = 0; i < prodotti.size(); i++)
        {
            number = new Number(4, i + 2, ((Prodotto)prodotti.get(i)).getValoreMag());
            excelSheet.addCell(number);
        }

        label = new Label(5, 1, "VAL VENDUTO");
        excelSheet.addCell(label);
        for(int i = 0; i < prodotti.size(); i++)
        {
            number = new Number(5, i + 2, ((Prodotto)prodotti.get(i)).getValoreMag());
            excelSheet.addCell(number);
        }

        int k = prodotti.size();
        label = new Label(3, k + 3, "TOTALI");
        excelSheet.addCell(label);
        float result = 0.0F;
        float result2 = 0.0F;
        for(Prodotto p: prodotti)
        {
            try
            {
                result += p.getValoreMag();
                result2 += p.getValoreVenduto();
            }
            catch(Exception exception) { }
        }

        number = new Number(4, k + 3, result);
        excelSheet.addCell(number);
        number = new Number(5, k + 3, result2);
        excelSheet.addCell(number);
        magazzino.write();
 
        if(magazzino != null){
            try
            {
                magazzino.close();
                txtInfo.appendText("backup avvenuto con successo");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
      }

    public void updateCliente(Cliente c)
    {
        dao.updateCliente(c);
    }

    public void aggiornaProdotto(Prodotto p, int qt)
    {
    	p.setQt(qt);
    }

    public void eliminaProdotto(Prodotto p)
    {
        dao.eliminaProdotto(p);
        prodotti.remove(p);
    }

	public void saveUpdates(TextArea txtInfo) {
		
		List<Prodotto> items= new LinkedList<>();
		for(Prodotto p: prodotti){
			if(p.isUpdated())
				items.add(p);
		}
		dao.updateItems(items);
		txtInfo.appendText("dati aggiornati");
	}

	public void aggiornaProdottoVenduto(Prodotto p, int venduto) {
		
		p.setVenduto(venduto);
		
	}


}
