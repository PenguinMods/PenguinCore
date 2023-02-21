package com.blockypenguin.penguincore.exceptions;

import java.util.stream.Stream;

public class MissingRequiredConstructorException extends RuntimeException {
	private static final long serialVersionUID = -4773056181089129013L;

	public MissingRequiredConstructorException(String message) {
        super(message);
    }
    
    public MissingRequiredConstructorException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MissingRequiredConstructorException(Throwable cause) {
        super(cause);
    }
    
    public MissingRequiredConstructorException(Class<?> clazz, Throwable cause, Class<?>... parameterTypes) {
        this(
        	"Could not create new instance of " + clazz.getSimpleName() + ", as a " +
        	(
        		parameterTypes.length == 0
        		? "no-args constructor must be present!"
        		: "constructor with " + parameterTypes.length + " arguments must be present: " + getTypes(parameterTypes)
        	),
        	cause
        );
    }
    
    private static String getTypes(Class<?>[] parameterTypes) {
    	return Stream.of(parameterTypes).collect(
    		StringBuilder::new,
    		(sb, clazz) -> sb.append(clazz.getSimpleName() + ", "),
    		(sb1, sb2) -> sb1.append(sb2)
    	).reverse().delete(0, 2).reverse().toString();
    }
}