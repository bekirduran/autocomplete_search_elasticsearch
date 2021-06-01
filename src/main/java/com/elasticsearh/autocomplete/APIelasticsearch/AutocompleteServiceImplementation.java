package com.elasticsearh.autocomplete.APIelasticsearch;

import com.elasticsearh.autocomplete.model.AutocompleteData;
import com.elasticsearh.autocomplete.model.AutocompleteResponse;
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import static com.elasticsearh.autocomplete.model.Constants.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutocompleteServiceImplementation implements AutocompleteService {

    RestHighLevelClient restHighLevelClient;
    SearchSourceBuilder builder;
    SearchRequest request;
    Gson gson;

    @PostConstruct
    public void init() {
        restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ES_HOSTNAME, ES_PORT))
        );
        builder = new SearchSourceBuilder();
        gson = new Gson();
    }

    @Override
    public AutocompleteResponse search(String term) throws IOException {
        // önemli kullanıcı "fuzziness(3)" ile 3 harf kadar yanılma payı ile arama yapabilir var.
     //   builder.query(QueryBuilders.matchQuery(ES_AUTOCOMPLETE_FIELD1,term).fuzziness(2));
        builder.query(QueryBuilders.multiMatchQuery(term,ES_AUTOCOMPLETE_FIELD1,ES_AUTOCOMPLETE_FIELD2).fuzziness(2));


        builder.from(0);
        builder.size(5);
        request = new SearchRequest(ES_INDEX).source(builder);
        List<AutocompleteData> data = new ArrayList<>();

        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits responseHits = response.getHits();
        SearchHit[] hits = responseHits.getHits();

        for (int i = 0;i<hits.length;i++){
            String sourceAsString = hits[i].getSourceAsString();
            AutocompleteData fromJson = gson.fromJson(sourceAsString, AutocompleteData.class);

            data.add(fromJson);

        }

        return new AutocompleteResponse(data);
    }
}
