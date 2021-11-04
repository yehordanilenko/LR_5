package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Familiars implements Serializable {
    @JsonProperty("familiars")
    ArrayList<Familiar> familiars;
    @JsonProperty("description")
    String description;
    public Familiars(){
        description= LocalDate.now().toString();
        this.familiars=new ArrayList<>();
    }

    void add(Familiar familiar){
        familiars.add(familiar);
    }

    void findMonth(){
        int counter1=0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите номер месяца, рожденных которых в этот месяц вы хотели бы увидеть:");
        int numberMonth = scan.nextInt();
        for (Familiar object : familiars) {
            if(object.getMonth() == numberMonth)
            {
                System.out.println(object);
                counter1++;
            }
        }
        if(counter1==0)
        {
            System.out.println("В указанном месяце нет родившихся знакомых");
        }
    }

    void existPhone() {
        System.out.println("Знакомые, имеющие телефон: ");
        for (Familiar object : familiars) {
            if(object.numberPhone != null){
                System.out.println(object);
            }
        }
    }

    void addNumber(Familiar familiar, int number){
        familiars.add(number,familiar);
        int size = familiars.size();
        for (int i = number; i < size; i++) {
            this.familiars.get(i).serialNumber=i+1;
        }
    }

    void findWithCodOperatore(){
        int counter2=0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите код оператора:");
        String codOperatora = "+38";
        codOperatora += scan.nextLine();
        for (Familiar object : familiars) {
            if(object.getNumberPhone()!=null){
                if(object.getNumberPhone().contains(codOperatora) ){
                    System.out.println(object);
                    counter2++;
                }
            }
        }
        if(counter2 ==0){
            System.out.println("Знакомых с указанным оператором нет");
        }
    }

    void saveFile(String fileWay) throws IOException {
        Saveload saveBase = new Saveload();
        saveBase.save(familiars,fileWay);
    }

    void loadFile(String fileWay) throws IOException {
        Saveload loadBase = new Saveload();
        loadBase.load(familiars , fileWay);
    }

    void serializeSaveFile(String fileWay) throws IOException {
        Saveload saveBase = new Saveload();
        saveBase.serialize(this, fileWay);
    }

    void deSerializeLoadFile(String fileWay) throws IOException {
        Saveload loadBase = new Saveload();
        loadBase.deserialize(this, fileWay);
    }

    void loadJacksonDeSerialize(String fileWay) throws IOException {
        Saveload loadBase = new Saveload();
        this.familiars = loadBase.jacksonDeSerialize(familiars, fileWay);
    }

    void saveJacksonSerializeSaveFile(String fileWay) throws IOException {
        Saveload saveBase = new Saveload();
        saveBase.jacksonSerialize(this, fileWay);
        familiars.clear();
    }
    @Override
    public String toString() {
        return "Familiars{" +
                "familiars=\n" + familiars +
                "\n"+ description+
                '}';
    }
}
