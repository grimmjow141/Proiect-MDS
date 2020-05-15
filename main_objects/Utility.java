package main_objects;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

//just an auxiliary abstract class to store different utility often used functions and variables
public abstract class Utility {
	
	private Utility() { }    //to prevent instantiation
	
	public static final SimpleDateFormat date_format = new SimpleDateFormat("E dd.MM hh:mm a"); //custom date format
	public static final SimpleDateFormat hour_format = new SimpleDateFormat("hh:mm");           //custom hour format
	public static enum Exc {STUDENT, RETIRED, DISABLED;}                  //exception, used in Client class
	public static enum Type {ACTION, ADVENTURE, THRILLER, COMEDY, DRAMA;} //type of a movie, used in Movie class

	public static String fill_zeros (int id) {       //it fills with 0s in the format: #CCC
		String nr = String.valueOf(id); 
		if (id > 99)
			return nr;
		else
			if (id > 9)
				return "0" + nr;
			else
				return "00" + nr;
	}
	
	
	@SuppressWarnings("The objects MUST have constructor with no parameters") 
	public static <T> ArrayList<T> read_from_file(String path, Class<T> type) { 
		try {
			ArrayList<T> objects = new ArrayList<T>();
			Field[] fields = type.getDeclaredFields();
			
			for (Field field: fields)
				field.setAccessible(true);
			for (String line: Files.readAllLines(Paths.get(path))) {
				String[] values = line.split(",");
				T object = (T) Class.forName(type.getName()).newInstance();
				if (values.length == fields.length) {
					for (int i = 0; i < fields.length; i ++) {
						if (!values[i].isBlank()) {
							//object = (T) Class.forName(type.getName()).newInstance();
							Object value = null;   
							String name = Character.toUpperCase(fields[i].getType().getSimpleName().charAt(0)) + fields[i].getType().getSimpleName().substring(1); 
							if (String.class.equals(fields[i].getType())) 
								fields[i].set(object, values[i]);
							else if (char.class.equals(fields[i].getType())) {
								if (values[i].length() == 1) 	//we make sure that the string has length of 1 to "cast" to char type
									fields[i].set(object, values[i].charAt(0));
								else 
									fields[i].set(object, null);
							} 
							else if (Date.class.equals(fields[i].getType())) {
								try {
									SimpleDateFormat f = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");			
									fields[i].set(object, f.parse(values[i]));
								} catch (ParseException e) {
									fields[i].set(object, null);
									e.printStackTrace();
								}
							}
							else {
								Class<?> field_type;
								if (fields[i].getType().isPrimitive()) {
									field_type = Class.forName("java.lang." + name + ( (int.class.equals(fields[i].getType()) ) ? "eger" : "") );
									Method method = field_type.getMethod("parse" + name, String.class);
									value = method.invoke(null, values[i]);
								} else if (fields[i].getType().isEnum()) {
									
									field_type = Class.forName(fields[i].getType().getName());
									value = Enum.valueOf((Class<Enum>) field_type, values[i]);
								}
								fields[i].set(object, value);
							}
						} else 
							fields[i].set(object, null);
					}
					objects.add(object);
				} 

			}
			return objects;
		} catch (InvalidPathException e) {
			System.out.println("Invalid path..");
			e.printStackTrace();
			return null;
		} catch (NoSuchMethodException e) {
			System.out.println("No such method error..");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O error..");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("Instantiation error..");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Illegal acces error..");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument error..");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("Invocation target error..");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class in a field not found..");
			e.printStackTrace();
		} 
		return null;
	}
	
	
	
	public static <T> void write_to_file (String path, ArrayList<T> objects) {
		try {
			FileWriter writer = new FileWriter(path, true /*<-append mode*/);
			Field[] fields;
			if (objects.get(0).getClass().getSuperclass().isInstance(new Object()))   //a way to check if the object is not inherited
				fields = objects.get(0).getClass().getDeclaredFields();
			else
				fields = objects.get(0).getClass().getSuperclass().getDeclaredFields();
			ArrayList<Field> unsynthetic_fields = new ArrayList<Field>();
			for (Field field: fields)
				if (!field.isSynthetic()) 
					unsynthetic_fields.add(field);
			for (Field field: unsynthetic_fields)
				field.setAccessible(true);

			for (T object: objects) {
				for (int i = 0; i < unsynthetic_fields.size(); i ++) {
					String value = String.valueOf(unsynthetic_fields.get(i).get(object));
					if (value == "null")
						value = " ";
					if (i < unsynthetic_fields.size() - 1)
						value += ",";
					writer.append(value);
				}
				writer.append(System.lineSeparator());
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("I/O error..");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument error..");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Illegal access error..");
			e.printStackTrace();
		}
		
	}
}
