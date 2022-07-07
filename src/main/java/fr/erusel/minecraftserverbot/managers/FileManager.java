package fr.erusel.minecraftserverbot.managers;

import fr.erusel.minecraftserverbot.exceptions.InvalidTokenException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    String MESSAGE_PATH = "config/message.txt";
    String TOKEN_PATH = "config/token.txt";
    String CONFIG_PATH = "config";

    Path messageFilePath = Paths.get(MESSAGE_PATH);
    Path tokenFilePath = Paths.get(TOKEN_PATH);
    Path configDirectoryPath = Paths.get(CONFIG_PATH);

    File messageFile;
    File tokenFile;

    FileReader frToken;
    FileReader frMessage;

    BufferedReader brToken;
    BufferedReader brMessage;

    public FileManager(){
        checkConfigDirectoryExist();
        checkTokenFileExist();
        checkMessageFileExist();
    }

    public void checkConfigDirectoryExist() {
        if (Files.exists(configDirectoryPath)) {
            System.out.println("Dossier config existant, passage..");
            return;
        }
        System.out.println("Dossier config inéxistant, création...");
        createConfigDirectory();
    }

    public void checkMessageFileExist(){
        messageFile = new File(MESSAGE_PATH);

        // If the file is already created
        if (messageFile.exists()){
            System.out.println("Fichier message existant, passage...");
            try {
                frMessage = new FileReader(messageFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            brMessage = new BufferedReader(frMessage);
            System.out.println(frMessage);
            return;
        }

        // If the file is not created
        System.out.println("Fichier message inéxistant, création...");
        try {
            messageFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            brMessage = new BufferedReader(new FileReader(messageFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(messageFile.getName());

    }

    public void checkTokenFileExist() {
        tokenFile = new File(TOKEN_PATH);
        if (tokenFile.exists()){
            System.out.println("Fichier token existant, passage...");
            try {
                frToken = new FileReader(tokenFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            brToken = new BufferedReader(frToken);
            return;
        }
        System.out.println("Fichier token inéxistant, création... et stoppage");
        try {
            tokenFile.createNewFile();
            try {
                frToken = new FileReader(tokenFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            brToken = new BufferedReader(frToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Veuillez entrer votre token dans le fichier token");
        System.exit(0);
    }

    public String getToken(){

        String token;

        try {
            token = brToken.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (token == null){
            try {
                throw new InvalidTokenException("Please enter a token into the Token Configuration File");
            } catch (InvalidTokenException e) {
                throw new RuntimeException(e);
            }
        }
        return token;
    }

    private void createConfigDirectory(){
        System.out.println("Dossier config inexistant, création...");
        try {
            Files.createDirectory(configDirectoryPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Dossier config crée !");
    }

    public String getMessageID(){
        String id;
        try {
            id = brMessage.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(id);
        return id;
    }

    public boolean isSetup(){
        String id;
        try {
            id = brMessage.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return id != null;
    }

    public void setMessageID(String id){

        PrintWriter printWriter;

        try {
            printWriter = new PrintWriter(messageFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        printWriter.println(id);
        printWriter.close();

    }






}
