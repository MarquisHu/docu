package com.docu.components.sequence;

public interface Sequence {

	long nextValue() throws SequenceException;
}
