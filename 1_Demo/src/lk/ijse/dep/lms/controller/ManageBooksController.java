package lk.ijse.dep.lms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.lms.business.BOFactory;
import lk.ijse.dep.lms.business.custom.ManageBooksBO;
import lk.ijse.dep.lms.dto.AuthorDTO;
import lk.ijse.dep.lms.dto.BooksDTO;
import lk.ijse.dep.lms.dto.BorrowDTO;
import lk.ijse.dep.lms.dto.MemberDTO;
import lk.ijse.dep.lms.entity.Books;
import lk.ijse.dep.lms.main.AppInitializer;
import lk.ijse.dep.lms.tablemodel.BooksTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageBooksController {
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnRelateAuthor;
    @FXML
    private JFXButton btnRelatePublisher;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtBookID;
    @FXML
    private ImageView imgHome;
    @FXML
    private JFXTextField txtBookName;
    @FXML
    private JFXTextField txtBookCategory;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<BooksTM> tblBooks;

    private ManageBooksBO manageBooksBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_BOOKS);
    private static BooksDTO[] booksDTOS = new BooksDTO[5];


    public void initialize(){
        tblBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookid"));
        tblBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookname"));
        tblBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookcategory"));
        btnDelete.setDisable(true);

        List<BooksDTO> booksDB = null;

        try {
            booksDB = manageBooksBO.getBooks();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<BooksDTO> booksDTOS = FXCollections.observableArrayList(booksDB);
        ObservableList<BooksTM> booksTMS = FXCollections.observableArrayList();
        for (BooksDTO booksDTO : booksDTOS) {
            booksTMS.add(new BooksTM(booksDTO.getBookid(),booksDTO.getBookname(),booksDTO.getBookcategory()));
        }
        tblBooks.setItems(booksTMS);

        tblBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BooksTM>() {
            @Override
            public void changed(ObservableValue<? extends BooksTM> observable, BooksTM oldValue, BooksTM newValue) {
                if (newValue == null){
                    return;
                }

                txtBookID.setText(newValue.getBookid());
                txtBookName.setText(newValue.getBookname());
                txtBookCategory.setText(newValue.getBookcategory());
                txtBookID.setEditable(false);
                btnSave.setText("Update");
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
    private void btnSave_OnAction(ActionEvent actionEvent) {

        if (txtBookID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Book ID is empty", ButtonType.OK).showAndWait();
            txtBookID.requestFocus();
            return;
        } else if (txtBookName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Book Name is empty", ButtonType.OK).showAndWait();
            txtBookName.requestFocus();
            return;
        } else if (txtBookCategory.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Book Category is empty", ButtonType.OK).showAndWait();
            txtBookCategory.requestFocus();
            return;
        }

        if (tblBooks.getSelectionModel().isEmpty()) {
            // New Books
            ObservableList<BooksTM> books = tblBooks.getItems();
            for (BooksTM booksTM : books) {
                if (booksTM.getBookid().equals(txtBookID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Book IDs are not allowed").showAndWait();
                    txtBookID.requestFocus();
                    return;
                }
            }

            BooksTM booksTM = new BooksTM(txtBookID.getText(), txtBookName.getText(), txtBookCategory.getText());
            tblBooks.getItems().add(booksTM);
            BooksDTO booksDTO = new BooksDTO(txtBookID.getText(), txtBookName.getText(), txtBookCategory.getText());

            booksDTOS[0]= booksDTO;


            boolean result = false;
            try {
                result = manageBooksBO.createBooks(booksDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Book has been saved successfully", ButtonType.OK).showAndWait();
                tblBooks.scrollTo(booksTM);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Add", ButtonType.OK).showAndWait();
            }


        } else {
            //Update Book

            BooksTM selectedBooks = tblBooks.getSelectionModel().getSelectedItem();
            selectedBooks.setBookname(txtBookName.getText());
            selectedBooks.setBookcategory(txtBookCategory.getText());
            tblBooks.refresh();

            boolean result = false;

            try {
                result = manageBooksBO.updateBooks(new BooksDTO(txtBookID.getText(),txtBookName.getText(),txtBookCategory.getText()));
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
            btnSave.setText("Save");

        }
        reset();

    }
    @FXML
    private void btnDelete_OnAction(ActionEvent actionEvent) {
        Alert cnfrm = new Alert(Alert.AlertType.WARNING, "Confirm to Delete Record",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = cnfrm.showAndWait();
        if (buttonType.get()==ButtonType.YES) {
            String selectedItem = tblBooks.getSelectionModel().getSelectedItem().getBookid();
            tblBooks.getItems().remove(tblBooks.getSelectionModel().getSelectedItem());
            tblBooks.refresh();

            boolean result = false;


            try {
                result = manageBooksBO.deleteBooks(selectedItem);
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

    @FXML
    private void btnRelateAuthor_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/lms/view/ManageAuthor_BookView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)txtBookID.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Return Books");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void btnRelatePublisher_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/lms/view/ManagePublisher_BookView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)txtBookID.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Return Books");
        stage.setResizable(false);
        stage.show();
    }

    public void reset(){
        txtBookID.clear();
        txtBookName.clear();
        txtBookCategory.clear();
        tblBooks.getSelectionModel().clearSelection();
        txtBookID.requestFocus();
    }

    @FXML
    private void btnNew_OnAction(ActionEvent actionEvent) {
        reset();
        txtBookID.setEditable(true);
        btnSave.setText("Save");
    }

    public static BooksDTO[] getbookdetails(){
        return booksDTOS;

    }
}
