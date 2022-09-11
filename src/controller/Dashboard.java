package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Dashboard {
	@FXML
    private Button issue;

	@FXML
	private Button b1;

	@FXML
	private Button b2;

	@FXML
	private Button b3;

	@FXML
	private Button b4;

	@FXML
	private Button b5;

	@FXML
	private Button b6;
	@FXML
	private Button b12;

	@FXML
	void addbook(ActionEvent event) {

		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/categories.fxml"));
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
	void aboutUs(ActionEvent event) {

		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/AboutUS.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		b12.setOnAction(eventHandler);
	}

	@FXML
	void adduser(ActionEvent event) {
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
		b5.setOnAction(eventHandler);
	}

	@FXML
	void displaybooks(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/viewMenu.fxml"));
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
	void dispplayuser(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/Members.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		b6.setOnAction(eventHandler);
	}

	@FXML
    void issueBook(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/issueBook.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		issue.setOnAction(eventHandler);
	
    }

	@FXML
	void logout(ActionEvent event) {

		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		b1.setOnAction(eventHandler);
	}

	@FXML
	void return_book(ActionEvent event) {
		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/returnBook.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		b2.setOnAction(eventHandler);
	}

}
