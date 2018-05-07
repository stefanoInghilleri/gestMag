package conceptStore;


import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;
import model.*;

public class MagazzinoController
{

	   private Model model;
	    @FXML
	    private TextArea txtCerca;
	    @FXML
	    private ChoiceBox<Integer> choiceQt;
	    @FXML
	    private DatePicker dateOp;
	    @FXML
	    private TableView<Prodotto> tableProd;
	    @FXML
	    private TableColumn<Prodotto,String> colDes;
	    @FXML
	    private TableColumn<Prodotto,Integer> colQt;
	    @FXML
	    private TableColumn<Prodotto, Float> colPun;
	    @FXML
	    private TableColumn<Prodotto,Integer> colVenduto;
	    @FXML
	    private TableColumn<Prodotto, Float> colValMag;
	    @FXML
	    private TableColumn<Prodotto, Float> colValVenduto;
	    @FXML
	    private HBox boxNewProd;
	    @FXML
	    private ListView<Prodotto> listProd;
	    @FXML
	    private Button btnInsert;
	    @FXML
	    private Button btnClear;
	    @FXML
	    private TextField txtDescrizione;
	    @FXML
	    private TextField txtQt;
	    @FXML
	    private TextField txtPrezzoUnitario;
	    @FXML
	    private DatePicker dateFrom;
	    @FXML
	    private DatePicker dateTo;
	    @FXML
	    private TextArea txtVenduto;
	    @FXML
	    private TextArea txtValMag;
	    @FXML
	    private TextArea txtInfo;
	    @FXML
	    private TableView<Cliente> tabClienti;
	    @FXML
	    private TableColumn<Cliente,String> colNome;
	    @FXML
	    private TableColumn<Cliente,String> colTelefono;
	    @FXML
	    private TableColumn<Cliente,String> colMail;
	    @FXML
	    private TableColumn<Cliente,String> colNote;
	    @FXML
	    private HBox boxNewCliente;
	    @FXML
	    private TextField txtNomeC;
	    @FXML
	    private TextField txtTelefono;
	    @FXML
	    private TextField txtMail;
	    @FXML
	    private TextField txtNote;
	    @FXML
	    private TextArea txtInfoCl;

    @FXML
    void doInsert(ActionEvent event)
    {
        List<Prodotto> items = listProd.getItems();
        tableProd.getItems().addAll(items);
        tableProd.refresh();
        model.caricaDb(items);
        txtInfo.appendText("Inserimento avvenuto con successo");
        boxNewProd.setVisible(false);
        btnInsert.setVisible(false);
        btnClear.setVisible(false);
        listProd.getItems().clear();
        listProd.setVisible(false);
    }

    @FXML
    void doCarica(ActionEvent event)
    {
        if(tableProd.getSelectionModel().getSelectedItem() != null && choiceQt.getValue() != null)
        {
            int carica = choiceQt.getValue().intValue();
            model.caricaQt(carica, tableProd.getSelectionModel().getSelectedItem());
            tableProd.refresh();
            choiceQt.setValue(null);
        } else
        {
            txtInfo.appendText("Attenzione seleziona \nun prodotto dalla lista!");
        }
    }
    
    @FXML
    void doScarica(ActionEvent event)
    {
        if(tableProd.getSelectionModel().getSelectedItem() != null && choiceQt.getValue() != null)
        {
            int carica = choiceQt.getValue().intValue();
            model.scaricaQt(carica, tableProd.getSelectionModel().getSelectedItem(), txtInfo);
            tableProd.refresh();
            choiceQt.setValue(null);
        } else
        {
            txtInfo.appendText("Attenzione seleziona \nun prodotto dalla lista!");
        }
    }
    
    @FXML
    void doSave(ActionEvent event) {
    		model.saveUpdates(this.txtInfo);
    }
    
    @FXML
    void doClear(ActionEvent event)
    {
        if(listProd.getSelectionModel().getSelectedItem() != null)
        {
            listProd.getItems().remove(listProd.getSelectionModel().getSelectedItem());
            listProd.refresh();
        } else
        {
            txtCerca.clear();
            tableProd.getItems().clear();
            tableProd.setItems(FXCollections.observableArrayList(model.getAllProdotti()));
            txtInfo.clear();
            boxNewProd.setVisible(false);
            btnInsert.setVisible(false);
            btnClear.setVisible(false);
            listProd.getItems().clear();
            listProd.setVisible(false);
        }
    }

    @FXML
    void doCerca(KeyEvent event)
    {
        List<Prodotto> prodotti = tableProd.getItems();
        List<Prodotto> result = model.cercaProdotto(txtCerca.getText(), prodotti);
        tableProd.getItems().clear();
        tableProd.getItems().addAll(result);
        txtInfo.clear();
    }

    @FXML
    void doDeleteItem(ActionEvent event)
    {
        if(tableProd.getSelectionModel().getSelectedItem() != null)
        {
            txtInfo.clear();
            Prodotto p = (Prodotto)tableProd.getSelectionModel().getSelectedItem();
            model.eliminaProdotto(p);
            tableProd.getItems().remove(p);
            tableProd.refresh();
            txtInfo.appendText("Prodotto eliminato con successo");
        } else
        {
            txtInfo.appendText("Attenzione seleziona\nil prodotto da eliminare");
        }
    }

    @FXML
    void doConferma(ActionEvent event)
    {
        Prodotto p = model.inserisciProdotto(txtDescrizione.getText(), txtQt.getText(), txtPrezzoUnitario.getText());
        listProd.getItems().add(p);
        listProd.refresh();
        txtDescrizione.clear();
        txtQt.clear();
        txtPrezzoUnitario.clear();
    }

    @FXML
    void doNewProd(ActionEvent event)
    {
        txtInfo.clear();
        boxNewProd.setVisible(true);
        listProd.setVisible(true);
        btnInsert.setVisible(true);
        btnClear.setVisible(true);
    }

    @FXML
    void doReset(ActionEvent event)
    {
        boxNewProd.setVisible(false);
        listProd.setVisible(false);
        btnInsert.setVisible(false);
        btnClear.setVisible(false);
        txtDescrizione.clear();
        txtPrezzoUnitario.clear();
        txtQt.clear();
        txtInfo.clear();
    }

    @FXML
    void doValues(ActionEvent event)
    {
        float result = 0.0F;
        float result2 = 0.0F;
        for(Prodotto p: tableProd.getItems())
        {
            result += p.getValoreMag();
            result2 += p.getValoreVenduto();
            result = (float)((double)Math.round((double)result * Math.pow(10D, 2D)) / Math.pow(10D, 2D));
            result2 = (float)((double)Math.round((double)result2 * Math.pow(10D, 2D)) / Math.pow(10D, 2D));
        }

        txtValMag.setText((new StringBuilder()).append(result).toString());
        txtVenduto.setText((new StringBuilder()).append(result2).toString());
    }

    @FXML
    void doEsportaTabella(ActionEvent event) throws Exception
    {
    	txtInfo.clear();
        model.esportaTabella(txtInfo, tableProd.getItems());
    }

    @FXML
    void doConfermaCL(ActionEvent event)
    {
        Cliente cl = model.inserisciCliente(txtNomeC.getText(), txtTelefono.getText(), txtMail.getText(), txtNote.getText());
        tabClienti.getItems().add(cl);
        tabClienti.refresh();
        txtInfoCl.appendText("Inserimento avvenuto con successo");
        doResetCL(event);
    }

    @FXML
    void doNewCustemer(ActionEvent event)
    {
        boxNewCliente.setVisible(true);
    }

    @FXML
    void doResetCL(ActionEvent event)
    {
        boxNewCliente.setVisible(false);
        txtNomeC.clear();
        txtTelefono.clear();
        txtMail.clear();
        txtNote.clear();
    }

    @FXML
    public void updateNote(CellEditEvent<Cliente,String> edittedCell)
    {
        Cliente c = tabClienti.getSelectionModel().getSelectedItem();
        c.setNote(edittedCell.getNewValue().toString());
        model.updateCliente(c);
        tabClienti.refresh();
    }

    @FXML
    public void updateMail(CellEditEvent<Cliente,String> edittedCell)
    {
        Cliente c = tabClienti.getSelectionModel().getSelectedItem();
        c.setMail(edittedCell.getNewValue().toString());
        model.updateCliente(c);
        tabClienti.refresh();
    }

    @FXML
    public void updateTelefono(CellEditEvent<Cliente,String> edittedCell)
    {
        Cliente c = tabClienti.getSelectionModel().getSelectedItem();
        c.setTelefono(edittedCell.getNewValue().toString());
        model.updateCliente(c);
        tabClienti.refresh();
    }

    @FXML
    void updateQt(CellEditEvent<Prodotto,Integer> edittedCell)
    {
        Prodotto p = tableProd.getSelectionModel().getSelectedItem();
        p.setQuantit‡Magazzino(edittedCell.getNewValue());
        model.aggiornaProdotto(p, edittedCell.getNewValue());
        tableProd.refresh();
    }

    @FXML
    void updateVenduto(CellEditEvent<Prodotto,Integer> edittedCell)
    {
        Prodotto p = tableProd.getSelectionModel().getSelectedItem();
        p.setQuantit‡Venduto(edittedCell.getNewValue());
        model.aggiornaProdottoVenduto(p, edittedCell.getNewValue());
        tableProd.refresh();
    }

    @FXML
    void initialize()
    {
        colDes.setCellValueFactory(new PropertyValueFactory<Prodotto,String>("descrizione"));
        colQt.setCellValueFactory(new PropertyValueFactory<Prodotto,Integer>("qt"));
        colPun.setCellValueFactory(new PropertyValueFactory<Prodotto,Float>("prezzoUni"));
        colVenduto.setCellValueFactory(new PropertyValueFactory<Prodotto,Integer>("venduto"));
        colValMag.setCellValueFactory(new PropertyValueFactory<Prodotto,Float>("valoreMag"));
        colValVenduto.setCellValueFactory(new PropertyValueFactory<Prodotto,Float>("valoreVenduto"));
        colNome.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nome"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
        colMail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("mail"));
        colNote.setCellValueFactory(new PropertyValueFactory<Cliente,String>("note"));
        tabClienti.setEditable(true);
        colNote.setCellFactory(TextFieldTableCell.forTableColumn());
        colMail.setCellFactory(TextFieldTableCell.forTableColumn());
        colTelefono.setCellFactory(TextFieldTableCell.forTableColumn());
        tableProd.setEditable(true);
        colQt.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colVenduto.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    public void setModel(Model model)
    {
        this.model = model;
        tableProd.setItems(FXCollections.observableArrayList(model.getAllProdotti()));
        tabClienti.setItems(FXCollections.observableArrayList(model.getClienti()));
        for(int i = 1; i <= 10; i++)
            choiceQt.getItems().add(i);

    }

 }
