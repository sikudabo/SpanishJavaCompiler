package com.simi.spanish.compiler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by Simeon Ikudabo on 09/12/18.
 * Creates an output file and then generates the corresponding java code
 */


public class CodeGenerator {
	
	//Create class members for the FileWriter object, the File object, and a private Hashtable with no external access. 
	//The wordDict will be a map that stores the Spanish Java commands as keys, and the English commands as values.
	public static FileWriter fw;
    public static File file;
    private static Hashtable<String, String> wordDict = new Hashtable<String, String>();

    //Create the constructor for this class. Have a throws cause for the checked exception "IOException".
    //I am adding the throws clause here since the compiler knows that the IOException is common and highly possible.
    //On initialization we will generate a welcome message in the constructor. 
    public CodeGenerator(){
        System.out.println("*** Welcome to the Code Generator!!! ***");
        buildDictionary();
    }
    
    //helper functions
    //createFileWriter method will pass the FileWriter object a filename with the .java extension added. 
    public static void createFileWriter(String filename) throws IOException {
        file = new File(filename + ".java");
        fw = new FileWriter(file);
    }
    
    //Simple method that will close the FileWriter 
    public static void closeFileWriter() throws IOException {
        fw.flush();
        fw.close();
    }
    
    //key = spanish, value = english
    public static void buildDictionary() {
        //Enter the keys and values for the dictionary.
    	//This HashMap will be loaded when the class is initialized. We call the method in the constructor. 
        wordDict.put("públic@", "public");
        wordDict.put("ent", "int");
        wordDict.put("dec", "double");
        wordDict.put("cadena", "String");
        wordDict.put("bool", "Boolean");
        wordDict.put("ciert@", "true");
        wordDict.put("fals@", "false");
        wordDict.put("nuev@", "new");
        wordDict.put("si", "if");
        wordDict.put("sino", "else");
        wordDict.put("para", "for");
        wordDict.put("mientras", "while");
        wordDict.put("hacer", "do");
        wordDict.put("clase", "class");
        wordDict.put("estátic@", "static");
        wordDict.put("vaci@", "void");
        wordDict.put("nul@", "null");
        wordDict.put("regresar", "return");
        wordDict.put("escáner", "Scanner");
        wordDict.put("sigEnt", "nextInt");
    }
    
    //Method that takes a Spanish keyword String argument and returns the English equivalent. 
    public static String translate(String keyword) {
        return wordDict.get(keyword);
    }
    
    //We will pass an access modifier and a class ID. We will translate the access modifier. 
    //publis is the access modifier that will be translated. 
    public static void writeClassStruct(String classId, String accessMod) throws IOException {
        accessMod = translate(accessMod);
        fw.write(accessMod + " class " + classId + " {");
    }
    
    //Write the main method after we write the class structure. 
    public static void writeMainStruct() throws IOException {
        fw.write("\npublic static void main(String [] args) {");
    }
    
    //Create a print statement. Pass a String to be printed.
    //Use escape characters within the print statement string so that we can put the String (s) in quotes. 
    public static void writePrintStmt(String s) throws IOException {
        fw.write("\nSystem.out.println(\"" + s + "\");");
    }
    
    //variable declaration writer helpers
    //In this method we must pass a dataType, translate it, and write a corresponding ID for the datatype. 
    public static void writeDataTypeAndId(String dataType, String id) throws IOException {
        dataType = translate(dataType);
        fw.write(dataType + " " + id + " ");
    }
    
    //Equals symbol. 
    public static void writeDecTail() throws IOException {
        fw.write("=");
    }
    
    //We will translate any bool value that is entered. 
    public static void writeBoolVal(String boolVal) throws IOException {
        fw.write(translate(boolVal) + " ");
    }
    
    //This method adds a delimiter and then a corresponding new line to any statement.  
    public static void writeSemiColon() throws IOException {
        fw.write(";\n");
    }
    
    //This writes the beginning of any "if" statement. 
    public static void writeIfBeg() throws IOException {
        fw.write("if ( ");
    }
    
    //This writes the opening curly bracket
    public static void writeOpenCB() throws IOException {
        fw.write("{ ");
    }
    
    //This writes the closing curly bracket
    public static void writeCloseCB() throws IOException {
        fw.write("} ");
    }
    
    //This writes the opening parenthesis. 
    public static void writeOpenParen() throws IOException {
        fw.write("( ");
    }
    
    //This will write the closing parenthesis. 
    public static void writeCloseParen() throws IOException {
        fw.write(") ");
    }

}
