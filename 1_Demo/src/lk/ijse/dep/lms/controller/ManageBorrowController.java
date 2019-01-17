package lk.ijse.dep.lms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.lms.business.BOFactory;
import lk.ijse.dep.lms.business.custom.*;
import lk.ijse.dep.lms.dbconnection.DBConnection;
import lk.ijse.dep.lms.dto.*;
import lk.ijse.dep.lms.main.AppInitializer;
import lk.ijse.dep.lms.tablemodel.BorrowTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageBorrowController {
    @FXML
    private JFXButton btnReport;
    @FXML
    private Label lblBorrowID;
    @FXML
    private JFXButton btnNew;
    @FXML
    private TableView<BorrowTM> tblBorrow;
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
    private JFXButton btnSave;
    @FXML
    private JFXTextField txtAuthorName;
    @FXML
    private JFXTextField txtPublisherID;
    @FXML
    private JFXTextField txtPublisherName;
    @FXML
    private JFXDatePicker dprBorrowDate;
    @FXML
    private JFXDatePicker dprReturnDate;
    @FXML
    private JFXTextField txtMemberID;
    @FXML
    private JFXTextField txtMemberName;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private ImageView imgBorrow;

    private ManageBooksBO booksBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_BOOKS);
    private ManagePublisherBO publisherBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_PUBLISHER);
    private ManageAuthorBO authorBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_AUTHOR);
    private ManageMemberBO manageMemberBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_MEMBER);
    private ManageBorrowBO manageBorrowBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_BORROW);

    int c= 0;

    public void initialize() throws Exception {
        tblBorrow.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("memberid"));
        tblBorrow.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bookid"));
        tblBorrow.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("authorid"));
        tblBorrow.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("publisherid"));
        tblBorrow.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("borrowdate"));
        tblBorrow.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("returndate"));

        lblBorrowID.setText(manageBorrowBO.generateOrderId());



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
    private void txtBookID_OnAction(ActionEvent actionEvent) {
        try {
            List<BooksDTO2> bookdetails = manageBorrowBO.getbookdetailswithauthorandpublisher(txtBookID.getText());
            for (BooksDTO2 bookdetail : bookdetails) {
                txtBookName.setText(bookdetail.getBookname());
                txtAuthorID.setText(bookdetail.getAuthorid());
                txtPublisherID.setText(bookdetail.getPublisherid());

            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
            new Alert(Alert.AlertType.ERROR,"Invalid Book ID", ButtonType.OK).showAndWait();
        }
    }


    @FXML
    private void txtAuthorID_OnAction(ActionEvent actionEvent) {
        try {
            AuthorDTO author = authorBO.findAuthor(txtAuthorID.getText());
            txtAuthorName.setText(author.getAuthorname());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
            new Alert(Alert.AlertType.ERROR,"Invalid Author ID", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void btnSave_OnAction(ActionEvent actionEvent) {
        String memberid = txtMemberID.getText();
        String membername = txtMemberName.getText();
        String bookid = txtBookID.getText();
        String bookname = txtBookName.getText();
        String publisherid = txtPublisherID.getText();
        String publishername = txtPublisherName.getText();
        String authorid = txtAuthorID.getText();
        String authorname = txtAuthorName.getText();
        LocalDate borrowdate = dprBorrowDate.getValue();
        LocalDate returndate = dprReturnDate.getValue();


        if (txtMemberID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Member ID is Empty", ButtonType.OK).show();
            txtMemberID.requestFocus();
            return;
        }
        if (txtBookID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Book ID is Empty", ButtonType.OK).show();
            txtBookID.requestFocus();
            return;
        }
        if (txtPublisherID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Publisher ID is Empty", ButtonType.OK).show();
            txtPublisherID.requestFocus();
            return;
        }
        if (txtAuthorID.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Author ID is Empty", ButtonType.OK).show();
            txtAuthorID.requestFocus();
            return;
        }




        if (c<2) {
            ObservableList<BorrowTM> borrowTMS = FXCollections.observableArrayList();
            BorrowTM borrowTM = new BorrowTM(memberid, bookid, authorid, publisherid, borrowdate, returndate);
            tblBorrow.getItems().add(borrowTM);
            c++;
        }else{
            new Alert(Alert.AlertType.ERROR,"Excess Borrows", ButtonType.OK).showAndWait();
            return;

        }
        reset();


        }


    @FXML
    private void txtPublisherID_OnAction(ActionEvent actionEvent) {
        getpublishername(publisherBO, txtPublisherID, txtPublisherName);
    }

    static void getpublishername(ManagePublisherBO publisherBO, JFXTextField txtPublisherID, JFXTextField txtPublisherName) {
        try {
            PublisherDTO publisher = publisherBO.findPublisher(txtPublisherID.getText());
            txtPublisherName.setText(publisher.getPublishername());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
            new Alert(Alert.AlertType.ERROR,"Invalid Publisher ID", ButtonType.OK).showAndWait();
        }
    }


    @FXML
    private void txtMemberID_OnAction(ActionEvent actionEvent) {
        try {
            MemberDTO member = manageMemberBO.findMember(txtMemberID.getText());
            txtMemberName.setText(member.getMembername());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
            new Alert(Alert.AlertType.ERROR,"Invalid Member ID", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent actionEvent) {
        tblBorrow.getItems().remove(tblBorrow.getSelectionModel().getSelectedItem());
        c--;
    }

    @FXML
    private void imgBorrow_OnMouseClicked(MouseEvent event) {
//        BorrowTM borrowTM = new BorrowTM(txtMemberID.getText(),txtBookID.getText(), txtAuthorID.getText(),txtPublisherID.getText(),dprBorrowDate.getValue(),dprReturnDate.getValue());
//        tblBorrow.getItems().add(borrowTM);
//        BorrowDTO borrowDTO = new BorrowDTO(lblBorrowID.getText(),txtBookID.getText(),txtMemberID.getText(),dprBorrowDate.getValue(),dprReturnDate.getValue());

//        booksDTOS[0]= booksDTO;



        ObservableList<BorrowTM> borrow = tblBorrow.getItems();
        ArrayList<BorrowDTO> borrowDTO = new ArrayList<>();

        for (BorrowTM borrowTM : borrow) {
            borrowDTO.add(new BorrowDTO(lblBorrowID.getText(),borrowTM.getBookid(),borrowTM.getMemberid(),borrowTM.getBorrowdate(),borrowTM.getReturndate()));
        }
        boolean result = false;
        try {
            result = manageBorrowBO.createBorrow(new BorrowDTO(lblBorrowID.getText(),txtBookID.getText(),txtMemberID.getText(),dprBorrowDate.getValue(),dprReturnDate.getValue()));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "Borrow has been saved successfully", ButtonType.OK).showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Add", ButtonType.OK).showAndWait();
        }


    }

    @FXML
    private void btnNew_OnAction(ActionEvent actionEvent) {
        reset();
        txtMemberID.requestFocus();
    }

    public void reset(){
        txtMemberID.clear();
        txtMemberName.clear();
        txtAuthorID.clear();
        txtAuthorName.clear();
        txtPublisherID.clear();
        txtPublisherName.clear();
        txtBookID.clear();
        txtBookName.clear();
        tblBorrow.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnReport_OnAction(ActionEvent actionEvent) throws JRException, IOException, SQLException {

        SimpleJasperReportsContext ctm = new SimpleJasperReportsContext();

        File file = new File("Jasper/jasperReport.jasper");

        JasperReport compileReport = (JasperReport) JRLoader.loadObject(ctm, file);

        JasperPrint fillreport = JasperFillManager.getInstance(ctm).fill(compileReport, new HashMap<>(), DBConnection.getConnection());

        JasperViewer.viewReport(fillreport,false);


    }
}
