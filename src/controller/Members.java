package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.User;

public class Members implements Initializable {

	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
	private Button b3;

	@FXML
	private TableColumn<User, String> email;

	@FXML
	private TableColumn<User, String> firstname;

	@FXML
	private TableColumn<User, Integer> id;

	@FXML
	private TableColumn<User, String> lastname;

	@FXML
	private TableColumn<User, String> phone;

	@FXML
	private TableView<User> table;
	Connection conn = Connector.getConnection();
	ObservableList<User> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		table.setEditable(true);
		String sql = "select iduser, firstname, lastname, email, phone from user";
		java.sql.PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("email"), rs.getString("phone"));
				data.add(user);
			}
			id.setCellValueFactory(new PropertyValueFactory<User, Integer>("iduser"));
			firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
			lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
			email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
			phone.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));

			firstname.setCellFactory(TextFieldTableCell.forTableColumn());
			lastname.setCellFactory(TextFieldTableCell.forTableColumn());
			email.setCellFactory(TextFieldTableCell.forTableColumn());
			phone.setCellFactory(TextFieldTableCell.forTableColumn());

			firstname.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
				public void handle(CellEditEvent<User, String> t) {
					((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
							.setFirstname(t.getNewValue());
					int id = ((User) t.getTableView().getItems().get(t.getTablePosition().getRow())).getIduser();
					String query = "update user set firstname = ? where iduser = ?";
					java.sql.PreparedStatement ps;
					try {
						ps = conn.prepareStatement(query);
						ps.setString(1, t.getNewValue());
						ps.setInt(2, id);
						int rs = ps.executeUpdate();
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation !");
						alert.setContentText("Add successfully !");
						alert.showAndWait();

					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			});
			
			lastname.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
				public void handle(CellEditEvent<User, String> t) {
					((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
							.setLastname(t.getNewValue());
					int id = ((User) t.getTableView().getItems().get(t.getTablePosition().getRow())).getIduser();
					String query = "update user set lastname = ? where iduser = ?";
					java.sql.PreparedStatement ps;
					try {
						ps = conn.prepareStatement(query);
						ps.setString(1, t.getNewValue());
						ps.setInt(2, id);
						int rs = ps.executeUpdate();
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation !");
						alert.setContentText("Add successfully !");
						alert.showAndWait();

					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			});
			
			email.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
				public void handle(CellEditEvent<User, String> t) {
					((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
							.setEmail(t.getNewValue());
					int id = ((User) t.getTableView().getItems().get(t.getTablePosition().getRow())).getIduser();
					String query = "update user set email = ? where iduser = ?";
					java.sql.PreparedStatement ps;
					try {
						ps = conn.prepareStatement(query);
						ps.setString(1, t.getNewValue());
						ps.setInt(2, id);
						int rs = ps.executeUpdate();
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation !");
						alert.setContentText("Add successfully !");
						alert.showAndWait();

					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			});
			
			phone.setOnEditCommit(new EventHandler<CellEditEvent<User, String>>() {
				public void handle(CellEditEvent<User, String> t) {
					((User) t.getTableView().getItems().get(t.getTablePosition().getRow()))
							.setPhone(t.getNewValue());
					int id = ((User) t.getTableView().getItems().get(t.getTablePosition().getRow())).getIduser();
					String query = "update user set phone = ? where iduser = ?";
					java.sql.PreparedStatement ps;
					try {
						ps = conn.prepareStatement(query);
						ps.setString(1, t.getNewValue());
						ps.setInt(2, id);
						int rs = ps.executeUpdate();
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation !");
						alert.setContentText("Add successfully !");
						alert.showAndWait();

					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			});

			table.setItems(data);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@FXML
	void add(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/addMember.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		b3.setOnAction(eventHandler);
	}

	@FXML
	void close() {
		Stage stage = (Stage) b1.getScene().getWindow();
		stage.close();
	}

	@FXML
	void delete(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {

				User user = (User) table.getSelectionModel().getSelectedItem();
				int num = table.getSelectionModel().getSelectedIndex();
				int id = user.getIduser();
				String sql = "delete from user  where iduser = ?";
				java.sql.PreparedStatement ps;
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					int rs = ps.executeUpdate();

					data.remove(num);
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation !");
					alert.setContentText("Deleted successfully !");
					alert.showAndWait();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		};
		b2.setOnAction(eventHandler);

	}

}
