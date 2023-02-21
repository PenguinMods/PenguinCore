package com.blockypenguin.penguincore.api;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blockypenguin.penguincore.exceptions.MissingRequiredConstructorException;

import net.minecraft.util.Identifier;

/**
 * <p>Abstract class to store information about your mod and provide helpful methods that utilise it.</p>
 * <p>It is recommended that you implement a {@code static getInstance()} method which calls
 * {@link #getRef(Class) getRef()} or {@link #getRefOrElse(Class, AbstractPenguinRef) getRefOrElse()}
 * to return the instance of your exact Ref class.</p>
 */
public abstract class AbstractPenguinRef {
	private static final Map<Class<? extends AbstractPenguinRef>, AbstractPenguinRef> INSTANCE_MAP = new HashMap<>();
	
	protected final String modId;
	protected final String modName;
	
	protected AbstractPenguinRef(String modId, String modName) {
		INSTANCE_MAP.put(this.getClass(), this);
		this.modId = modId;
		this.modName = modName;
	}
	
	public String getModId() {
		return modId;
	}
	
	public String getModName() {
		return modName;
	}
	
	/**
	 * Creates a {@link Logger} for any specified purpose. <br> <br>
	 * Passing no arguments returns a Logger with the name of the mod, taken from {@link #getModName()}. <br>
	 * Passing one argument returns a Logger with the name of the mod, followed by a colon {@code ":"}, followed by the argument passed. <br>
	 * Passing multiple arguments returns a Logger with the name of the mod, followed by a colon {@code ":"}, followed by the arguments passed separated by slashes {@code "/"}.
	 * 
	 * @param strings A varargs array of strings 
	 * @return An instance of {@link Logger}
	 */
	public Logger createLogger(String... strings) {
		String id = modName + ":";
		
		for(String string : strings)
			id += string + "/";
		
		return LoggerFactory.getLogger(id.substring(0, id.length() - 1));
	}
	
	/**
	 * Creates an identifier for any resource belonging to this mod.
	 * 
	 * @param path The path to the resource
	 * @return An identifier, or {@code null} if {@code path} is invalid
	 * @see Identifier#of(String, String) Identifier.of()
	 */
	public Identifier createIdentifier(String path) {
		return Identifier.of(modId, path);
	}
	
	/**
	 * Returns an instance of a subclass of {@code AbstractPenguinRef}, designated by the given {@code clazz}.
	 * 
	 * @param <T> The subtype of the instance of {@code AbstractPenguinRef}
	 * @param clazz The class of the subtype of {@code AbstractPenguinRef}
	 * @return An {@link Optional} containing either the requested instance or null if none exists
	 * @see #getRefOrElse(Class, AbstractPenguinRef) getRefOrElse()
	 */
	public static <T extends AbstractPenguinRef> Optional<T> getRef(Class<T> clazz) {
		return Optional.ofNullable(getRefOrElse(clazz, null));
	}
	
	/**
	 * Returns an instance of a subclass of {@code AbstractPenguinRef}, designated by the given {@code clazz}.
	 * If one has not yet been created, it will be instantiated and added to the {@link #INSTANCE_MAP}.
	 * 
	 * @param <T> The subtype of the instance of {@code AbstractPenguinRef}
	 * @param clazz The class of the subtype of {@code AbstractPenguinRef}
	 * @return Either the requested instance or a new instance of it if none exists
	 * @see #getRefOrElse(Class, AbstractPenguinRef) getRefOrElse()
	 * @throws MissingRequiredConstructorException if {@code T} doesn't have a no-args constructor.
	 */
	public static <T extends AbstractPenguinRef> T getRefOrCreate(Class<T> clazz) {
		return getRef(clazz).orElseGet(() -> {
			try {
				return clazz.getDeclaredConstructor().newInstance();
			}catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new MissingRequiredConstructorException(clazz, e);
			}
		});
	}
	
	/**
	 * Returns an instance of a subclass of {@code AbstractPenguinRef}, designated by the given {@code clazz}, or {@code def} if none exists.
	 * 
	 * @param <T> The subtype of the instance of {@code AbstractPenguinRef}
	 * @param clazz The class of the subtype of {@code AbstractPenguinRef}
	 * @param def The default value to fall back on if no {@code AbstractPenguinRef} can be found for the given class
	 * @return Either the requested instance or {@code def} if none exists
	 * @see #getRef(Class) getRef()
	 */
	@SuppressWarnings("unchecked")
	public static <T extends AbstractPenguinRef> T getRefOrElse(Class<T> clazz, T def) {
		T instance = (T) INSTANCE_MAP.get(clazz);
		
		if(instance != null)
			return instance;
		
		return def;
	}
}