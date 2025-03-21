package lv.rvt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String path = "data/notes.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void Saglabat(List<Piezime> piezimes) {
        try {
            objectMapper.writeValue(new File(path), piezimes);
        } catch (IOException e) {
            System.out.println("Kļūda piezīmju saglabāšanā: " + e.getMessage());
        }
    }

    public static List<Piezime> Ieladet() {
        try {
            return objectMapper.readValue(new File(path), new TypeReference<List<Piezime>>() {});
        } catch (IOException e) {
            System.out.println("Iepriekšējās piezīmes netika atrastas. Izveidots jauns fails.");
            return new java.util.ArrayList<>();
        }
    }
}
