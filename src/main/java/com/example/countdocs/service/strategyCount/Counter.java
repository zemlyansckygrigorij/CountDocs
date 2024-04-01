package com.example.countdocs.service.strategyCount;

import java.io.File;
import java.io.IOException;

public interface Counter {
    int getCountPages(File file)  throws IOException;
}
