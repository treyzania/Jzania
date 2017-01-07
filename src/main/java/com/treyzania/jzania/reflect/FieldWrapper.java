package com.treyzania.jzania.reflect;

import java.lang.reflect.Field;

public class FieldWrapper<T, F> {
	
	private Field field;
	
	public FieldWrapper(Class<?> clazz, String name) throws NoSuchFieldException, SecurityException {
		
		do {
			
			try {
				this.field = clazz.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				
				if (clazz == Object.class) throw new NoSuchFieldException(name);
				clazz = clazz.getSuperclass();
				
			}
			
		} while (field == null);
		
		this.field.setAccessible(true);
		
	}
	
	public void set(T instance, F value) {
		
		try {
			this.field.set(instance, value);
		} catch (IllegalAccessException e) {
			
			// this can't happen
			
			// just in case
			System.out.println("THE IMPOSSIBLE HAPPENED.");
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public F get(T instance) {
		
		try {
			return (F) this.field.get(instance);
		} catch (IllegalAccessException e) {
			
			// this can't happen
			return null;
			
		}
		
	}
	
}
