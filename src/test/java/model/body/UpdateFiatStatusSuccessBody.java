package model.body;

import lombok.Data;

@Data
public class UpdateFiatStatusSuccessBody {
    private String orderUID;

    public UpdateFiatStatusSuccessBody(String orderUID) {
        this.orderUID = orderUID;
    }
}
