package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.body.*;
import io.qameta.allure.Step;

public class JsonBodyFiat {
    Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();
    public JsonArray fiatListBodyGsonAssertion() {
        JsonArray array= new JsonArray();
        JsonObject eur = new JsonObject();
        eur.addProperty("ID", 7);
        eur.addProperty("name", "EUR");
        JsonObject rub = new JsonObject();
        rub.addProperty("ID", 5);
        rub.addProperty("name", "RUB");
        JsonObject usd = new JsonObject();
        usd.addProperty("ID", 6);
        usd.addProperty("name", "USD");
        JsonObject aed = new JsonObject();
        aed.addProperty("ID", 10);
        aed.addProperty("name", "AED");
        array.add(eur);
        array.add(rub);
        array.add(usd);
        array.add(aed);
        return array;
    }

    @Step("/order/fetch_admin_fiat_transfer")
    public String fetchAdminFiatTransferGson() {
        FetchAdminFiatTransferBody jsonObject = new FetchAdminFiatTransferBody(
                null,
                null,
                null,
                null,
                null,
                null,
                1,
                null,
                null);

        return gson.toJson(jsonObject);
    }

    @Step("/order/get_fiat_details_admin")
    public String getFiatDetailsGson(Integer integer) {
        GetFiatDetailsAdmin getFiatDetailsAdmin = new GetFiatDetailsAdmin(integer);
        return gson.toJson(getFiatDetailsAdmin);
    }

    @Step
    public String updateFiatStatusSuccessGson(String orderUID) {
        UpdateFiatStatusSuccessBody updateFiatStatusSuccessBody = new UpdateFiatStatusSuccessBody(orderUID);
        return gson.toJson(updateFiatStatusSuccessBody);
    }

    /*@Step
    public String createFiatWithdraw(String amount, Integer walletID, String recipient, String pinCode, String securityCode, String securityID) {
        Auth auth = new Auth(pinCode, securityCode, securityID);
        CreateFiatWithdrawBody createFiatWithdrawBody = new CreateFiatWithdrawBody(auth, amount, walletID, recipient);
        return gson.toJson(createFiatWithdrawBody);
    }*/
}
