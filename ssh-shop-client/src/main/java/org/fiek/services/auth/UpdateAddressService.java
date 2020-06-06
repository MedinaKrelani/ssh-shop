package org.fiek.services.auth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Address;
import org.fiek.models.User;
import org.fiek.store.auth.AddAddressAction;
import org.fiek.store.auth.EditAddressAction;
import org.fiek.utils.Ajax;
import org.fiek.utils.JsonHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class UpdateAddressService extends Service<Void> implements View {
    private User user;
    private Address addressObj;
    public UpdateAddressService(User user, Address addressObj) {
        this.user = user;
        this.addressObj = addressObj;
    }

    private void updateAddress() throws Exception {
        final String json1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.addressObj, Address.class);
        Ajax request = new Ajax();
        JsonObject response = request.patch("users/" + user.getId() + "/address/" + addressObj.getId(), json1);
        System.out.println("Response:" + response);
        String jsonAddress = response.get("address").toString();
        System.out.println("Response in String:" + jsonAddress);
        String jsonAddr = jsonAddress.replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonAddr1 = jsonAddr.replaceAll("},", "}},");
        String[] addr = jsonAddr1.split("},");
        String address = addr[0];
        publishAction(new EditAddressAction(address));
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateAddress();
                return null;
            }
        };
    }
}
