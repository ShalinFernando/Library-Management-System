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
import lk.ijse.dep.lms.business.custom.ManageMemberBO;
import lk.ijse.dep.lms.dto.BooksDTO;
import lk.ijse.dep.lms.dto.MemberDTO;
import lk.ijse.dep.lms.main.AppInitializer;
import lk.ijse.dep.lms.tablemodel.BooksTM;
import lk.ijse.dep.lms.tablemodel.MemberTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageMemberController {
    @FXML
    private JFXButton btnNew;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgHome;
    @FXML
    private JFXTextField txtMemberID;
    @FXML
    private JFXTextField txtMemberName;
    @FXML
    private JFXTextField txtMemberEmail;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<MemberTM> tblMember;

    private ManageMemberBO manageMemberBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_MEMBER);

    public void initialize(){
        tblMember.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("memberid"));
        tblMember.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("membername"));
        tblMember.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        btnDelete.setDisable(true);


        List<MemberDTO> memberDB = null;

        try {
            memberDB = manageMemberBO.getMember();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<MemberDTO> memberDTOS = FXCollections.observableArrayList(memberDB);
        ObservableList<MemberTM> memberTMS = FXCollections.observableArrayList();

        for (MemberDTO memberDTO : memberDTOS) {
            memberTMS.add(new MemberTM(memberDTO.getMemberid(),memberDTO.getMembername(),memberDTO.getEmail()));

        }
        tblMember.setItems(memberTMS);
        tblMember.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MemberTM>() {
            @Override
            public void changed(ObservableValue<? extends MemberTM> observable, MemberTM oldValue, MemberTM newValue) {
                if (newValue == null){
                    return;
                }

                txtMemberID.setText(newValue.getMemberid());
                txtMemberName.setText(newValue.getMembername());
                txtMemberEmail.setText(newValue.getEmail());
                txtMemberID.setEditable(false);
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

        if (txtMemberID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Member ID is empty", ButtonType.OK).showAndWait();
            txtMemberID.requestFocus();
            return;
        } else if (txtMemberName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Member Name is empty", ButtonType.OK).showAndWait();
            txtMemberName.requestFocus();
            return;
        } else if (txtMemberEmail.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Email is empty", ButtonType.OK).showAndWait();
            txtMemberEmail.requestFocus();
            return;
        }

        if (tblMember.getSelectionModel().isEmpty()) {
            // New Member
            ObservableList<MemberTM> member = tblMember.getItems();
            for (MemberTM memberTM : member) {
                if (memberTM.getMemberid().equals(txtMemberID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Member IDs are not allowed").showAndWait();
                    txtMemberID.requestFocus();
                    return;
                }
            }

            MemberTM memberTM = new MemberTM(txtMemberID.getText(),txtMemberName.getText(),txtMemberEmail.getText());
            tblMember.getItems().add(memberTM);
            MemberDTO memberDTO = new MemberDTO(txtMemberID.getText(),txtMemberName.getText(),txtMemberEmail.getText());
            boolean result = false;

            try {
                result = manageMemberBO.createMember(memberDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Member has been saved successfully", ButtonType.OK).showAndWait();
                tblMember.scrollTo(memberTM);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Add", ButtonType.OK).showAndWait();
            }


        } else {
            //Update Book

            MemberTM selectedMember = tblMember.getSelectionModel().getSelectedItem();
            selectedMember.setMembername(txtMemberName.getText());
            selectedMember.setEmail(txtMemberEmail.getText());
            tblMember.refresh();

            boolean result = false;

            try {
                result = manageMemberBO.updateMember(new MemberDTO(txtMemberID.getText(),txtMemberName.getText(),txtMemberEmail.getText()));
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

        }
        reset();

    }

    @FXML
    private void btnDelete_OnAction(ActionEvent actionEvent) {
        Alert cnfrm = new Alert(Alert.AlertType.WARNING, "Confirm to Delete Record",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = cnfrm.showAndWait();
        if (buttonType.get()==ButtonType.YES) {
            String selectedItem = tblMember.getSelectionModel().getSelectedItem().getMemberid();
            tblMember.getItems().remove(tblMember.getSelectionModel().getSelectedItem());
            tblMember.refresh();

            boolean result = false;


            try {
                result = manageMemberBO.deleteMember(selectedItem);
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
        txtMemberID.clear();
        txtMemberName.clear();
        txtMemberEmail.clear();
        tblMember.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnNew_OnAction(ActionEvent actionEvent) {
        reset();
        btnSave.setText("Save");
        txtMemberID.setEditable(true);
        txtMemberID.requestFocus();
    }
}
