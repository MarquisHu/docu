package com.docu.components.sequence;

public interface SequenceDao {
	SequenceRange nextRange(String name) throws SequenceException;
}
