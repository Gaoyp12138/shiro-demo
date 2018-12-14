package com.example.shirodemo.rest;

import com.example.shirodemo.common.RestResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoyp
 * @time 下午3:18
 */
@RestController
public class ShiroController {

    @GetMapping("/info")
    public RestResp info(){
        return RestResp.success("info");
    }

    @GetMapping("/login")
    public RestResp login(){
        return RestResp.success("login");
    }



}
