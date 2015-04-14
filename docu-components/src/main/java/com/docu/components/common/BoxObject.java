package com.docu.components.common;

public class BoxObject<T> {
	private T value ;
	public BoxObject(T v){
		value = v;
	}
	public BoxObject(){ 
	}

	public void setValue(T v){
		value = v;
	}
	public T getValue(){
		return value; 
	}
	
	public String toString(){
		return (value == null? null: value.toString());
	}
}
