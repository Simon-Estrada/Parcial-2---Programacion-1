package Controllers;

import Objects.Inmueble;
import Objects.InmuebleRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InmobiliariaFormularioController implements GoBack {
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

    private DashboardController dashboardController;
    private InmuebleRepository inmueblesRepositorio;

    @FXML
    public void initialize() {
        inmueblesRepositorio = InmuebleRepository.getInstance();
    }

    @Override
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    public void onAgregar() {

        if (txtTipo.getText().trim().isEmpty() ||
                txtCiudad.getText().trim().isEmpty() ||
                txtHabitaciones.getText().trim().isEmpty() ||
                txtPisos.getText().trim().isEmpty() ||
                txtPrecio.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben ser diligenciados obligatoriamente.", Alert.AlertType.WARNING);
            return;
        }

        try {
            String tipo = txtTipo.getText().trim();
            String ciudad = txtCiudad.getText().trim();
            int habitaciones = Integer.parseInt(txtHabitaciones.getText().trim());
            int pisos = Integer.parseInt(txtPisos.getText().trim());
            double precio = Double.parseDouble(txtPrecio.getText().trim());


            if (habitaciones <= 0 || pisos <= 0 || precio <= 0) {
                mostrarAlerta("Error", "Los valores numéricos deben ser mayores a cero.", Alert.AlertType.ERROR);
                return;
            }

            Inmueble nuevoInmueble = new Inmueble(tipo, ciudad, habitaciones, pisos, precio);
            inmueblesRepositorio.agregarInmueble(nuevoInmueble);

            mostrarAlerta("Éxito", "Inmueble agregado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            onCancelar();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese valores numéricos válidos en Habitaciones, Pisos y Precio.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al agregar el Inmueble: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void onCancelar() {
        limpiarCampos();
        if (dashboardController != null) {
            dashboardController.removeCurrentView();
        }
    }

    private void limpiarCampos() {
        txtTipo.clear();
        txtCiudad.clear();
        txtHabitaciones.clear();
        txtPisos.clear();
        txtPrecio.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}