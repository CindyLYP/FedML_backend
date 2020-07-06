package com.fl.server.controller;

import com.fl.server.object.Index;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fl.server.object.TrainState;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class GetTrainState {
    @GetMapping("/getTrainState ")
    @ResponseBody
    public TrainState GetTrainStateFunc(@RequestBody Index index) {
        TrainState trainState = new TrainState();
        // get train state from model

        return trainState;
    }
}