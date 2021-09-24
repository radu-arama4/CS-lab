package sample.util;

import sample.data.CustomItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class SerializationDeserializationUtil {
    public static boolean serialize(List<CustomItem> obj, String name) {
        try {
            File dir = new File("src/sample/storage");
            dir.mkdirs();
            File tmp = new File(dir, name + ".txt");
            tmp.createNewFile();

            FileOutputStream fileOut = new FileOutputStream("src/sample/storage/" + name + ".txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("Audit items saved!");


            try {
                Files.write(Paths.get("src/sample/existing.txt"), ("\n"+name).getBytes(), StandardOpenOption.APPEND);
            }catch (IOException e) {
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<CustomItem> deserialize(String name) {
        try {
            FileInputStream fileIn = new FileInputStream("src/sample/storage/" + name + ".txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            List<CustomItem> receivedItems = (List<CustomItem>) in.readObject();

            System.out.println("Saved items:");

            receivedItems.forEach(System.out::println);

            in.close();
            fileIn.close();

            return receivedItems;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
