package lk.ijse.dep.lms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.lms.business.BOFactory;
import lk.ijse.dep.lms.business.custom.ManageAuthorBO;
import lk.ijse.dep.lms.business.custom.ManageAuthor_BookBO;
import lk.ijse.dep.lms.business.custom.ManageBooksBO;
import lk.ijse.dep.lms.dto.AuthorDTO;
import lk.ijse.dep.lms.dto.Author_BookDTO;
import lk.ijse.dep.lms.dto.Author_BookDTOPK;
import lk.ijse.dep.lms.dto.BooksDTO;
import lk.ijse.dep.lms.main.AppInitializer;
import lk.ijse.dep.lms.tablemodel.Author_BookTM;
import lk.ijse.dep.lms.tablemodel.BooksTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageAuthor_BookController {
    @FXML
    private JFXButton btnNew;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgHome;
    @FXML
    private JFXTextField txtBookID;
    @FXML
    private JFXTextField txtBookName;
    @FXML
    private JFXTextField txtAuthorID;
    @FXML
    private JFXTextField txtAuthorName;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<Author_BookTM> tblAuthor_Book;

    private ManageAuthor_BookBO author_bookBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_AUTHOR_BOOK);
    private ManageAuthorBO authorBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_AUTHOR);
    private ManageBooksBO booksBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_BOOKS);

    private static Author_BookDTO[] author_bookDTO = new Author_BookDTO[5];

    public void initialize(){
        tblAuthor_Book.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookid"));
        tblAuthor_Book.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("authorid"));
        btnDelete.setDisable(true);


        List<Author_BookDTO> author_bookDB = null;

        try {
            author_bookDB = author_bookBO.getAuthor_Books();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<Author_BookDTO> author_bookDTOS = FXCollections.observableArrayList(author_bookDB);
        ObservableList<Author_BookTM> author_bookTMS = FXCollections.observableArrayList();

        for (Author_BookDTO ab : author_bookDTOS) {
            author_bookTMS.add(new Author_BookTM(ab.getAuthor_bookDTOPK().getBookid(),ab.getAuthor_bookDTOPK().getAuthorid()));
        }
        tblAuthor_Book.setItems(author_bookTMS);


        tblAuthor_Book.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Author_BookTM>() {
            @Override
            public void changed(ObservableValue<? extends Author_BookTM> observable, Author_BookTM oldValue, Author_BookTM newValue) {
                if (newValue==null){
                    return;
                }

                txtAuthorID.setText(newValue.getAuthorid());
                txtBookID.setText(newValue.getBookid());
                btnDelete.setDisable(false);
            }
        });

    }

    @FXML
    private void navigate(MouseEvent event) throws IOException {
        AppInitializer.navigateToHome(root, (Stage) this.root.getScene().getWindow());
    }

    @FXML
    private void PlayEnterAnimation(MouseEvent event) {
        ManageAuthorController.PlayEnterAnimationMethod(event);
    }

    @FXML
    private void PlayExitAnimation(MouseEvent event) {
        ManageAuthorController.PlayExitAnimationMethod(event);
    }

    @FXML
    private void txtBookId_OnAction(ActionEvent actionEvent) {
        String bookid = txtBookID.getText();
        ManagePublisher_BookController.getbookdetails(bookid, booksBO, txtBookName);

    }

    @FXML
    private void txtAuthorID_OnAction(ActionEvent actionEvent) {
        String authorid = txtAuthorID.getText();
        try {
            AuthorDTO author = authorBO.findAuthor(authorid);
            txtAuthorName.setText(author.getAuthorname());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
            new Alert(Alert.AlertType.ERROR,"Invalid Author ID", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void btnSave_OnAction(ActionEvent actionEvent) {
        if (txtBookID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Book ID is empty", ButtonType.OK).showAndWait();
            txtBookID.requestFocus();
            return;
        } else if (txtAuthorID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Author ID is empty", ButtonType.OK).showAndWait();
            txtAuthorID.requestFocus();
            return;
        }

        if (tblAuthor_Book.getSelectionModel().isEmpty()) {
            // New Author_Books
            ObservableList<Author_BookTM> author_books = tblAuthor_Book.getItems();
            for (Author_BookTM author_bookTM : author_books) {
                if (author_bookTM.getBookid().equals(txtBookID.getText()) && author_bookTM.getAuthorid().equals(txtAuthorID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate IDs are not allowed").showAndWait();
                    txtBookID.requestFocus();
                    return;
                }
            }

            Author_BookTM author_bookTM = new Author_BookTM(txtBookID.getText(),txtAuthorID.getText());
            tblAuthor_Book.getItems().add(author_bookTM);
            Author_BookDTO author_book = new Author_BookDTO(txtBookID.getText(),txtAuthorID.getText());
            boolean result = false;
            try {
                result = author_bookBO.createAuthor_Books(author_book);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Author details to Book has been saved successfully", ButtonType.OK).showAndWait();
                tblAuthor_Book.scrollTo(author_bookTM);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Add", ButtonType.OK).showAndWait();
            }


            author_bookDTO[0] = author_book;

        } else {
            //Update Author_Book


        }
        reset();

    }

    @FXML
    private void btnDelete_OnAction(ActionEvent actionEvent) {
        Alert cnfrm = new Alert(Alert.AlertType.WARNING, "Confirm to Delete Record",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = cnfrm.showAndWait();
        if (buttonType.get()==ButtonType.YES) {
            String selectedBook = tblAuthor_Book.getSelectionModel().getSelectedItem().getBookid();
            String selectedAuthor = tblAuthor_Book.getSelectionModel().getSelectedItem().getAuthorid();
            tblAuthor_Book.getItems().remove(tblAuthor_Book.getSelectionModel().getSelectedItem());
            tblAuthor_Book.refresh();

            boolean result = false;


            try {
                result = author_bookBO.deleteAuthor_Books(new Author_BookDTOPK(selectedAuthor,selectedBook));
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Record Deleted Successfully", ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Delete Failed", ButtonType.OK).showAndWait();
            }
            reset();
        }
    }

    public void reset(){
        txtAuthorID.clear();
        txtAuthorName.clear();
        txtBookID.clear();
        txtBookName.clear();
        tblAuthor_Book.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnNew_OnAction(ActionEvent actionEvent) {
        reset();
        txtBookID.requestFocus();
    }

    public static Author_BookDTO[] getauthor_book(){
        return author_bookDTO;
    }
}
