package model.cash;

import lombok.Data;

@Data
public class GetAvailableDateSlots {
    private String date;
    private Integer vaultID;

    public GetAvailableDateSlots(String date, Integer vaultID) {
        this.date = date;
        this.vaultID = vaultID;
    }
}
