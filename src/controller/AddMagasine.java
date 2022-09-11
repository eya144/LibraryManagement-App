package controller;


import java.sql.SQLException;

import connection.Connector;

import java.sql.Connection;

import java.sql.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddMagasine {

    @FXML
    private Button b2;

    @FXML
    private TextField ft1;

    @FXML
    private TextField ft2;

    @FXML
    private Button b1;

    @FXML
	void reset(ActionEvent event) {
		
			
				ft1.setText("");
				ft2.setText("");
			}
    
    @FXML
    void add(ActionEvent event)  {
    	
    	
    	
    
				String titre;
			
				String field;
    


    titre = ft1.getText();
	field = ft2.getText();
	
	
	if (titre.isEmpty() || field.isEmpty()) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Form Error !");
		alert.setContentText("Please verify your informations");
		alert.showAndWait();
	} else {
		String sql = "insert into  magazine (title, field)  values (?,?)";
		try {
			
			Connection con = (Connection) Connector.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, titre);
			ps.setString(2, field);
			int rs = ps.executeUpdate();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation !");
			alert.setContentText("Add successfully !");
			alert.showAndWait();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}







}
}