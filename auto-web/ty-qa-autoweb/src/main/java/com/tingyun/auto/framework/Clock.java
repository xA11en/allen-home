package com.tingyun.auto.framework;

public interface Clock {
	 /**
     * @return The current time in milliseconds since epoch time.
     * @see System#currentTimeMillis()
     */
    long now();

    /**
     * Computes a point of time in the future.
     * 
     * @param durationInMillis The point in the future, in milliseconds relative to the {@link #now()
     *        current time}.
     * @return A timestamp representing a point in the future.
     */
    long laterBy(long durationInMillis);

    /**
     * Tests if a point in time occurs before the {@link #now() current time}.
     * 
     * @param endInMillis The timestamnp to check.
     * @return Whether the given timestamp represents a point in time before the current time.
     */
    boolean isNowBefore(long endInMillis);
}
