package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class Categories {

   

	@FXML
    private void b1(ActionEvent event)throws IOException {
		try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/AddBook.fxml"));
			Stage stage= new Stage();
			stage.setTitle("About Books");
			stage.setScene(new Scene(root,1190,800));
			stage.show();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
        
    }
	@FXML
    private void b4(ActionEvent event)throws IOException {
		try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/AddDictionnary.fxml"));
			Stage stage= new Stage();
			stage.setTitle("Add Dictionnary");
			stage.setScene(new Scene(root,1190,800));
			stage.show();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
        
    }



	@FXML
    private void b2(ActionEvent event)throws IOException {
		try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/addJournal.fxml"));
			Stage stage= new Stage();
			stage.setTitle("About Books");
			stage.setScene(new Scene(root,1190,800));
			stage.show();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
        
    }



	@FXML
    private void b3(ActionEvent event)throws IOException {
		try {
			 
			Parent root=FXMLLoader.load(getClass().getResource("/view/magazine.fxml"));
			Stage stage= new Stage();
			stage.setTitle("About Books");
			stage.setScene(new Scene(root,1190,800));
			stage.show();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
        
    }



}
