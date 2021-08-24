
package gui;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormControler implements Initializable{
    
    private DepartmentService service;

    private Department entity;
    
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
    
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
    
    public void setDepartmentService(DepartmentService service){
        this.service = service;
    }
    
    public void subscribeDataChangeListener(DataChangeListener listener){
        dataChangeListeners.add(listener);
    }
    
    @FXML
    public void onBtSaveAction(ActionEvent event){
        if(entity == null){
            throw new IllegalStateException("Entity está vazio");
        }
        if(service == null){
             throw new IllegalStateException("Service está vazio");
        }
        try{
        entity = getFormData();
        service.saveOrUpdate(entity);
        notifyDataChangeListeners();
        Utils.currentStage(event).close();
        }catch(DbException e){
            Alerts.showAlert("Erro salvando objeto", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    public void onBtCancelAction(ActionEvent event){
        Utils.currentStage(event).close();
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

    private Department getFormData() {
        Department obj = new Department();
        
        obj.setId(Utils.tryParsetoInt(txtId.getText()));
        obj.setName(txtName.getText());
        
        return obj;
    }

    private void notifyDataChangeListeners() {
        for(DataChangeListener listener : dataChangeListeners){
            listener.onDataChanged();
        }
    }
    
}
