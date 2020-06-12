package org.fiek.services.product;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.controllers.profile.ListCardController;
import org.fiek.models.*;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.store.auth.GetCountryByNameAction;
import org.fiek.utils.Ajax;
import org.reactfx.value.Var;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class GetProductDetailService extends Service<Void> implements View {
    private  String product_id;
    public static Product productStatic;

    public GetProductDetailService(String id) {
        this.product_id = id;
    }


    private void getProductDetails() throws Exception {
        if (productStatic == null) {
            Ajax request = new Ajax();
            System.out.println("Brenda Servisit:" + product_id);
            JsonObject response = request.getAsJson("products/"+ product_id);
            String jsonAddress = response.get("user").toString();
            final Product actionAddress = new GsonBuilder().create().fromJson(jsonAddress, Product.class);
            productStatic = actionAddress;

        }

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getProductDetails();
                return null;
            }
        };
    }
}
