package com.example.countdocs.service.handler;

import java.io.IOException;

public interface Handler {
    int getCountPages();
    void close() throws IOException;
}
