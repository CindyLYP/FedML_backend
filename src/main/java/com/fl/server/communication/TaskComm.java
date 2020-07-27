package com.fl.server.communication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;


public class TaskComm {
    public HashMap<String, Object> GenerateHMSO(){
        return new HashMap<String, Object>();
    }

    public ArrayList<Object> GenerateLO(){
        return new ArrayList<Object>();
    }

    public HashMap<String, Object> Default_train_config(){
        HashMap<String, Object> train_config = GenerateHMSO();
        train_config.put("max_iter", 12345);
        train_config.put("loss_func", "mse");
        train_config.put("metrics", "auc_ks");
        return train_config;
    }

    public HashMap<String, Object> Default_main_client(){
        HashMap<String, Object> main_client = GenerateHMSO();
        main_client.put("addr", "127.0.0.1");
        main_client.put("http_port", 8377);
        main_client.put("computation_port", 8378);
        return main_client;
    }

    public HashMap<String, Object> Default_crypto_producer(){
        HashMap<String, Object> crypto_producer = GenerateHMSO();
        crypto_producer.put("addr", "127.0.0.1");
        crypto_producer.put("http_port", 8390);
        crypto_producer.put("computation_port", 8391);
        return crypto_producer;
    }

    public HashMap<String, Object> Default_label_client(){
        HashMap<String, Object> label_client = GenerateHMSO();
        label_client.put("addr", "127.0.0.1");
        label_client.put("http_port", 8884);
        label_client.put("computation_port", 8885);
        label_client.put("data_file", "Splitted_Indexed_Data/credit_default_label.csv");
        label_client.put("dim", 1);
        return label_client;
    }


    public HashMap<String, Object> Test_feature_client(int port, String datafile, int dim){
        HashMap<String, Object> feature_client = GenerateHMSO();
        feature_client.put("addr", "127.0.0.1");
        feature_client.put("http_port", port);
        feature_client.put("computation_port", port+1);
        feature_client.put("data_file", datafile);
        feature_client.put("dim", dim);
        return feature_client;
    }

    private HashMap<String, Object> LoadData(){
        HashMap<String, Object> task_request = new HashMap<String, Object>();

        task_request.put("task_name", "test5");
        task_request.put("model_name", "shared_nn");

        // load config
        HashMap<String, Object> configs = GenerateHMSO();
        configs.put("train_config", Default_train_config());
        task_request.put("configs", configs);

        // load clients
        HashMap<String, Object> clients = GenerateHMSO();
        clients.put("main_client", Default_main_client());
        clients.put("crypto_producer", Default_crypto_producer());

        ArrayList<Object> feature_clients = GenerateLO();
        feature_clients.add(Test_feature_client(8084, "Splitted_Indexed_Data/credit_default_data1.csv", 30));
        feature_clients.add(Test_feature_client(8082, "Splitted_Indexed_Data/credit_default_data2.csv", 42));
        clients.put("feature_clients", feature_clients);

        clients.put("label_client", Default_label_client());
        task_request.put("clients", clients);
        return task_request;
    }

    public boolean TrainLaunch(){
        String urlTaskLaunch = "http://127.0.0.1:8380/createTask";
        System.out.println(urlTaskLaunch);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HashMap<String, Object> req = LoadData();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(req, headers);

            System.out.println(request.toString());
            ResponseEntity<String> response = restTemplate.postForEntity(urlTaskLaunch, request, String.class);
            System.out.println(response.getBody());

        }catch (HttpClientErrorException e){
            System.out.println("http post fail!");
            return false;
        }

        return true;
    }

    public boolean TrainStart(){
        String urlTaskLaunch = "http://127.0.0.1:8380/startTask";
        System.out.println(urlTaskLaunch);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HashMap<String, Object> req = new HashMap<String, Object>();
            req.put("task_name", "test5");

            //System.out.println(request.toString());
            ResponseEntity<String> response = restTemplate.getForEntity(urlTaskLaunch + "?task_name={task_name}", String.class, req);
            System.out.println(response.getBody());

        }catch (HttpClientErrorException e){
            System.out.println("http post fail!");
            return false;
        }

        return true;
    }

//    public boolean TestTrainLaunch(){
//        String urlTaskLaunch = "http://192.168.1.105:8888/loginReq";
//        //System.out.println(urlTaskLaunch);
//
//        HashMap<String, Object> req = new HashMap<String, Object>();
//        req.put("username", "123");
//        req.put("password", "123");
//        //System.out.println("req generated");
//
//        JSONObject jsonObj = new JSONObject(req);
//        //System.out.println(jsonObj.toString());
//        String content = jsonObj.toString();
//
//        try {
//
//            RestTemplate restTemplate = new RestTemplate();
//
//            //String jsonString = "{\"username\":\"123\",\"password\":\"123\"}";
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            //HttpEntity<String> strEntity = new HttpEntity<String>(jsonString, headers);
//            //System.out.println(strEntity.toString());
//
//            MultiValueMap<String, Object> map= new LinkedMultiValueMap<>();
//            map.add("username", "123");
//            map.add("password", "123");
//            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
//
//            ResponseEntity<String> response = restTemplate.postForEntity(urlTaskLaunch, request, String.class);
//            System.out.println(response.getBody());
//
//        }catch (HttpClientErrorException e){
//            e.printStackTrace();
//            System.out.println("http post fail!");
//            return false;
//        }
//
//        return true;
//    }
}
