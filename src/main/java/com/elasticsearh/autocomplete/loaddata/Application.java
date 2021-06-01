package com.elasticsearh.autocomplete.loaddata;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\morph\\Downloads\\MOCK_DATA.csv");

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("52.246.250.73",9200))
        );

        IndexRequest request = new IndexRequest("products");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] strings = line.split(",");

            String no = strings[0];
            String product = strings[1];
            String product_type = strings[2];


            JSONObject object = new JSONObject();
            object.put("product_no",no);
            object.put("product_name",product);
            object.put("product_type",product_type);

            System.out.println(object.toJSONString());

            request.source(object.toJSONString(), XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);

        }
    }
}
