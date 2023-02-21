package com.blockypenguin.penguincore;

import com.blockypenguin.penguincore.api.AbstractPenguinRef;

public class PenguinCoreRef extends AbstractPenguinRef {
	public PenguinCoreRef() {
		super("penguincore", "Penguin Core");
	}
	
	public static PenguinCoreRef getInstance() {
		return AbstractPenguinRef.getRef(PenguinCoreRef.class).get();
	}
}