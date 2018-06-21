package com.company;

import com.company.services.FileWrapper.FileWrapper;
import com.company.services.dbService.DBService;

public class Main {
    private static FileWrapper fileWrapper;

    public static void main(String[] args) {
        fileWrapper = FileWrapper.getInstance();

    }
}
