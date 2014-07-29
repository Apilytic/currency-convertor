package org.apilytic.currency.api.model;

import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 * Request exchange query for currency.
 * <p>
 * Created by g on 7/5/14.
 */
@RooJavaBean
public class CurrencyRate {

    /**
     * Currency ISO code we want exchange from.
     */
    private String fromCurrency;

    /**
     * Currency ISO code we want exchange to.
     */
    private String toCurrency;

    /**
     * Desired amount that we want to be exchanged.
     */
    //TODO create annotation that uses Rate format to override setter
    private String amount;

    /**
     * Optional property - name of the exchange query query.
     * If persists values are retrieved by this key in the resulting model.
     */
    private String title;

    /**
     * Currency local output format.
     */
    private String locale;

}
