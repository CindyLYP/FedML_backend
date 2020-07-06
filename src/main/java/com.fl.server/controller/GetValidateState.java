package com.fl.server.controller;

import com.fl.server.object.Index;
import com.fl.server.object.TrainState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetValidateState {
    @GetMapping("/getTrainState ")
    @ResponseBody
    public TrainState GetTrainStateFunc(@RequestBody Index index) {
        TrainState trainState = new TrainState();
        // get train state from model

        return trainState;
    }
}