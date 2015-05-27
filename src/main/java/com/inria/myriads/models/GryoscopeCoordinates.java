package com.inria.myriads.models;

import java.util.Objects;

import com.fluentinterface.ReflectionBuilder;
import com.fluentinterface.builder.Builder;

public class GryoscopeCoordinates {

	private double x;
	private double y;
	private double z;
	
	public static GryoscopeCoordinatesBuilder create() {
		return ReflectionBuilder.implementationFor(GryoscopeCoordinatesBuilder.class).create();
	}

	public interface GryoscopeCoordinatesBuilder extends Builder<GryoscopeCoordinates> {
		public GryoscopeCoordinatesBuilder withX(double x);
		public GryoscopeCoordinatesBuilder withY(double y);
		public GryoscopeCoordinatesBuilder withZ(double z);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY(), getZ());
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof GryoscopeCoordinates)
				&& Objects.equals(getX(), ((GryoscopeCoordinates) obj).getX())
				&& Objects.equals(getY(), ((GryoscopeCoordinates) obj).getY())
				&& Objects.equals(getZ(), ((GryoscopeCoordinates) obj).getZ());
	}
}
