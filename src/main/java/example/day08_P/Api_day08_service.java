package example.day08_P;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service 
public class Api_day08_service {
    @Value ("${api.public-data.service-key}")
    private  String serviceKey;

    WebClient webClient = WebClient.builder().build();

    public Map<String, Object> get(){
        String url = "https://api.odcloud.kr/api/15062631/v1/uddi:5134c40d-4de8-49ec-ad70-de75d6675ac7";
        url += "?page="+1;
        url += "&perPage="+10;
        url += "&serviceKey="+serviceKey;

        Map<String, Object> response = webClient.get()
                                        .uri(url)
                                        .retrieve()
                                        .bodyToMono( Map.class)
                                        .block();
        return  response;
    }
    
}
