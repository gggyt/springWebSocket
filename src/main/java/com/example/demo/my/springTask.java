package com.example.demo.my;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class springTask {

    @Async
    public void dealWithMessage() throws Exception {

    }
}
