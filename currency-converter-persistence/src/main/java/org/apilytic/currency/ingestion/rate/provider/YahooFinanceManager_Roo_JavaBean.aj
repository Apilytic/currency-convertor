// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.apilytic.currency.ingestion.rate.provider;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.apilytic.currency.csv.CsvReaderAdapter;
import org.apilytic.currency.ingestion.rate.provider.YahooCSVBean;
import org.apilytic.currency.ingestion.rate.provider.YahooFinanceManager;
import org.springframework.web.client.RestTemplate;

privileged aspect YahooFinanceManager_Roo_JavaBean {
    
    public void YahooFinanceManager.setExchangeQuery(String exchangeQuery) {
        this.exchangeQuery = exchangeQuery;
    }
    
    public void YahooFinanceManager.setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public void YahooFinanceManager.setCsvStringReader(CsvReaderAdapter csvStringReader) {
        this.csvStringReader = csvStringReader;
    }
    
    public void YahooFinanceManager.setYahooCSVMappingStrategy(ColumnPositionMappingStrategy<YahooCSVBean> yahooCSVMappingStrategy) {
        this.yahooCSVMappingStrategy = yahooCSVMappingStrategy;
    }
    
    public void YahooFinanceManager.setCsvToBean(CsvToBean<YahooCSVBean> csvToBean) {
        this.csvToBean = csvToBean;
    }
    
}
