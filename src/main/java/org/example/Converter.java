package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class User {
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class Converter {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            boolean firstLine = true;
            String[] headers = null;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    headers = line.split(" ");
                    firstLine = false;
                } else {
                    String[] values = line.split(" ");
                    if (values.length == headers.length) {
                        users.add(new User(values[0], Integer.parseInt(values[1])));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter("user.json")) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
