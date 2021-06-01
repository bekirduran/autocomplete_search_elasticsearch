package com.elasticsearh.autocomplete.model;

public class AutocompleteData {
    private String product_no;
    private String product_name;
    private String product_type;

    public AutocompleteData() {
    }

    public AutocompleteData(String productNo, String productName, String productType) {
        this.product_no = productNo;
        this.product_name = productName;
        this.product_type = productType;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProductType() {
        return product_type;
    }

    public void setProductType(String productType) {
        this.product_type = productType;
    }
}
