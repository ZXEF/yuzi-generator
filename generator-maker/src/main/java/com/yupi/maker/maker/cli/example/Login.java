package com.yupi.maker.maker.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--username"}, description = "Username")
    private String user;

    @Option(names = {"-p", "--password"}, arity = "0..1",description = "Password",interactive = true)
    private String password;

    @Option(names = {"-cp", "--checkpassword"},arity = "0..1", description = "checkPassword",interactive = true)
    private String checkpassword;

    public Integer call() throws Exception {
        System.out.println("psw:"+password);
        System.out.println("checkpsw:"+checkpassword);
        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u","user123","-p","123","-cp","123456");
    }
}
