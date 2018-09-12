package com.simi.spanish.compiler;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class ConverterCompiler {
	
	public static void main(String[] args) throws IOException{
		System.out.println("*** Welcome to the Tango Compiler!!! ***");
        String path = "C://Users//Zookeeper//Downloads//SpanishProgram.txt";
        File inFile = new File(path);
        CompilerScanner tScanner = new CompilerScanner(inFile);
        SpanishCompiler tParser = new SpanishCompiler(tScanner.tokens);
    

	}

}
