package com.elasticsearh.autocomplete.APIelasticsearch;

import com.elasticsearh.autocomplete.model.AutocompleteResponse;

import java.io.IOException;

public interface AutocompleteService {
    AutocompleteResponse search(String term) throws IOException;
}
