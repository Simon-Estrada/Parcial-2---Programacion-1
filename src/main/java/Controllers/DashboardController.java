package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    void showInmobiliariaFormulario(){
        loadView("InmobiliariaFormulario.fxml");
    }

    @FXML
    void showInmobiliariaList(){
        loadView("InmobiliariaList.fxml");
    }

    public void loadView(String fxmlFileName){
        try{
            String fullPath ="/com/example/inmobiliaria/"+fxmlFileName;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fullPath));
            if(mainStackPane.getChildren().size() > 1){
                mainStackPane.getChildren().remove(1, mainStackPane.getChildren().size());
            }

            Node view = loader.load();
            Object controller = loader.getController();
            if(controller instanceof GoBack){
                ((GoBack) controller).setDashboardController(this);
            }
            mainStackPane.getChildren().add(view);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void removeCurrentView(){
        if(mainStackPane.getChildren().size()>1){
            mainStackPane.getChildren().remove(mainStackPane.getChildren().size()-1);
        }
    }
}