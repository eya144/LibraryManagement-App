package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import connection.Connector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class AddBook {
	Connection con = Connector.getConnection();
	public java.sql.PreparedStatement preparedStatement;

	@FXML
	private Button add;

	@FXML
	private Button reset;

	@FXML
	private TextField ft2;

	@FXML
	private TextField ft1;
	
	@FXML
	void reset(ActionEvent event) {
		
			
				ft1.setText("");
				ft2.setText("");
			}

	
	
	@FXML
	void add(ActionEvent event) {
		
				String titre;
				String author;
			


			
					titre = ft1.getText();
					author = ft2.getText();
					if (titre.isEmpty() || author.isEmpty()) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Form Error !");
						alert.setContentText("Please verify your informations");
						alert.showAndWait();
					} else {
						String sql = "insert into  book (title, author)  values (?,?)";
						try {
							PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
							ps.setString(1, titre);
							ps.setString(2, author);
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
