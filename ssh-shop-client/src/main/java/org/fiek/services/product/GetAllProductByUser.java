package org.fiek.services.product;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;

import javafx.concurrent.Service;

import javafx.concurrent.Task;

import org.fiek.models.User;

import org.fiek.store.chat.AddChannelsAction;

import org.fiek.store.product.FetchProductUserAction;

import org.fiek.store.product.FetchProductsAction;

import org.fiek.utils.Ajax;




public class GetAllProductByUser extends Service<Void> implements View {

    User user;


    public GetAllProductByUser(User user) {
        this.user = user;
    }

    @Override

    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                try {
                    Ajax request = new Ajax();
                    JsonObject response = request.get("/products/?user_id=" + user.getId());
                    String rows = response.get("products").toString();


                    publishAction(new FetchProductUserAction(rows));

                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }
                return null;

            }

        };

    }


}
