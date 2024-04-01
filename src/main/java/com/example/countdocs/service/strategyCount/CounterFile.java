package com.example.countdocs.service.strategyCount;

import java.io.File;
import java.io.IOException;

public class CounterFile {
    private Counter counter;

    public CounterFile(Counter counter){
        this.counter = counter;
    }

    public int count(File file) throws IOException {
        return counter.getCountPages(file);
    }
}
