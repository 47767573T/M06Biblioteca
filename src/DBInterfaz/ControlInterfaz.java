package DBInterfaz;

import DBControl.Acceso;
import DBControl.Consultar;
import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by Moises on 19/01/2016.
 */
public class ControlInterfaz implements Initializable{

    @FXML
    Button btnSalir= new Button();

    @FXML
    Label lblResultado = new Label();

    @FXML
    Button btnIngresar = new Button();
    @FXML
    Button btnEliminar= new Button();
    @FXML
    Button btnConsultar= new Button();
    @FXML
    Button btnModificar= new Button();

    @FXML
    Button btnLibros= new Button();
    @FXML
    Button btnSocios= new Button();
    @FXML
    Button btnPrestamos= new Button();

    @FXML
    ListView lvwResultado = new ListView();


    @FXML   //BOTONES RELACIONADOS CON TABLA LIBRO
    AnchorPane anpLibros = new AnchorPane();
        @FXML
        TextField txfIdLibros = new TextField();
        @FXML
        TextField txfAnoEdicion = new TextField();
        @FXML
        TextField txfEditorial = new TextField();
        @FXML
        TextField txfTitulo = new TextField();
        @FXML
        TextField txfPaginas = new TextField();
        @FXML
        TextField txfUnidades = new TextField();

    @FXML   //BOTONES RELACIONADOS CON TABLA SOCIOS
    AnchorPane anpSocios = new AnchorPane();
        @FXML
        TextField txfIdSocios = new TextField();
        @FXML
        TextField txfNombre = new TextField();
        @FXML
        TextField txfApellido = new TextField();
        @FXML
        TextField txfEdad = new TextField();
        @FXML
        TextField txfDireccion = new TextField();
        @FXML
        TextField txfTelefono = new TextField();

    @FXML //BOTONES RELACIONADOS CON TABLA SOCIOS
    AnchorPane anpPrestamos = new AnchorPane();
        @FXML
        TextField txfIdPrestamos = new TextField();
        @FXML
        TextField txfIdLibroPrestamo = new TextField();
        @FXML
        TextField txfIdSocioPrestamo = new TextField();
        @FXML
        TextField txfFechaInicial= new TextField();
        @FXML
        TextField txfFechaFinal = new TextField();
        @FXML
        CheckBox chbNoDevuelto = new CheckBox();

    public static Acceso acceso;

    public static String ano_mes_dia = "yyyy/MM/dd";
    static SimpleDateFormat formatoBasicoFecha = new SimpleDateFormat(ano_mes_dia);

    int menuAccion = 0;
    int menuTabla = 0;

    ListProperty<String> listProperty = new SimpleListProperty<>();
    List <String> lvwRenglones = new ArrayList<>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //1.- Conectamos con la base de datos
        Configuration conf = new Configuration().configure();
        acceso = new Acceso(conf);
        Session session = acceso.getSession("MainApp");

        acceso.ComprobarTablas(session); //Mostramos por consola las entidades

        //2.- Para testear creamos tablas y registros ficticios
        InterfazTest.autoInsertSocios();
        InterfazTest.autoInsertLibros();
        InterfazTest.autoInsertPrestamos();

        //3.- Ocultamos partes del menu al iniciar
        txfIdLibros.setVisible(false);
        txfIdSocios.setVisible(false);
        txfIdPrestamos.setVisible(false);

        anpLibros.setVisible(false);
        anpPrestamos.setVisible(false);
        anpSocios.setVisible(false);

        btnLibros.setVisible(false);
        btnSocios.setVisible(false);
        btnPrestamos.setVisible(false);
    }
    //METODOS DE EVENTO DEL MENU PRINCIPAL::::::::::::::::::::::::::::::::::::::::::::::::::
    public void onIngresar(ActionEvent actionEvent)throws Exception{
        menuAccion = 10;
        mostrarMenuTablas();
    }

    public void onEliminar(ActionEvent actionEvent)throws Exception{
        menuAccion = 20;
        mostrarMenuTablas();
    }

    public void onConsultar(ActionEvent actionEvent)throws Exception{
        menuAccion = 30;
        mostrarMenuTablas();
    }

    public void onModificar(ActionEvent actionEvent)throws Exception{
        menuAccion = 40;
        mostrarMenuTablas();
    }


    //METODOS DE EVENTO DE MENU SECUNDARIO:::::::::::::::::::::::::::::::::::::::::::::
    public void onActionLibro(ActionEvent actionEvent) throws Exception{
        menuTabla = 1;
        mostrarMenuCampos();
        mostrarCampos();
    }

    public void onActionSocio(ActionEvent actionEvent) throws Exception{
        menuTabla = 2;
        mostrarMenuCampos();
        mostrarCampos();
    }

    public void onActionPrestamo(ActionEvent actionEvent) throws Exception{
        menuTabla = 3;
        mostrarMenuCampos();
        mostrarCampos();
    }

    public void onBusqueda(ActionEvent actionEvent) throws Exception{
        switch (menuTabla+menuAccion){
            case 11:
                ingresarLibro();
                break;
            case 12:
                ingresarSocio();
                break;
            case 13:
                ingresarPrestamo();
                break;
            case 21:
                eliminarLibro();
                break;
            case 22:
                eliminarSocio();
                break;
            case 23:
                eliminarPrestamo();
                break;
            case 31:
                consultarLibros();
                break;
            case 32:
                consultarSocios();
                break;
            case 33:
                consultarPrestamos();
        }
    }

    public void consultarLibros(){
        List<Libros> listLibros = DBControl.Consultar.queryLibros(
                validarCampoVacio(txfTitulo.getText())
                ,validarCampoVacio(txfEditorial.getText())
                ,validarCampoVacio(txfAnoEdicion.getText())
                ,validarCampoVacio(txfPaginas.getText())
                ,validarCampoVacio(txfUnidades.getText()));

        lblResultado.setText("Resultado: "+String.valueOf(listLibros.size()));
        montarListadoLibro(listLibros);
    }

    public void consultarSocios(){
        List<Socios> listSocios = DBControl.Consultar.querySocios(
                validarCampoVacio(txfNombre.getText())
                ,validarCampoVacio(txfApellido.getText())
                ,validarCampoVacio(txfDireccion.getText())
                ,validarCampoVacio(txfTelefono.getText()));

        lblResultado.setText("Resultado: "+String.valueOf(listSocios.size()));
        montarListadoSocio(listSocios);
    }


    public void consultarPrestamos(){
        int idLibro;
        int idSocio;

        if (txfIdLibroPrestamo.getText().equals("")){
            idLibro = 0;
        } else idLibro = Integer.parseInt(txfIdLibroPrestamo.getText());

        if (txfIdLibroPrestamo.getText().equals("")){
            idSocio = 0;
        } else idSocio = Integer.parseInt(txfIdLibroPrestamo.getText());

        List<Prestamo> listPrestamos = DBControl.Consultar.queryPrestamos(
                validarCampoVacioInt(idLibro)
                ,validarCampoVacioInt(idSocio)
                ,validarCampoVacio(txfDireccion.getText())
                ,validarCampoVacio(txfTelefono.getText()));

        System.out.println(listPrestamos.get(0).getId());


        lblResultado.setText("Resultado: "+String.valueOf(listPrestamos.size()));
        montarListadoPrestamo(listPrestamos);
    }

    public void eliminarLibro(){
        int resultado = 0;
        int id = Integer.parseInt(txfIdLibros.getText());

        if (DBControl.Eliminar.libroPorId(id)) resultado = 1;

        vaciarListado();
        lblResultado.setText("Resultado: "+resultado);
    }

    public void eliminarSocio(){
        int resultado = 0;
        int id = Integer.parseInt(txfIdSocios.getText());

        if (DBControl.Eliminar.socioPorId(id)) resultado = 1;

        vaciarListado();
        lblResultado.setText("Resultado: "+resultado);
    }

    public void eliminarPrestamo(){
        int resultado = 0;
        int id = Integer.parseInt(txfIdPrestamos.getText());

        if (DBControl.Eliminar.prestamoPorId(id)) resultado = 1;

        vaciarListado();
        lblResultado.setText("Resultado: "+resultado);
    }

    public void ingresarLibro (){

        Libros libro = new Libros(
                txfTitulo.getText()
                ,txfUnidades.getText()
                ,txfEditorial.getText()
                ,txfPaginas.getText()
                ,txfAnoEdicion.getText());
        DBControl.Insertar.nuevoLibro(libro);
        lblResultado.setText("Resultado: 1");
    }

    public void ingresarSocio (){

        Socios socio = new Socios(
                txfNombre.getText()
                ,txfApellido.getText()
                ,txfEdad.getText()
                ,txfDireccion.getText()
                ,txfTelefono.getText());
        DBControl.Insertar.nuevoSocio(socio);
        lblResultado.setText("Resultado: 1");
    }

    public void ingresarPrestamo (){
        DBControl.Insertar.crearPrestamo(
                Integer.parseInt(txfIdLibroPrestamo.getText())
                ,Integer.parseInt(txfIdSocioPrestamo.getText()));
        lblResultado.setText("Resultado: 1");
    }

    public void onSalir(){
        //acceso.Apagar(); //Cerramos el sessionFactory al cerrar la aplicacion
        Platform.exit();
    }

    //METODOS UTILES::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void mostrarMenuTablas(){
        btnLibros.setVisible(true);
        btnSocios.setVisible(true);
        btnPrestamos.setVisible(true);
    }

    public void mostrarMenuCampos(){

        switch (menuTabla) {
            case 1:
                anpLibros.setVisible(true);
                anpPrestamos.setVisible(false);
                anpSocios.setVisible(false);
                break;
            case 2:
                anpLibros.setVisible(false);
                anpSocios.setVisible(true);
                anpPrestamos.setVisible(false);
                break;
            case 3:
                anpLibros.setVisible(false);
                anpSocios.setVisible(false);
                anpPrestamos.setVisible(true);
                break;
        }
    }

    public void mostrarCampos(){

        switch (menuTabla+menuAccion) {
            case 11:
                txfIdLibros.setVisible(false);
                txfAnoEdicion.setVisible(true);
                txfEditorial.setVisible(true);
                txfTitulo.setVisible(true);
                txfPaginas.setVisible(true);
                txfUnidades.setVisible(true);
                break;

            case 12:
                txfIdSocios.setVisible(false);
                txfNombre.setVisible(true);
                txfApellido.setVisible(true);
                txfEdad.setVisible(true);
                txfDireccion.setVisible(true);
                txfTelefono.setVisible(true);
                break;

            case 13:
                txfIdPrestamos.setVisible(false);
                txfIdLibroPrestamo.setVisible(true);
                txfIdSocioPrestamo.setVisible(true);
                chbNoDevuelto.setVisible(false);
                txfFechaFinal.setVisible(false);
                txfFechaInicial.setVisible(false);
                break;

            case 21:
                txfIdLibros.setVisible(true);
                txfAnoEdicion.setVisible(false);
                txfEditorial.setVisible(false);
                txfTitulo.setVisible(false);
                txfPaginas.setVisible(false);
                txfUnidades.setVisible(false);
                break;

            case 22:
                txfIdSocios.setVisible(true);
                txfNombre.setVisible(false);
                txfApellido.setVisible(false);
                txfEdad.setVisible(false);
                txfDireccion.setVisible(false);
                txfTelefono.setVisible(false);
                break;

            case 23:
                txfIdPrestamos.setVisible(true);
                txfIdLibroPrestamo.setVisible(false);
                txfIdSocioPrestamo.setVisible(false);
                chbNoDevuelto.setVisible(false);
                txfFechaFinal.setVisible(false);
                txfFechaInicial.setVisible(false);
                break;

            case 31:
                txfIdLibros.setVisible(false);
                txfAnoEdicion.setVisible(true);
                txfEditorial.setVisible(true);
                txfTitulo.setVisible(true);
                txfPaginas.setVisible(true);
                txfUnidades.setVisible(true);
                break;

            case 32:
                txfIdSocios.setVisible(false);
                txfNombre.setVisible(true);
                txfApellido.setVisible(true);
                txfEdad.setVisible(true);
                txfDireccion.setVisible(true);
                txfTelefono.setVisible(true);
                break;

            case 33:
                txfIdPrestamos.setVisible(false);
                txfIdLibroPrestamo.setVisible(true);
                txfIdSocioPrestamo.setVisible(true);
                chbNoDevuelto.setVisible(true);
                txfFechaFinal.setVisible(true);
                txfFechaInicial.setVisible(true);
                break;
        }
    }

    public String validarCampoVacio (String resultado){
        if (resultado.equals("")) resultado = "0";

        return resultado;
    }

    public int validarCampoVacioInt (int resultado){
        if (resultado == 0) resultado = 0;

        return resultado;
    }

    public void montarListadoLibro (List<Libros> listado){
        lvwRenglones.clear();

        for (Libros aListado : listado) {
            lvwRenglones.add(aListado.toString());
        }

        listProperty.set(FXCollections.observableArrayList(lvwRenglones));
        lvwResultado.itemsProperty().bind(listProperty);
    }

    public void montarListadoSocio (List<Socios> listado){
        lvwRenglones.clear();

        for (Socios aListado : listado) {
            lvwRenglones.add(aListado.toString());
        }

        listProperty.set(FXCollections.observableArrayList(lvwRenglones));
        lvwResultado.itemsProperty().bind(listProperty);
    }

    public void montarListadoPrestamo (List<Prestamo> listado){
        lvwRenglones.clear();

        for (Prestamo aListado : listado) {
            lvwRenglones.add(aListado.toString());
        }

        listProperty.set(FXCollections.observableArrayList(lvwRenglones));
        lvwResultado.itemsProperty().bind(listProperty);
    }

    public void vaciarListado (){
        lvwRenglones.clear();

        listProperty.set(FXCollections.observableArrayList(lvwRenglones));
        lvwResultado.itemsProperty().bind(listProperty);

    }
}
