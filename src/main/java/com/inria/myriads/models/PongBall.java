package com.inria.myriads.models;

import java.util.Objects;

import com.fluentinterface.ReflectionBuilder;
import com.fluentinterface.builder.Builder;

public class PongBall {

	private long id;
	private Coordinates coordinates;
	private int diameter;

	public static PongBallBuilder create() {
		return ReflectionBuilder.implementationFor(PongBallBuilder.class)
				.create();
	}

	public interface PongBallBuilder extends Builder<PongBall> {
		
		public PongBallBuilder withId(long id);

		public PongBallBuilder withDiameter(int diameter);

		public PongBallBuilder withCoordinates(Coordinates coordinates);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDiameter(), getCoordinates());
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof PongBall)
				&& Objects.equals(getCoordinates(),
						((PongBall) obj).getCoordinates())
				&& Objects
						.equals(getDiameter(), ((PongBall) obj).getDiameter());
	}

}
