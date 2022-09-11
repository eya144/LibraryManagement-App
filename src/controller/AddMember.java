package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.Connector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddMember {

	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
	private Button b3;

	@FXML
	private TextField email;

	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private TextField phone;

	@FXML
	private TextField username;
	Connection conn = Connector.getConnection();

	@FXML
	void addMember(ActionEvent event) {
		String firstnameValue = firstname.getText();
		String lastnameValue = lastname.getText();
		String emailValue = email.getText();
		String phoneValue = phone.getText();

		if (firstnameValue.isEmpty() || lastnameValue.isEmpty() || phoneValue.isEmpty() || emailValue.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Form Error !");
			alert.setContentText("Please verify your informations");
			alert.showAndWait();
		} else {
			EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent eventHandler) {
					Stage AdminInter = new Stage();
					String sql = "insert into user (firstname, lastname, email, phone) values (?,?,?,?)";
					java.sql.PreparedStatement ps;
					try {
						ps = conn.prepareStatement(sql);
						ps.setString(1, firstnameValue);
						ps.setString(2, lastnameValue);
						ps.setString(3, emailValue);
						ps.setString(4, phoneValue);
						int rs = ps.executeUpdate();
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation !");
						alert.setContentText("Add successfully !");
						alert.showAndWait();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			};

			b3.setOnAction(eventHandler);

		}
	}

	@FXML
	void close() {
		Stage stage = (Stage) b1.getScene().getWindow();
		stage.close();
	}

	@FXML
	void reset(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				firstname.setText("");
				lastname.setText("");
				email.setText("");
				phone.setText("");
			}

		};

		b2.setOnAction(eventHandler);
	}

}
