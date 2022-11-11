package model;

/**
 * Represents an interface for the required annotation.
 * @version 1.0
 * @since 06 Nov 2022
 */
public @interface Required {
	/**
	 * This is the value of the annotation
	 * @return the value of the annotation
	 */
	public boolean value() default true;
}

