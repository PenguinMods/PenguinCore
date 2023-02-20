package com.blockypenguin.penguincore;

import com.blockypenguin.penguincore.api.AbstractPenguinRef;

public class Ref extends AbstractPenguinRef {
	public Ref() {
		super("penguincore", "Penguin Core");
	}
	
	public static Ref getInstance() {
		return AbstractPenguinRef.getRef(Ref.class).get();
	}
}