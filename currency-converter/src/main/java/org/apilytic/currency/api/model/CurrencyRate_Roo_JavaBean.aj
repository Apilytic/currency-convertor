// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.apilytic.currency.api.model;

import org.apilytic.currency.api.model.CurrencyRate;

privileged aspect CurrencyRate_Roo_JavaBean {
    
    public String CurrencyRate.getFromCurrency() {
        return this.fromCurrency;
    }
    
    public void CurrencyRate.setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
    
    public String CurrencyRate.getToCurrency() {
        return this.toCurrency;
    }
    
    public void CurrencyRate.setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
    
    public String CurrencyRate.getAmount() {
        return this.amount;
    }
    
    public void CurrencyRate.setAmount(String amount) {
        this.amount = amount;
    }
    
    public String CurrencyRate.getTitle() {
        return this.title;
    }
    
    public void CurrencyRate.setTitle(String title) {
        this.title = title;
    }
    
}
