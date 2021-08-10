/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author ymont
 */
public class Aplicacao extends Application {
    
    private static Scene mainScene;

    @Override
    public void start(Stage stage){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
        ScrollPane root = loader.load();
        root.setFitToHeight(true);
        root.setFitToWidth(true);
        mainScene  = new Scene(root);
        stage.setScene(mainScene);
        stage.setTitle("Aplicação JavaFX Simples");
        stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static Scene getMainScene(){
        return mainScene;
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
