package Controllers;

import Objects.Inmueble;
import Objects.InmuebleRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class InmobiliariaFormularioController implements GoBack{
    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtHabitaciones;

    @FXML
    private TextField txtPisos;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtTipo;

    @FXML
    public void initialize(){
        inmueblesRepositorio = InmuebleRepository.getInstance();
    }
    public DashboardController dashboardController;
    private InmuebleRepository inmueblesRepositorio;

    @Override
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
    @FXML
    public void onAgregar(){
        if(!validarCampos()){
            mostrarAlerta("Error ", "Por favor completa todos los campos correctamente.", Alert.AlertType.ERROR);
            return;
        }
        try{
            String tipo = txtTipo.getText();
            String ciudad = txtCiudad.getText();
            int habitaciones = Integer.parseInt(txtHabitaciones.getText());
            int pisos = Integer.parseInt(txtPisos.getText());
            double precio = Double.parseDouble(txtPrecio.getText());
            Inmueble nuevoInmueble = new Inmueble(tipo, ciudad,habitaciones, pisos, precio);
            inmueblesRepositorio.agregarInmueble(nuevoInmueble);
            mostrarAlerta("Exito ","Inmueble agregado correctamente.", Alert.AlertType.INFORMATION);
            onCancelar();
        } catch (NumberFormatException e){
            mostrarAlerta("Error ", "ingrese un numero valido.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al agregar el Inmueble: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void onCancelar(){
        if(dashboardController!=null){
            dashboardController.removeCurrentView();
        }
    }
    private boolean validarCampos(){
        if(txtTipo.getText().isEmpty() || txtCiudad.getText().isEmpty() || txtHabitaciones.getText().isEmpty() || txtPisos.getText().isEmpty()||
                txtPrecio.getText().isEmpty() ){
            mostrarAlerta("Error", "Todos los campos deben ser diligenciados obligatoriamente.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
