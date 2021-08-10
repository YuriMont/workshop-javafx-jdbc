
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListControler implements Initializable{

    @FXML
    private  TableView<Department> tableViewDepartment;
    
    @FXML
    private TableColumn<Department, Integer> tableColumnId;
    
    @FXML
    private TableColumn<Department, String> tableColumnNome;
    
    @FXML
    private Button btNovo;
    
    @FXML
    public void onBtNovoAction(){
        System.out.println("onBtNovoAction");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        Stage stage = (Stage) aplicacao.Aplicacao.getMainScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
    }
    
}
