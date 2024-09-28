package com.dantalian.ojcodesandbox.security;

import com.dantalian.ojcodesandbox.unsafe.RunFileError;

import java.io.File;
import java.io.IOException;

public class TestSecurityManager {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setSecurityManager(new MySecurityManager());
        RunFileError runFileError = new RunFileError();
        runFileError.test();
    }
}
