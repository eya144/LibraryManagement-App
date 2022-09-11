package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import java.sql.PreparedStatement;

import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.User;
import model.Document;

public class IssueBook implements Initializable {
	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
    private ComboBox<String> type;

	@FXML
	private TextField book;

	@FXML
	private TableColumn<User, String> firstname;

	@FXML
	private TableColumn<Document, Integer> idbook;

	@FXML
	private TableColumn<User, Integer> iduser;

	@FXML
	private TableColumn<User, String> lastname;

	@FXML
	private TableView<Document> tableBook;

	@FXML
	private TableView<User> tableUser;

	@FXML
	private TableColumn<Document, String> title;

	@FXML
	private TextField user;

	Connection conn = Connector.getConnection();
	ObservableList<User> userData = FXCollections.observableArrayList();
	ObservableList<Document> bookData = FXCollections.observableArrayList();

	void resultUser() {
		tableUser.setEditable(true);
		
		user.setOnKeyReleased(e1 -> {
			for ( int i = 0; i<tableUser.getItems().size(); i++) {
			    tableUser.getItems().clear();
			}

			try {
				String query = "select iduser, firstname, lastname from user where (firstname like ? or lastname like ?) or (firstname like ? and lastname like ?)";
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
				ps.setString(1, "%"+user.getText()+"%");
				ps.setString(2, "%"+user.getText()+"%");
				ps.setString(3, "%"+user.getText()+"%");
				ps.setString(4, "%"+user.getText()+"%");
				
				
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					User user = new User(rs.getInt("iduser"), rs.getString("firstname"), rs.getString("lastname"));
					userData.add(user);
				}
				iduser.setCellValueFactory(new PropertyValueFactory<User, Integer>("iduser"));
				firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
				lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
				tableUser.setItems(userData);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	void resultBook() {
tableUser.setEditable(true);
		
		book.setOnKeyReleased(e1 -> {
			for ( int i = 0; i<tableBook.getItems().size(); i++) {
			    tableBook.getItems().clear();
			}

			try {
				
				String query = "select id, title from "+type.getValue()+" where (title like ? )";
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
				
				
				ps.setString(1, "%"+book.getText()+"%");
				
				
				
				
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Document doc = new Document(rs.getInt("id"), rs.getString("title"));
					bookData.add(doc);
				}
				
				idbook.setCellValueFactory(new PropertyValueFactory<Document, Integer>("id"));
				title.setCellValueFactory(new PropertyValueFactory<Document, String>("title"));
				tableBook.setItems(bookData);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	@FXML
	void close() {
		Stage stage = (Stage) b1.getScene().getWindow();
	    stage.close();
	    }
	@FXML
    void issue(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler <ActionEvent> ()
		{
	@Override
	public void handle(ActionEvent eventHandler)
	{
	
		Document doc = tableBook.getSelectionModel().getSelectedItem();
		User user = tableUser.getSelectionModel().getSelectedItem();
		
		int idbook = doc.getId();
		int iduser = user.getIduser();

		
		String query = "insert into issue (id_book, id_user) values (?,?)";
         java.sql.PreparedStatement ps;
         try {
			ps = conn.prepareStatement(query);
			ps.setInt(1,idbook);
			ps.setInt(2,iduser);
			
			
	    	int rs = ps.executeUpdate();
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation !");
			alert.setContentText("Issued successfully !");
			alert.showAndWait();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			
	}
	
		};
		b2.setOnAction(eventHandler);


	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		resultUser();
		resultBook();

	}

}
