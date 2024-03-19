package com.infochat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Storage implements Serializable {
	public Object load(String fileName) {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName));
			Object daten = stream.readObject();
			stream.close();
			return daten;
		} catch (Exception e) {
			System.out.println("Das Objekt konnte nicht geladen werden.");
			e.printStackTrace();
		}
		return null;
	}

	public void save(Object data, String fileName) {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName));
			stream.writeObject(data);
			stream.close();
			System.out.println("written");
		} catch (Exception e) {
			System.out.println("Das Objekt konnte nicht geschrieben werden.");
			e.printStackTrace();
		}
	}
}
