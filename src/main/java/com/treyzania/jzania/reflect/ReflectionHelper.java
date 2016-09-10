package com.treyzania.jzania.reflect;

import java.lang.reflect.Field;

public class ReflectionHelper {

	public static void copyFields(Class<?> clazz, Object from, Object to) {
		
		// Quick checks.
		if (clazz == null || from == null || to == null) throw new IllegalArgumentException("agruments cannot be null!");
		if (!clazz.isAssignableFrom(from.getClass()) || !clazz.isAssignableFrom(to.getClass())) throw new IllegalArgumentException("arguments not of same type");
		
		for (Field f : clazz.getDeclaredFields()) {
			
			try {
				
				boolean acc = f.isAccessible();
				f.setAccessible(true);
				f.set(to, f.get(from));
				f.setAccessible(acc);
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
		// Now copy the parent fields.
		if (clazz.getSuperclass() != Object.class) copyFields(clazz.getSuperclass(), from, to);
		
	}
	
}
