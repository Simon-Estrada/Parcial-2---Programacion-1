package Controllers;

import Objects.Inmueble;
import Objects.InmuebleRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class InmobiliariaListController implements GoBack{

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Inmueble, String> colCiudad;

    @FXML
    private TableColumn<Inmueble, String> colHabitaciones;

    @FXML
    private TableColumn<Inmueble,Integer> colPisos;

    @FXML
    private TableColumn<Inmueble, Double> colPrecio;

    @FXML
    private TableColumn<Inmueble,String> colTipo;

    @FXML
    private VBox contenedorListado;

    @FXML
    private TableView<Inmueble> tablaInmuebles;

    private DashboardController dashboardController;
    private InmuebleRepository inmuebleRepository;
    private ObservableList<Inmueble> inmueblesObservable;

    @FXML
    public void initialize() {
        inmuebleRepository = InmuebleRepository.getInstance();
        configurarTabla();
        cargarInmuebles();
        btnEliminar.setDisable(true);

        tablaInmuebles.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    btnEliminar.setDisable(newValue == null);
                }
        );
    }
    @Override
    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }
    private void configurarTabla(){
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colHabitaciones.setCellValueFactory(new PropertyValueFactory<>("habitaciones"));
        colPisos.setCellValueFactory(new PropertyValueFactory<>("pisos"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    }
    private void cargarInmuebles(){
        inmueblesObservable = FXCollections.observableArrayList(
                inmuebleRepository.getInmuebles());
        tablaInmuebles.setItems(inmueblesObservable);
    }
    @FXML
    void onVolverDashboard() {
        if (dashboardController != null) {
            dashboardController.removeCurrentView();
        }
    }

    @FXML
    void onCrearInmueble() {
        if (dashboardController != null) {
            dashboardController.loadView("InmobiliariaFormulario.fxml");
        }
    }

    @FXML
    void onEliminarInmueble() {
        Inmueble inmuebleSeleccionado = tablaInmuebles.getSelectionModel().getSelectedItem();
        if(inmuebleSeleccionado == null){
            mostrarAlerta("Error", "Seleccione un inmueble para eliminar.", Alert.AlertType.WARNING);
            return;
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Estás seguro?");
        confirmacion.setContentText("¿Deseas eliminar el inmueble: " + inmuebleSeleccionado.toString() + "?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if(resultado.isPresent() && resultado.get() == ButtonType.OK){
            try{
                inmuebleRepository.eliminarInmueble(inmuebleSeleccionado);
                cargarInmuebles();
                mostrarAlerta("Exito", "inmueble eliminado correctamente.", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                mostrarAlerta("Exito", "Error al eliminar el inmueble: "+ e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
    public void actualizarTabla() {
        cargarInmuebles();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
