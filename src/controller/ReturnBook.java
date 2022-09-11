package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import model.Issue;
import model.Document;


public class ReturnBook implements Initializable {

	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
	private TableColumn<Issue, String> firstname;

	@FXML
	private TableColumn<Issue, Integer> id;
	@FXML
	private TableColumn<Issue, Date> date;

	@FXML
	private TextField input;

	@FXML
	private TableColumn<Issue, String> lastname;

	@FXML
	private TableView<Issue> table;

	@FXML
	private TableColumn<Issue, String> title;

	@FXML
	private ComboBox<Document> type;

	@FXML
	void close() {
		Stage stage = (Stage) b1.getScene().getWindow();
		stage.close();
	}

	Connection conn = Connector.getConnection();
	ObservableList<Issue> data = FXCollections.observableArrayList();

	void display() {
		table.setEditable(true);

		input.setOnKeyReleased(e1 -> {
			for (int i = 0; i < table.getItems().size(); i++) {
				table.getItems().clear();
			}

			try {
				String query = "select idissue, title, firstname, lastname, issue_date from issue,"+type.getValue()+",user  where ((issue.id_book = "+type.getValue()+".id) and (issue.id_user = user.iduser)) and ((user.firstname like ? or user.lastname like ?) or (user.firstname like ? and user.lastname like ?))";
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
				ps.setString(1, "%" + input.getText() + "%");
				ps.setString(2, "%" + input.getText() + "%");
				ps.setString(3, "%" + input.getText() + "%");
				ps.setString(4, "%" + input.getText() + "%");

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Issue issue = new Issue(rs.getInt("idissue"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("title"), rs.getDate("issue_date"));
					data.add(issue);
				}
				
				id.setCellValueFactory(new PropertyValueFactory<Issue, Integer>("idissue"));
				firstname.setCellValueFactory(new PropertyValueFactory<Issue, String>("firstname"));
				lastname.setCellValueFactory(new PropertyValueFactory<Issue, String>("lastname"));
				title.setCellValueFactory(new PropertyValueFactory<Issue, String>("title"));
				date.setCellValueFactory(new PropertyValueFactory<Issue, Date>("date"));

				table.setItems(data);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@FXML
	void returnBook(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {

				Issue issue = table.getSelectionModel().getSelectedItem();
				int id = issue.getIdissue();
				String sql = "delete from issue where idissue = ?";
				java.sql.PreparedStatement ps;
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, id);
					int rs = ps.executeUpdate();

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		display();
		
	}

}


