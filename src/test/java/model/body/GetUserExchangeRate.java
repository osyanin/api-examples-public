package model.body;

import lombok.Data;

@Data
public class GetUserExchangeRate {
    private Integer currency_from_id;
    private Integer currency_to_id;
    private Integer amount;

    public GetUserExchangeRate(Integer currency_from_id, Integer currency_to_id, Integer amount) {
        this.currency_from_id = currency_from_id;
        this.currency_to_id = currency_to_id;
        this.amount = amount;
    }
}
