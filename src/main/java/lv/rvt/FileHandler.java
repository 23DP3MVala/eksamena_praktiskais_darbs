package lv.rvt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.rvt.tools.*;

import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class FileHandler {
    private static final String filename = "notes.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void Saglabat(List<Piezime> piezimes) {
        try (BufferedWriter writer = Helper.getWriter(filename, StandardOpenOption.CREATE)) {
            objectMapper.writeValue(writer, piezimes);
        } catch (IOException e) {
            System.out.println("Kļūda piezīmju saglabāšanā: " + e.getMessage());
        }
    }

    public static List<Piezime> Ieladet() {
        try (BufferedReader reader = Helper.getReader(filename))  {
            return objectMapper.readValue(reader, new TypeReference<List<Piezime>>() {});
        } 
        catch (FileNotFoundException e) {
            System.out.println("Fails netika atrasts. Izveidots jauns fails.");
            return new ArrayList<>();
        }
        catch (IOException e) {
            System.out.println("Kļūda lasot piezīmes no faila: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
