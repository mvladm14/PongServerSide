package com.inria.myriads.models;

import java.util.Objects;

import com.fluentinterface.ReflectionBuilder;
import com.fluentinterface.builder.Builder;

public class Coordinates {

	private int x;
	private int y;

	public static CoordinatesBuilder create() {
		return ReflectionBuilder.implementationFor(CoordinatesBuilder.class)
				.create();
	}

	public interface CoordinatesBuilder extends Builder<Coordinates> {

		public CoordinatesBuilder withX(int x);

		public CoordinatesBuilder withY(int y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Coordinates)
				&& Objects.equals(getX(), ((Coordinates) obj).getX())
				&& Objects.equals(getY(), ((Coordinates) obj).getY());
	}
}
