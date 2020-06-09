/**
 * Sample Skeleton for 'list-Item-Address.fxml' Controller Class
 */

package org.fiek.controllers.Cart;

import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import eu.lestard.fluxfx.View;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.fiek.models.Cart;

import org.fiek.App;

import org.fiek.services.Cart.DeleteCartService;

import org.fiek.store.*;

import org.fiek.models.*;

import org.fiek.store.auth.*;

import org.fiek.store.auth.AuthStore;

import org.fiek.store.cart.CartStore;

import org.fiek.store.cart.DeleteCartAction;

import org.fiek.store.cart.SetActiveCartAction;

import org.fiek.store.chat.SetActiveChannelAction;


public class listItemCartController implements View {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);

    AuthStore authStore = baseStore.getAuthStore();

    CartStore cartStore = baseStore.getCartStore();

    User user = authStore.getUser();

    Cart cart;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="exitButton"
    private JFXButton exitButton; // Value injected by FXMLLoader

    @FXML // fx:id="priceLb"
    private Label priceLb; // Value injected by FXMLLoader


    @FXML // fx:id="prodLb"
    private Label prodLb; // Value injected by FXMLLoader

    @FXML // fx:id="quantityLb"
    private Label quantityLb; // Value injected by FXMLLoader

    @FXML // fx:id="hboxItem"
    private HBox hboxItem; // Value injected by FXMLLoader

    @FXML
    private ImageView imageSelector;


    public listItemCartController(Cart cart) {
        this.cart = cart;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        assert prodLb != null : "fx:id=\"prodLb\" was not injected: check your FXML file 'cart.fxml'.";
        assert imageSelector != null : "fx:id=\"imageSelector\" was not injected: check your FXML file 'cart.fxml'.";
        assert quantityLb != null : "fx:id=\"quantityLb\" was not injected: check your FXML file 'cart.fxml'.";
        assert priceLb != null : "fx:id=\"priceLb\" was not injected: check your FXML file 'cart.fxml'.";
        assert exitButton != null : "fx:id=\"exitbutton\" was not injected: check your FXML file 'cart.fxml'.";

        System.out.println("List item on action!");



        if (cart != null) {
            prodLb.setText("Produkti:" + cart.getVariantId());
            quantityLb.setText("Quantity:" + cart.getQuantity());


        }

    }

    @FXML
    public void clickHandler(MouseEvent action) {
        publishAction(new SetActiveCartAction(cart));

    }
}
