package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class aboutusController {
	
	
	
	@FXML
    private void back(ActionEvent event)throws IOException {
		try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Back");
			stage.setScene(new Scene(root,1190,800));
			stage.show();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
        
    }

}
