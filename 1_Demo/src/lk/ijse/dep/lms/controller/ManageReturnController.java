package lk.ijse.dep.lms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.lms.business.BOFactory;
import lk.ijse.dep.lms.business.custom.ManageBorrowBO;
import lk.ijse.dep.lms.business.custom.ManageReturnsBO;
import lk.ijse.dep.lms.dto.BooksDTO;
import lk.ijse.dep.lms.dto.BorrowDTO;
import lk.ijse.dep.lms.dto.ReturnsDTO;
import lk.ijse.dep.lms.main.AppInitializer;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageReturnController {
    @FXML
    private JFXTextField txtBorrowID;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgHome;
    @FXML
    private Label lblDate;
    @FXML
    private JFXTextField txtMemberID;
    @FXML
    private JFXTextField txtBookID;
    @FXML
    private JFXButton btnPenalty;
    @FXML
    private JFXDatePicker dprBorrowDate;
    @FXML
    private Label lblPenalty;
    @FXML
    private JFXButton btnReturn;

    private ManageBorrowBO manageBorrowBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_BORROW);
    private ManageReturnsBO manageReturnsBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_RETURNS);
    long dif;

    public void initialize(){
        lblDate.setText(LocalDate.now()+"");
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
    private void btnPenalty_OnAction(ActionEvent actionEvent) {
        LocalDate today = LocalDate.now();
        LocalDate duedate = dprBorrowDate.getValue();

        dif = Math.abs(today.getDayOfMonth() - duedate.getDayOfMonth());
        lblPenalty.setText("Penalty due is : " + dif*20 + " As for the cost Rs. 20 into " + dif + " Days");

    }

    @FXML
    private void btnReturn_OnAction(ActionEvent actionEvent) {

        ReturnsDTO returnsDTO = new ReturnsDTO(txtBorrowID.getText(),txtMemberID.getText(),txtBookID.getText(),dprBorrowDate.getValue(),LocalDate.now(), (int) (dif*20));


        boolean result = false;
        try {
            result = manageReturnsBO.createReturns(returnsDTO);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }
        if (result) {
            new Alert(Alert.AlertType.INFORMATION, "Return has been saved successfully", ButtonType.OK).showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Add", ButtonType.OK).showAndWait();
        }
        reset();
    }

    private void reset() {
        txtBorrowID.clear();
        txtMemberID.clear();
        txtBookID.clear();

    }

    @FXML
    private void txtBorrowID_OnAction(ActionEvent actionEvent) {
        try {
            BorrowDTO borrow = manageBorrowBO.findBorrow(txtBorrowID.getText());
            txtBookID.setText(borrow.getBookid());
            txtMemberID.setText(borrow.getMemberid());
            dprBorrowDate.setValue(borrow.getReturndate());

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }
}
