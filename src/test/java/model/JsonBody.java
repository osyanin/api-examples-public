package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import core.TestBase;
import core.User;

public class JsonBody {

    public String userCredentialsBodyJackson(User user)  {
        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("username", user.getLogin());
        obj.put("password", user.getPassword());
        obj.put("pinCode", user.getPin());

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }

    public String registrationInitBodyJackson(String username, String password, String pinCode, String inviteToken)  {
        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("username", username);
        obj.put("password", password);
        obj.put("paymentPinCode", pinCode);
        obj.put("inviteToken", inviteToken);

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }
    public String registrationFinishBodyJackson(
            String firstName,
            String codewordGreen,
            int securityQuestionIDOne,
            String securityQuestionAnswerOne,
            String tgUsernameMain,
            String emailMain)  {
        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("firstName", firstName);
        obj.put("codewordGreen", codewordGreen);
        obj.put("securityQuestionIDOne", securityQuestionIDOne);
        obj.put("securityQuestionAnswerOne", securityQuestionAnswerOne);
        obj.put("tgUsernameMain", tgUsernameMain);
        obj.put("emailMain", emailMain);

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }

    public String getInviteTokenBodyJackson(int adminID, int tariffID, String comment)  {
        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("adminID", adminID);
        obj.put("tariffID", tariffID);
        obj.put("comment", comment);

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }

    public String userIdBodyJackson(int userId)  {
        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("userID", userId);

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }

    public String changePasswordBodyJackson(String currentPassword, String newPassword)  {
        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("currentPassword", currentPassword);
        obj.put("newPassword", newPassword);
        ObjectNode objInObj = objectMapper.createObjectNode();
        objInObj.put("securityCode", TestBase.siteConnection.getTwoFactorPassword());
        obj.set("auth", objInObj);

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }

    public String changePinBodyJackson(String password, String currentPaymentPin, String newPaymentPin, String otpCode)  {
        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("currentPaymentPin", currentPaymentPin);
        obj.put("newPaymentPin", newPaymentPin);
        obj.put("otpCode", otpCode);
        ObjectNode objInObj = objectMapper.createObjectNode();
        objInObj.put("securityCode", TestBase.siteConnection.getTwoFactorPassword());
        obj.set("auth", objInObj);

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }

    public String changeTgUsernameBodyJackson(String tgUsername, boolean isMain)  {
        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("tgUsername", tgUsername);
        obj.put("isMain", isMain);

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }

    /*public String cashNegativeTypeBodyJackson(
            User user,
            Object amount,
            Object vaultID,
            Object directionID,
            Object walletID,
            Object meetTimeFrom,
            Object meetTimeTo,
            Object comment,
            Object banknotes,
            Object otpCode,
            Object pinCode,
            Object isCourier
            )  {
       *//* {
                "amount": -1,
                "vaultID": 1,
                "directionID": 2,
                "walletID": 2879,
                "meetTimeFrom": 1674288000,
                "meetTimeTo": 1674288900,
                "comment": "",
                "banknotes": null,
                "auth": {
                    "otpCode": "zxclol",
                    "pinCode": "16011"
                },
                 "isCourier": false
        }*//*

        String createdPlainJsonObject;
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("amount", amount);
        obj.put("vaultID", 1);
        obj.put("directionID", 2);
        obj.put("walletID", 2879);
        obj.put("meetTimeFrom", String.valueOf(meetTimeFrom));
        obj.put("meetTimeTo", 2);
        obj.put("comment", 2);
        obj.put("banknotes", 2);
        ObjectNode auth = objectMapper.createObjectNode();
        auth.put("otpCode", 2);
        auth.put("pinCode", 2);
        obj.put("isCourier", 2);

        try {
            createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return createdPlainJsonObject;
    }*/
}
