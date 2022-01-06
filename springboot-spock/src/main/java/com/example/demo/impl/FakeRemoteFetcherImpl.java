package com.example.demo.impl;

import com.example.demo.RemoteFetcher;
import org.springframework.stereotype.Service;

@Service
public class FakeRemoteFetcherImpl implements RemoteFetcher {
    @Override
    public String fetch(String key) {
        return key + " back";
    }
}
