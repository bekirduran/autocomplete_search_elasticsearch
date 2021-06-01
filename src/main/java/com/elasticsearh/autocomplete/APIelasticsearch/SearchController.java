package com.elasticsearh.autocomplete.APIelasticsearch;


import com.elasticsearh.autocomplete.model.AutocompleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SearchController {


    //OUR ENDPOINT
    @Autowired
    AutocompleteService autocompleteService;

    @GetMapping("/autocomplete")
    public AutocompleteResponse autocomplete(@RequestParam String term) throws IOException {
        AutocompleteResponse searchRequest = autocompleteService.search(term);
        return searchRequest;
    }
}
