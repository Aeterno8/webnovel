package com.github.aeterno8.webnovel.service;

public interface SequenceGeneratorService {
    /**
     * Generates a sequence number based on the specified sequence name.
     * <p>
     * This method retrieves and increments a counter stored in the database,
     * returning the new sequence number. If no sequence exists for the given
     * name, it initializes a new sequence starting at 1.
     * </p>
     *
     * @param seqName the name of the sequence to generate a number for
     * @return the generated sequence number as a {@code long}
     *         (returns 1 if the sequence does not exist and is created)
     */
    long generateSequence(String seqName);
}