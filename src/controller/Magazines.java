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
import model.Magazine;

public class Magazines implements Initializable {

	

	@FXML
	private Button b1;

	@FXML
	private Button b2;


	@FXML
	private Button b4;

	@FXML
	private TableColumn<Magazine, Integer> id;

	@FXML
	private TableView<Magazine> table;

	@FXML
	private TableColumn<Magazine, String> title;
	
	@FXML
	private TableColumn<Magazine, String> field;

	@FXML
	void add(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/magazine.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		b4.setOnAction(eventHandler);
	}

	@FXML
	void close() {
		Stage stage = (Stage) b1.getScene().getWindow();
	    stage.close();
	    }

	@FXML
	void delete(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler <ActionEvent> ()
		{
	@Override
	public void handle(ActionEvent eventHandler)
	{
	
		Magazine magazine = table.getSelectionModel().getSelectedItem();
		int num = table.getSelectionModel().getSelectedIndex();
		int id = magazine.getId();
		String sql = "delete from magazine  where id = ?";
         java.sql.PreparedStatement ps;
         try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
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


	Connection conn = Connector.getConnection();
	ObservableList<Magazine> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		table.setEditable(true);
		String sql = "select id, field, title from Magazine";
		java.sql.PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Magazine Magazine = new Magazine(rs.getInt("id"), rs.getString("title"), rs.getString("field"));
				data.add(Magazine);
				System.out.println(Magazine.toString());
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("title"));
				System.out.println(rs.getString("field"));
			}
			id.setCellValueFactory(new PropertyValueFactory<Magazine, Integer>("id"));
			title.setCellValueFactory(new PropertyValueFactory<Magazine, String>("title"));
			field.setCellValueFactory(new PropertyValueFactory<Magazine, String>("field"));
			
			title.setCellFactory(TextFieldTableCell.forTableColumn());
			field.setCellFactory(TextFieldTableCell.forTableColumn());

			title.setOnEditCommit(new EventHandler<CellEditEvent<Magazine, String>>() {
				public void handle(CellEditEvent<Magazine, String> t) {
					((Magazine) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTitle(t.getNewValue());
					int id = ((Magazine) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId();
					String sql = "update Magazine set title = ? where id = ?";
					java.sql.PreparedStatement ps;
					try {
						ps = conn.prepareStatement(sql);
						ps.setString(1, t.getNewValue());
						ps.setInt(2, id);
						int rs = ps.executeUpdate();
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation !");
						alert.setContentText("Modified successfully !");
						alert.showAndWait();

					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			});

			field.setOnEditCommit(new EventHandler<CellEditEvent<Magazine, String>>() {
				public void handle(CellEditEvent<Magazine, String> t) {
					((Magazine) t.getTableView().getItems().get(t.getTablePosition().getRow())).setField(t.getNewValue());
					int id = ((Magazine) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId();
					String sql = "update Magazine set field = ? where id = ?";
					java.sql.PreparedStatement ps;
					try {
						ps = conn.prepareStatement(sql);
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

}
