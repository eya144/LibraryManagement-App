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
import model.NewsPaper;

public class NewsPapers implements Initializable {

	

	@FXML
	private Button b1;

	@FXML
	private Button b2;


	@FXML
	private Button b4;

	@FXML
	private TableColumn<NewsPaper, Integer> id;

	@FXML
	private TableView<NewsPaper> table;

	@FXML
	private TableColumn<NewsPaper, String> title;
	
	@FXML
	private TableColumn<NewsPaper, String> printDate;

	@FXML
	void add(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/addJournal.fxml"));
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
	
		NewsPaper news =table.getSelectionModel().getSelectedItem();
		int num = table.getSelectionModel().getSelectedIndex();
		int id = news.getId();
		String sql = "delete from newspaper where id = ?";
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
	ObservableList<NewsPaper> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		table.setEditable(true);
		String sql = "select id, printdate, title from newspaper";
		java.sql.PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NewsPaper newspaper = new NewsPaper(rs.getInt("id"), rs.getString("title"), rs.getString("printdate"));
				data.add(newspaper);
				System.out.println(newspaper.toString());
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("title"));
				System.out.println(rs.getString("printdate"));
			}
			id.setCellValueFactory(new PropertyValueFactory<NewsPaper, Integer>("id"));
			title.setCellValueFactory(new PropertyValueFactory<NewsPaper, String>("title"));
			printDate.setCellValueFactory(new PropertyValueFactory<NewsPaper, String>("printdate"));
			
			title.setCellFactory(TextFieldTableCell.forTableColumn());
			printDate.setCellFactory(TextFieldTableCell.forTableColumn());

			title.setOnEditCommit(new EventHandler<CellEditEvent<NewsPaper, String>>() {
				public void handle(CellEditEvent<NewsPaper, String> t) {
					((NewsPaper) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTitle(t.getNewValue());
					int id = ((NewsPaper) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId();
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

			printDate.setOnEditCommit(new EventHandler<CellEditEvent<NewsPaper, String>>() {
				public void handle(CellEditEvent<NewsPaper, String> t) {
					((NewsPaper) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPrintdate(t.getNewValue());
					int id = ((NewsPaper) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId();
					String sql = "update newspaper set printdate = ? where id = ?";
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
