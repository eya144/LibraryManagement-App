package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import connection.Connector;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class Login  {
	Connection conn =  Connector.getConnection();

    @FXML
    private Button log;

    @FXML
    private TextField password;

    @FXML
    private TextField username;
    
    @FXML
	void Annuler(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

    @FXML
    void login(ActionEvent event) throws SQLException {
        java.sql.PreparedStatement preparedStatement;
        ResultSet res;
        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
        
        
        
        
        
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username.getText().toString());
            preparedStatement.setString(2, password.getText().toString());
            res = preparedStatement.executeQuery();
            if (res.next()) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Home");
                    stage.setScene(new Scene(root, 900, 650));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
else {
	        	
	        	
	        	Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("Error");
	    		alert.setContentText("Wrong username or password ");
	    		alert.showAndWait(); 
	         
	        }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("erreur " + e.getMessage());
        }
    }

   

}
