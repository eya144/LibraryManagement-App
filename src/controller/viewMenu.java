package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class viewMenu {

    @FXML
    private Button book;

    @FXML
    private Button dictionary;

    @FXML
    private Button magazine;

    @FXML
    private Button newspaper;

    @FXML
    void viewBooks(ActionEvent event) {
    	EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/books.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		book.setOnAction(eventHandler);
    }

    @FXML
    void viewDictionarys(ActionEvent event) {
    	EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/Dictionarys.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		dictionary.setOnAction(eventHandler);
    }

    @FXML
    void viewMagazines(ActionEvent event) {
    	EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/Magazines.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		magazine.setOnAction(eventHandler);
    }

    @FXML
    void viewNewsPapers(ActionEvent event) {
    	EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent eventHandler) {
				Stage AddBook = new Stage();
				try {
					Parent fxml = FXMLLoader.load(getClass().getResource("../view/NewsPapers.fxml"));
					Scene scene = new Scene(fxml);
					AddBook.setScene(scene);
					AddBook.show();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		newspaper.setOnAction(eventHandler);
    }

}
