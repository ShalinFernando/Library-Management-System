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
import lk.ijse.dep.lms.business.custom.ManagePublisherBO;
import lk.ijse.dep.lms.dto.PublisherDTO;
import lk.ijse.dep.lms.main.AppInitializer;
import lk.ijse.dep.lms.tablemodel.PublisherTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagePublisherController {
    @FXML
    private JFXButton btnNew;
    @FXML
    private ImageView imgHome;
    @FXML
    private JFXTextField txtPublisherID;
    @FXML
    private JFXTextField txtPublisherName;
    @FXML
    private JFXTextField txtPublisherEmail;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<PublisherTM> tblPublisher;
    @FXML
    private AnchorPane root;

    private ManagePublisherBO managePublisherBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_PUBLISHER);


    public void initialize(){
        tblPublisher.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("publisherid"));
        tblPublisher.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("publishername"));
        tblPublisher.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("publisheremail"));
        btnDelete.setDisable(true);

        List<PublisherDTO> publisherDB = null;
        try {
            publisherDB = managePublisherBO.getPublisher();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<PublisherDTO> publisherDTOS = FXCollections.observableArrayList(publisherDB);
        ObservableList<PublisherTM> publisherTMS = FXCollections.observableArrayList();
        for (PublisherDTO publisherDTO : publisherDTOS) {
            publisherTMS.add(new PublisherTM(publisherDTO.getPublisherid(),publisherDTO.getPublishername(),publisherDTO.getPublisheremail()));
        }
        tblPublisher.setItems(publisherTMS);

        tblPublisher.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PublisherTM>() {
            @Override
            public void changed(ObservableValue<? extends PublisherTM> observable, PublisherTM oldValue, PublisherTM newValue) {
                if (newValue == null){
                    return;
                }

                txtPublisherID.setText(newValue.getPublisherid());
                txtPublisherName.setText(newValue.getPublishername() );
                txtPublisherEmail.setText(newValue.getPublisheremail());

                btnSave.setText("Update");
                txtPublisherID.setEditable(false);
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
        if (txtPublisherID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Publisher ID is empty", ButtonType.OK).showAndWait();
            txtPublisherID.requestFocus();
            return;
        } else if (txtPublisherName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Publisher Name is empty", ButtonType.OK).showAndWait();
            txtPublisherName.requestFocus();
            return;
        } else if (txtPublisherEmail.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Email is empty", ButtonType.OK).showAndWait();
            txtPublisherEmail.requestFocus();
            return;
        }

        if (tblPublisher.getSelectionModel().isEmpty()) {
            // New publisher
            ObservableList<PublisherTM> publisher = tblPublisher.getItems();
            for (PublisherTM publisherTM : publisher) {
                if (publisherTM.getPublisherid().equals(txtPublisherID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Publisher IDs are not allowed").showAndWait();
                    txtPublisherID.requestFocus();
                    return;
                }
            }

            PublisherTM memberTM = new PublisherTM(txtPublisherID.getText(),txtPublisherName.getText(),txtPublisherEmail.getText());
            tblPublisher.getItems().add(memberTM);
            PublisherDTO memberDTO = new PublisherDTO(txtPublisherID.getText(),txtPublisherName.getText(),txtPublisherEmail.getText());
            boolean result = false;

            try {
                result = managePublisherBO.createPublisher(memberDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Publisher has been saved successfully", ButtonType.OK).showAndWait();
                tblPublisher.scrollTo(memberTM);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Add", ButtonType.OK).showAndWait();
            }


        } else {
            //Update Book

            PublisherTM selectedPublisher = tblPublisher.getSelectionModel().getSelectedItem();
            selectedPublisher.setPublishername(txtPublisherName.getText());
            selectedPublisher.setPublisheremail(txtPublisherEmail.getText());
            tblPublisher.refresh();

            boolean result = false;

            try {
                result = managePublisherBO.updatePublisher(new PublisherDTO(txtPublisherID.getText(),txtPublisherName.getText(),txtPublisherEmail.getText()));
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
            String selectedItem = tblPublisher.getSelectionModel().getSelectedItem().getPublisherid();
            tblPublisher.getItems().remove(tblPublisher.getSelectionModel().getSelectedItem());
            tblPublisher.refresh();

            boolean result = false;


            try {
                result = managePublisherBO.deletePublisher(selectedItem);
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
        btnSave.setText("Save");
        txtPublisherID.requestFocus();
        txtPublisherID.setEditable(true);
    }

    public void reset(){
        txtPublisherID.clear();
        txtPublisherName.clear();
        txtPublisherEmail.clear();
        tblPublisher.getSelectionModel().clearSelection();

    }
}
