package com.elasticsearh.autocomplete.model;

import java.util.List;

public class AutocompleteResponse {
    private List<AutocompleteData> data;

    public AutocompleteResponse(List<AutocompleteData> data) {
        this.data = data;
    }

    public AutocompleteResponse() {
    }

    public List<AutocompleteData> getData() {
        return data;
    }

    public void setData(List<AutocompleteData> data) {
        this.data = data;
    }
}
