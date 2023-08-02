package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import model.body.Auth;
import model.body.CreateExchange;
import io.qameta.allure.Step;

public class JsonBodyExchange {
    Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();

    @Step
    public String createExchangeGson(String amount,
                                     String amountFrom,
                                     String rule,
                                     Integer walletFromID,
                                     Integer walletToID,
                                     String pinCode,
                                     String securityCode,
                                     String securityID) {
        Auth auth = new Auth(pinCode, securityCode, securityID);
        CreateExchange createExchange = new CreateExchange(amount, amountFrom, rule, walletFromID, walletToID, auth);
        return gson.toJson(createExchange);
    }

    @Step
    public String updateExchangeStatusGSON(Integer orderID, Integer statusID, String otpCode){
        JsonObject body = new JsonObject();
        body.addProperty("orderID", orderID);
        body.addProperty("statusID", statusID);
        JsonObject auth = new JsonObject();
        auth.addProperty("otpCode", otpCode);
        body.add("auth", auth);
        return gson.toJson(body);
    }
}
