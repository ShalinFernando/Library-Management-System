package lk.ijse.dep.lms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import lk.ijse.dep.lms.business.custom.ManagePublisherBO;
import lk.ijse.dep.lms.business.custom.ManagePublisher_BookBO;
import lk.ijse.dep.lms.dto.*;
import lk.ijse.dep.lms.main.AppInitializer;
import lk.ijse.dep.lms.tablemodel.MemberTM;
import lk.ijse.dep.lms.tablemodel.Publisher_BookTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagePublisher_BookController {
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
    private JFXTextField txtPublisherID;
    @FXML
    private JFXTextField txtPublisherName;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<Publisher_BookTM> tblPublisher_Book;

    private ManagePublisher_BookBO publisher_bookBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_PUBLISHER_BOOK);
    private ManageBooksBO booksBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_BOOKS);
    private ManagePublisherBO publisherBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_PUBLISHER);


    private static Publisher_BookDTO[] publisher_bookDTO = new Publisher_BookDTO[5];

    public void initialize(){
        tblPublisher_Book.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("bookid"));
        tblPublisher_Book.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("publisherid"));
        btnDelete.setDisable(true);

        List<Publisher_BookDTO> publisher_bookDB = null;

        try {
            publisher_bookDB = publisher_bookBO.getPublisher_Book();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<Publisher_BookDTO> publisher_bookDTOS = FXCollections.observableArrayList(publisher_bookDB);
        ObservableList<Publisher_BookTM> publisher_bookTMS = FXCollections.observableArrayList();

        for (Publisher_BookDTO publisher_bookDTO : publisher_bookDTOS) {
            publisher_bookTMS.add(new Publisher_BookTM(publisher_bookDTO.getPublisher_bookDTOPK().getPublisherid(),publisher_bookDTO.getPublisher_bookDTOPK().getBookid()));


        }
        tblPublisher_Book.setItems(publisher_bookTMS);
        tblPublisher_Book.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Publisher_BookTM>() {
            @Override
            public void changed(ObservableValue<? extends Publisher_BookTM> observable, Publisher_BookTM oldValue, Publisher_BookTM newValue) {
                if (newValue == null){
                    return;
                }

                txtPublisherID.setText(newValue.getPublisherid());
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
        getbookdetails(bookid, booksBO, txtBookName);

    }

    static void getbookdetails(String bookid, ManageBooksBO booksBO, JFXTextField txtBookName) {
        try {
            BooksDTO book = booksBO.findBooks(bookid);
            txtBookName.setText(book.getBookname());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
            new Alert(Alert.AlertType.ERROR,"Invalid Book ID", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void txtPublisherID_OnAction(ActionEvent actionEvent) {
        ManageBorrowController.getpublishername(publisherBO, txtPublisherID, txtPublisherName);

    }

    @FXML
    private void btnSave_OnAction(ActionEvent actionEvent) {
        if (txtBookID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Book ID is empty", ButtonType.OK).showAndWait();
            txtBookID.requestFocus();
            return;
        } else if (txtPublisherID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Publisher ID is empty", ButtonType.OK).showAndWait();
            txtPublisherName.requestFocus();
            return;
        }

        if (tblPublisher_Book.getSelectionModel().isEmpty()) {
            // New Author_Books
            ObservableList<Publisher_BookTM> publisher_bookTMS = tblPublisher_Book.getItems();
            for (Publisher_BookTM publisher_bookTM : publisher_bookTMS) {
                if (publisher_bookTM.getBookid().equals(txtBookID.getText()) && publisher_bookTM.getPublisherid().equals(txtPublisherID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate IDs are not allowed").showAndWait();
                    txtBookID.requestFocus();
                    return;
                }
            }

            Publisher_BookTM publisher_bookTM = new Publisher_BookTM(txtBookID.getText(),txtPublisherID.getText());
            tblPublisher_Book.getItems().add(publisher_bookTM);
            Publisher_BookDTO publisher_book= new Publisher_BookDTO(txtBookID.getText(),txtPublisherID.getText());
            boolean result = false;
            try {
                result = publisher_bookBO.createPublisher_Book(publisher_book);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Publisher details to Book has been saved successfully", ButtonType.OK).showAndWait();
                tblPublisher_Book.scrollTo(publisher_bookTM);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Add", ButtonType.OK).showAndWait();
            }


            publisher_bookDTO[0] = publisher_book;


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
            String selectedBook = tblPublisher_Book.getSelectionModel().getSelectedItem().getBookid();
            String selectedPublisher = tblPublisher_Book.getSelectionModel().getSelectedItem().getPublisherid();
            tblPublisher_Book.getItems().remove(tblPublisher_Book.getSelectionModel().getSelectedItem());
            tblPublisher_Book.refresh();

            boolean result = false;


            try {
                result = publisher_bookBO.deletePublisher_Book(new Publisher_BookDTOPK(selectedPublisher,selectedBook));
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
    private void btnNew_OnAction(ActionEvent actionEvent) {
        reset();
    }

    public void reset(){
        txtBookID.clear();
        txtBookName.clear();
        txtPublisherID.clear();
        txtPublisherName.clear();
        tblPublisher_Book.getSelectionModel().clearSelection();
        txtBookID.requestFocus();
    }

    public static Publisher_BookDTO[] getpubisher_Book(){
        return publisher_bookDTO;
    }

}
