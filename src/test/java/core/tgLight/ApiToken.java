package it.tdlight.example;

import it.tdlight.client.APIToken;
import java.util.Objects;
import java.util.StringJoiner;

public class ApiToken {private int apiID;
    private String apiHash;

    public ApiToken(int apiID, String apiHash) {
        this.apiID = apiID;
        this.apiHash = apiHash;
    }

    public static ApiToken BO() {
        int apiID = 33321;
        String apiHash = "e63dc7dca3a831b1bdf9f9a69651afad";
        return new ApiToken(apiID, apiHash);
    }

    public int getApiID() {
        return this.apiID;
    }

    public void setApiID(int apiID) {
        this.apiID = apiID;
    }

    public String getApiHash() {
        return this.apiHash;
    }

    public void setApiHash(String apiHash) {
        this.apiHash = apiHash;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ApiToken apiData = (ApiToken)o;
            return this.apiID == apiData.apiID && Objects.equals(this.apiHash, apiData.apiHash);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.apiID, this.apiHash});
    }

    public String toString() {
        return (new StringJoiner(", ", ApiToken.class.getSimpleName() + "[", "]")).add("apiID=" + this.apiID).add("apiHash='" + this.apiHash + "'").toString();
    }
}
