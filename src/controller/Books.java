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
import model.Book;

public class Books implements Initializable {

	

	@FXML
	private Button b1;

	@FXML
	private Button b2;


	@FXML
	private Button b4;

	@FXML
	private TableColumn<Book, Integer> id;

	@FXML
	private TableView<Book> table;

	@FXML
	private TableColumn<Book, String> title;
	
	@FXML
	private TableColumn<Book, String> author;

	@FXML
	void add(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/AddBook.fxml"));
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
		
			Book book =table.getSelectionModel().getSelectedItem();
			int num =table.getSelectionModel().getSelectedIndex();
			int id = book.getId();
			String sql = "delete from book  where id = ?";
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
	ObservableList<Book> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		table.setEditable(true);
		String sql = "select id, author, title from book";
		java.sql.PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"));
				data.add(book);
				System.out.println(book.toString());
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("title"));
				System.out.println(rs.getString("author"));
			}
			id.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
			title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
			author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
			
			title.setCellFactory(TextFieldTableCell.forTableColumn());
			author.setCellFactory(TextFieldTableCell.forTableColumn());

			title.setOnEditCommit(new EventHandler<CellEditEvent<Book, String>>() {
				public void handle(CellEditEvent<Book, String> t) {
					((Book) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTitle(t.getNewValue());
					int id = ((Book) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId();
					String sql = "update book set title = ? where id = ?";
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

			author.setOnEditCommit(new EventHandler<CellEditEvent<Book, String>>() {
				public void handle(CellEditEvent<Book, String> t) {
					((Book) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAuthor(t.getNewValue());
					int id = ((Book) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId();
					String sql = "update book set author = ? where id = ?";
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
