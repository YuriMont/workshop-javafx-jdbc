
package gui;

import gui.util.Constraints;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormControler implements Initializable{

    private Department entity;
    
    @FXML 
    private TextField txtId;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private Label labelErroName;
    
    @FXML
    private Button btSave;
    
    @FXML
    private Button btCancel;
    
    public void setDepartment(Department entity){
        this.entity=entity;
    }
    
    @FXML
    public void onBtSaveAction(){
        System.out.println("onBtSaveAction");
    }
    
    @FXML
    public void onBtCancelAction(){
        System.out.println("onBtCancelAction");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void initializeNodes(){
        Constraints.setTextFildInteger(txtId);
        Constraints.setTextFildMaxLength(txtName, 30);
    }
    
    public void updateFormData(){
        if(entity==null){
            throw new IllegalStateException("Entity está vazio");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(String.valueOf(entity.getName()));
        
    }
    
}
