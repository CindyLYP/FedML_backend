package com.fl.server.controller;

import com.fl.server.object.Index;
import com.fl.server.object.TrainState;
import com.fl.server.object.ValidateState;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetValidateState {
    @PostMapping("/getValidateState")
    @ResponseBody
    public ValidateState GetValidateStateFunc(@RequestBody Index index) {
        ValidateState validateState = new ValidateState();
        // get  validateState from model

        return validateState;
    }
}