package com.company;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Saveload implements Serializable{
    @JsonProperty("familiars")
    public void save(ArrayList<Familiar> familiars , String fileWay) throws IOException {
        FileWriter outStream = new FileWriter(fileWay);
        BufferedWriter bw = new BufferedWriter(outStream);
        for (Familiar object : familiars) {
            try {
                bw.write(String.valueOf(object.getSerialNumber()));
                bw.write(System.lineSeparator());
                bw.write(object.getFIO());
                bw.write(System.lineSeparator());
                bw.write(String.valueOf(object.getYear()));
                bw.write(System.lineSeparator());
                bw.write(String.valueOf(object.getMonth()));
                bw.write(System.lineSeparator());
                bw.write(String.valueOf(object.getDay()));
                bw.write(System.lineSeparator());
                bw.write(object.getAddress());
                bw.write(System.lineSeparator());
                bw.write(object.getNumberPhone());
                bw.write(System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bw.close();
        outStream.close();
    }
    public void serialize(Familiars familiars, String fileName) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(familiars);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        familiars.familiars.clear();
    }

    public void jacksonSerialize(Familiars familiars, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.writeValue(new File(fileName), familiars);
    }

    public void load(ArrayList<Familiar> familiars , String fileWay) throws IOException {
        familiars.clear();
        try {
            Scanner scan = new Scanner(new FileReader(fileWay));
            int serialNumber;
            String FIO;
            int year;
            int month;
            int day;
            String address;
            String numberPhone;
            while (scan.hasNextLine()) {
                serialNumber = Integer.valueOf(scan.nextLine());
                FIO = scan.nextLine();
                year = Integer.valueOf(scan.nextLine());
                month = Integer.valueOf(scan.nextLine());
                day = Integer.valueOf(scan.nextLine());
                address = scan.nextLine();
                numberPhone = scan.nextLine();
                familiars.add(new Familiar(serialNumber, FIO, year, month, day, address, numberPhone));
            }
            scan.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deserialize(Familiars familiars, String fileName) throws IOException {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            familiars = (Familiars) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException с) {
            System.out.println("Not found!");
            с.printStackTrace();
        }
        //return familiars;
    }

    public ArrayList jacksonDeSerialize(ArrayList<Familiar> familiars, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Familiars newFamiliars = objectMapper.readValue(new File(fileName), Familiars.class);
        return familiars = newFamiliars.familiars;
    }

}
