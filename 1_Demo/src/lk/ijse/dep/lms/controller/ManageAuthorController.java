package lk.ijse.dep.lms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.lms.business.BOFactory;
import lk.ijse.dep.lms.business.custom.ManageAuthorBO;
import lk.ijse.dep.lms.dto.AuthorDTO;
import lk.ijse.dep.lms.main.AppInitializer;
import lk.ijse.dep.lms.tablemodel.AuthorTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageAuthorController {
    public JFXButton btnNew;
    @FXML
    private ImageView imgHome;
    @FXML
    private JFXTextField txtAuthorID;
    @FXML
    private JFXTextField txtAuthorName;
    @FXML
    private JFXTextField txtAuthorEmail;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<AuthorTM> tblAuthor;
    @FXML
    private AnchorPane root;

    private ManageAuthorBO manageAuthorBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_AUTHOR);

    public void initialize(){
        tblAuthor.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("authorid"));
        tblAuthor.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("authorname"));
        tblAuthor.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("authoremail"));
        btnDelete.setDisable(true);

        List<AuthorDTO> authorDB = null;
        try {
            authorDB = manageAuthorBO.getAuthor();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        ObservableList<AuthorDTO> authorDTOS = FXCollections.observableArrayList(authorDB);
        ObservableList<AuthorTM> authorTMS = FXCollections.observableArrayList();
        for (AuthorDTO authorDTO : authorDTOS) {
            authorTMS.add(new AuthorTM(authorDTO.getAuthorid(),authorDTO.getAuthorname(),authorDTO.getAuthoremail()));
        }
        tblAuthor.setItems(authorTMS);

        tblAuthor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AuthorTM>() {
            @Override
            public void changed(ObservableValue<? extends AuthorTM> observable, AuthorTM oldValue, AuthorTM newValue) {

                if (newValue == null){
                    return;
                }

                txtAuthorID.setText(newValue.getAuthorid());
                txtAuthorName.setText(newValue.getAuthorname());
                txtAuthorEmail.setText(newValue.getAuthoremail());
                txtAuthorID.setEditable(false);
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
        PlayEnterAnimationMethod(event);
    }

    static void PlayEnterAnimationMethod(MouseEvent event) {
        ImageView icon = (ImageView) event.getSource();
        ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
        scaleT.setToX(1.2);
        scaleT.setToY(1.2);
        scaleT.play();

        DropShadow glow = new DropShadow();
        glow.setColor(Color.CORNFLOWERBLUE);
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setRadius(20);
        icon.setEffect(glow);
    }

    @FXML
    private void PlayExitAnimation(MouseEvent event) {
        PlayExitAnimationMethod(event);
    }

    static void PlayExitAnimationMethod(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    @FXML
    private void btnSave_OnAction(ActionEvent actionEvent) {
        if (txtAuthorID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Author ID is empty", ButtonType.OK).showAndWait();
            txtAuthorID.requestFocus();
            return;
        } else if (txtAuthorName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Author Name is empty", ButtonType.OK).showAndWait();
            txtAuthorName.requestFocus();
            return;
        } else if (txtAuthorEmail.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Author Email is empty", ButtonType.OK).showAndWait();
            txtAuthorEmail.requestFocus();
            return;
        }

        if (tblAuthor.getSelectionModel().isEmpty()){
            // New Author
            ObservableList<AuthorTM> authors = tblAuthor.getItems();
            for (AuthorTM authorTM : authors) {
                if (authorTM.getAuthorid().equals(txtAuthorID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Author IDs are not allowed").showAndWait();
                    txtAuthorID.requestFocus();
                    return;
                }
            }

            AuthorTM authorTM = new AuthorTM(txtAuthorID.getText(),txtAuthorName.getText(),txtAuthorEmail.getText());
            tblAuthor.getItems().add(authorTM);
            AuthorDTO authorDTO = new AuthorDTO(txtAuthorID.getText(),txtAuthorName.getText(),txtAuthorEmail.getText());
            boolean result = false;

            try {
                result = manageAuthorBO.createAuthor(authorDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (result){
                new Alert(Alert.AlertType.INFORMATION, "Author has been saved successfully", ButtonType.OK).showAndWait();
                tblAuthor.scrollTo(authorTM);
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to Add", ButtonType.OK).showAndWait();
            }



        }else{
            //Update Author


            AuthorTM selectedAuthor = tblAuthor.getSelectionModel().getSelectedItem();
            selectedAuthor.setAuthorname(txtAuthorName.getText());
            selectedAuthor.setAuthoremail(txtAuthorEmail.getText());
            tblAuthor.refresh();

            boolean result = false;

            try {
                result = manageAuthorBO.updateAuthor(new AuthorDTO(txtAuthorID.getText(),txtAuthorName.getText(),txtAuthorEmail.getText()));
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
            btnSave.setText("Save");
            if (result){
                new Alert(Alert.AlertType.INFORMATION,"Record Updated Successfully", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Record Update Failed", ButtonType.OK).show();
            }


        }
        reset();
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent actionEvent) {

        Alert cnfrm = new Alert(Alert.AlertType.WARNING, "Confirm to Delete Record",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = cnfrm.showAndWait();
        if (buttonType.get()==ButtonType.YES) {
            String selectedItem = tblAuthor.getSelectionModel().getSelectedItem().getAuthorid();
            tblAuthor.getItems().remove(tblAuthor.getSelectionModel().getSelectedItem());
            tblAuthor.refresh();

            boolean result = false;


            try {
                result = manageAuthorBO.deleteAuthor(selectedItem);
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
        txtAuthorEmail.clear();
        txtAuthorID.requestFocus();
        tblAuthor.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnNew_OnAction(ActionEvent actionEvent) {
        reset();
        txtAuthorID.setEditable(true);
        txtAuthorID.requestFocus();
    }
}
