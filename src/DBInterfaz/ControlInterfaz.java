package DBInterfaz;

import DBControl.Acceso;
import DBObjects.Libros;
import DBObjects.Prestamo;
import DBObjects.Socios;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    Button btnLibros= new Button();
    @FXML
    Button btnSocios= new Button();
    @FXML
    Button btnPrestamos= new Button();

    @FXML
    ListView<String> lvwResultado = new ListView<>();


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

    static int menuAccion = 0;
    static int menuTabla = 0;

    ListProperty<String> listProperty = new SimpleListProperty<>();
    List <String> lvwRenglones = new ArrayList<>();


    /**
     * Metodo Al iniciar la aplicacion
     * @param location
     * @param resources
     */
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

    /**
     * Metodo al ingresar que guarda el boton seleccionado y muestra los menus relacionados
     * @throws Exception
     */
    public void onIngresar()throws Exception{
        menuAccion=10;
        mostrarMenuTablas();
    }

    /**
     * Metodo al Eliminar que guarda el boton seleccionado y muestra los menus relacionados
     * @throws Exception
     */
    public void onEliminar()throws Exception{
        menuAccion=20;
        mostrarMenuTablas();
    }

    /**
     * Metodo al consultar que guarda el boton seleccionado y muestra los menus relacionados
     * @throws Exception
     */
    public void onConsultar()throws Exception{
        menuAccion=30;
        mostrarMenuTablas();
    }


    //METODOS DE EVENTO DE MENU SECUNDARIO:::::::::::::::::::::::::::::::::::::::::::::

    /**
     * Metodo al seleccionar menu libro que guarda el boton seleccionado y muestra los menus relacionados
     * @throws Exception
     */
    public void onActionLibro() throws Exception{
        menuTabla=1;
        mostrarMenuCampos();
        mostrarCampos();
    }

    /**
     * Metodo al seleccionar menu Socio que guarda el boton seleccionado y muestra los menus relacionados
     * @throws Exception
     */
    public void onActionSocio() throws Exception{
        menuTabla=2;
        mostrarMenuCampos();
        mostrarCampos();
    }

    /**
     * Metodo al seleccionar menu Prestamo que guarda el boton seleccionado y muestra los menus relacionados
     * @throws Exception
     */
    public void onActionPrestamo() throws Exception{
        menuTabla=3;
        mostrarMenuCampos();
        mostrarCampos();
    }

    /**
     * Metodo que distribuye el metodo a utilizar segun las opciones escogidas
     * @throws Exception
     */
    public void onBusqueda() throws Exception{
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

    /**
     * Selecciona libro(s) segun especificaciones de los campos y lo muestra por la aplicacion
     */
    public void consultarLibros(){
        List<Libros> listLibros = DBControl.Consultar.queryLibros(
                validarCampoVacioStr(txfTitulo.getText())
                ,validarCampoVacioStr(txfEditorial.getText())
                ,validarCampoVacioStr(txfAnoEdicion.getText())
                ,validarCampoVacioStr(txfPaginas.getText())
                ,validarCampoVacioStr(txfUnidades.getText()));

        lblResultado.setText("Resultado: "+String.valueOf(listLibros.size()));
        montarListadoLibro(listLibros);
    }

    /**
     * Selecciona Socio(s) segun especificaciones de los campos y lo muestra por la aplicacion
     */
    public void consultarSocios(){
        List<Socios> listSocios = DBControl.Consultar.querySocios(
                validarCampoVacioStr(txfNombre.getText())
                ,validarCampoVacioStr(txfApellido.getText())
                ,validarCampoVacioStr(txfDireccion.getText())
                ,validarCampoVacioStr(txfTelefono.getText())
                ,validarCampoVacioStr(txfEdad.getText()));

        lblResultado.setText("Resultado: "+String.valueOf(listSocios.size()));
        montarListadoSocio(listSocios);
    }

    /**
     * Selecciona prestamo(s) segun especificaciones de los campos y lo muestra por la aplicacion
     * @throws ParseException
     */
    public void consultarPrestamos() throws ParseException {
        int idLibro;
        int idSocio;

        if (txfIdLibroPrestamo.getText().equals("")){
            idLibro = 0;
        } else idLibro = Integer.parseInt(txfIdLibroPrestamo.getText());

        if (txfIdSocioPrestamo.getText().equals("")){
            idSocio = 0;
        } else idSocio = Integer.parseInt(txfIdSocioPrestamo.getText());

        List<Prestamo> listPrestamos = DBControl.Consultar.queryPrestamos(
                validarCampoVacioInt(idLibro)
                ,validarCampoVacioInt(idSocio)
                ,validarCampoVacioStr(txfDireccion.getText())
                ,validarCampoVacioStr(txfTelefono.getText()));

        System.out.println(listPrestamos.get(0).getId());

        if (chbNoDevuelto.isSelected()) listPrestamos = filtrarPrestamoCaducado(listPrestamos);


        lblResultado.setText("Resultado: "+String.valueOf(listPrestamos.size()));
        montarListadoPrestamo(listPrestamos);
    }

    /**
     * Elimina el registro libro de la base de datos según especificaciones de usuario y muestra el numero de registros afectados
     */
    public void eliminarLibro(){
        int resultado = 0;
        int id = Integer.parseInt(txfIdLibros.getText());

        if (DBControl.Eliminar.libroPorId(id)) resultado = 1;

        vaciarListado();
        lblResultado.setText("Resultado: "+resultado);
    }

    /**
     * Elimina el registro socio de la base de datos según especificaciones de usuario y muestra el numero de registros afectados
     */
    public void eliminarSocio(){
        int resultado = 0;
        int id = Integer.parseInt(txfIdSocios.getText());

        if (DBControl.Eliminar.socioPorId(id)) resultado = 1;

        vaciarListado();
        lblResultado.setText("Resultado: "+resultado);
    }

    /**
     * Elimina el registro prestamo de la base de datos según especificaciones de usuario y muestra el numero de registros afectados
     */
    public void eliminarPrestamo(){
        int resultado = 0;
        int id = Integer.parseInt(txfIdPrestamos.getText());

        if (DBControl.Eliminar.prestamoPorId(id)) resultado = 1;

        vaciarListado();
        lblResultado.setText("Resultado: "+resultado);
    }

    /**
     * Ingresa un libro en la base de datos según especificaciones de usuario y muestra el numero de registros afectados
     */
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

    /**
     * Ingresa un Socio en la base de datos según especificaciones de usuario y muestra el numero de registros afectados
     */
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

    /**
     * Ingresa un prestamo en la base de datos según especificaciones de usuario y muestra el numero de registros afectados
     */
    public void ingresarPrestamo (){
        DBControl.Insertar.crearPrestamo(
                Integer.parseInt(txfIdLibroPrestamo.getText())
                ,Integer.parseInt(txfIdSocioPrestamo.getText()));
        lblResultado.setText("Resultado: 1");
    }

    /**
     * Cierra el programa
     */
    public void onSalir(){
        Platform.exit();
    }

    /**
     * Prepara los campos del tipo String si estan vacios dandole valores por defecto para querys
     * @param resultado El valor del campo a validar
     * @return si está vacío da el valor 0 por defecto sino no lo cambia
     */
    public String validarCampoVacioStr (String resultado){
        if (resultado.equals("")) resultado = "0";

        return resultado;
    }

    /**
     * Prepara los campos del tipo Integer si estan vacios dandole valores por defecto para querys
     * @param resultado El valor del campo a validar
     * @return si está vacío da el valor 0 por defecto sino no lo cambia
     */
    public int validarCampoVacioInt (int resultado){
        if (resultado == 0) resultado = 0;

        return resultado;
    }

    /**
     * Controla lo que se muestra por listado tras seleccionar las opciones relacionadas con Libros
     * @param listado Listado a mostrar rellenado segun especificaciones de usuario
     */
    public void montarListadoLibro (List<Libros> listado){
        lvwRenglones.clear();

        for (Libros aListado : listado) {
            lvwRenglones.add(aListado.toString());
        }

        listProperty.set(FXCollections.observableArrayList(lvwRenglones));
        lvwResultado.itemsProperty().bind(listProperty);
    }

    /**
     * Controla lo que se muestra por listado tras seleccionar las opciones relacionadas con Socios
     * @param listado Listado a mostrar rellenado segun especificaciones de usuario
     */
    public void montarListadoSocio (List<Socios> listado){
        lvwRenglones.clear();

        for (Socios aListado : listado) {
            lvwRenglones.add(aListado.toString());
        }

        listProperty.set(FXCollections.observableArrayList(lvwRenglones));
        lvwResultado.itemsProperty().bind(listProperty);
    }


    /**
     * Controla lo que se muestra por listado tras seleccionar las opciones relacionadas con Prestamos
     * @param listado Listado a mostrar rellenado segun especificaciones de usuario
     */
    public void montarListadoPrestamo (List<Prestamo> listado){
        lvwRenglones.clear();

        for (Prestamo aListado : listado) {
            lvwRenglones.add(aListado.toString());
        }

        listProperty.set(FXCollections.observableArrayList(lvwRenglones));
        lvwResultado.itemsProperty().bind(listProperty);
    }

    /**
     * Vacia y muestra el list cuando el resultado de una consulta es 0
     */
    public void vaciarListado (){
        lvwRenglones.clear();

        listProperty.set(FXCollections.observableArrayList(lvwRenglones));
        lvwResultado.itemsProperty().bind(listProperty);

    }

    /**
     * Filtra el listado de prestamos segun si han caducado
     * @param prestamos Listado de prestamos segun especificaciones del usuario
     * @return El listado filtrado
     * @throws ParseException
     */
    public List<Prestamo> filtrarPrestamoCaducado (List<Prestamo> prestamos) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat(ano_mes_dia);

        Calendar calendar = Calendar.getInstance();
        Date fechaActual = calendar.getTime();
        Date fechaFin;

        for (int i = 0; i < prestamos.size(); i++) {
            fechaFin = formatoFecha.parse(prestamos.get(i).getFechaFin());
            System.out.println(fechaActual.compareTo(fechaFin));
            //Comparamos fechas y si no ha caducado lo borra para quedarnos con los caducados
            if (fechaFin.after(fechaActual)) prestamos.remove(i);
        }
        return prestamos;
    }

    /**
     * Este método controla que campos mostrará la aplicación según selección del usuario
     */
    public void mostrarCampos(){

        switch (menuTabla+menuAccion) {
            case 11:                        //Ingresar libros
                txfIdLibros.setVisible(false);
                txfAnoEdicion.setVisible(true);
                txfEditorial.setVisible(true);
                txfTitulo.setVisible(true);
                txfPaginas.setVisible(true);
                txfUnidades.setVisible(true);
                break;

            case 12:                        //Ingresar socios
                txfIdSocios.setVisible(false);
                txfNombre.setVisible(true);
                txfApellido.setVisible(true);
                txfEdad.setVisible(true);
                txfDireccion.setVisible(true);
                txfTelefono.setVisible(true);
                break;

            case 13:                        //Ingresar prestamos
                txfIdPrestamos.setVisible(false);
                txfIdLibroPrestamo.setVisible(true);
                txfIdSocioPrestamo.setVisible(true);
                chbNoDevuelto.setVisible(false);
                txfFechaFinal.setVisible(false);
                txfFechaInicial.setVisible(false);
                break;

            case 21:                        //Eliminar libros
                txfIdLibros.setVisible(true);
                txfAnoEdicion.setVisible(false);
                txfEditorial.setVisible(false);
                txfTitulo.setVisible(false);
                txfPaginas.setVisible(false);
                txfUnidades.setVisible(false);
                break;

            case 22:                        //Eliminar socios
                txfIdSocios.setVisible(true);
                txfNombre.setVisible(false);
                txfApellido.setVisible(false);
                txfEdad.setVisible(false);
                txfDireccion.setVisible(false);
                txfTelefono.setVisible(false);
                break;

            case 23:                        //Eliminar prestamos
                txfIdPrestamos.setVisible(true);
                txfIdLibroPrestamo.setVisible(false);
                txfIdSocioPrestamo.setVisible(false);
                chbNoDevuelto.setVisible(false);
                txfFechaFinal.setVisible(false);
                txfFechaInicial.setVisible(false);
                break;

            case 31:                        //Consultar libros
                txfIdLibros.setVisible(false);
                txfAnoEdicion.setVisible(true);
                txfEditorial.setVisible(true);
                txfTitulo.setVisible(true);
                txfPaginas.setVisible(true);
                txfUnidades.setVisible(true);
                break;

            case 32:                        //Consultar socios
                txfIdSocios.setVisible(false);
                txfNombre.setVisible(true);
                txfApellido.setVisible(true);
                txfEdad.setVisible(true);
                txfDireccion.setVisible(true);
                txfTelefono.setVisible(true);
                break;

            case 33:                        //Consultar prestamos
                txfIdPrestamos.setVisible(false);
                txfIdLibroPrestamo.setVisible(true);
                txfIdSocioPrestamo.setVisible(true);
                chbNoDevuelto.setVisible(true);
                txfFechaFinal.setVisible(true);
                txfFechaInicial.setVisible(true);
                break;
        }
    }

    /**
     * Controla que opciones de tabla se muestran
     */
    public void mostrarMenuTablas(){
        btnLibros.setVisible(true);
        btnSocios.setVisible(true);
        btnPrestamos.setVisible(true);
    }

    /**
     * Controla que opciones muestra segun tabla
     */
    public void mostrarMenuCampos(){

        switch (menuTabla) {
            case 1:                         //Libros
                anpLibros.setVisible(true);
                anpPrestamos.setVisible(false);
                anpSocios.setVisible(false);
                break;
            case 2:                         //Socios
                anpLibros.setVisible(false);
                anpSocios.setVisible(true);
                anpPrestamos.setVisible(false);
                break;
            case 3:                         //Prestamos
                anpLibros.setVisible(false);
                anpSocios.setVisible(false);
                anpPrestamos.setVisible(true);
                break;
        }
    }
}
