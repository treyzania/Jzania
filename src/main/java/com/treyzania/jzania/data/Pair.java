package com.treyzania.jzania.data;

public final class Pair<A, B> {
	
	public final A a;
	public final B b;
	
	public Pair(A a, B b) {
		
		this.a = a;
		this.b = b;
		
	}
	
	@Override
	public int hashCode() {
		
		// Yucky auto-generated code.
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		
		// Yucky auto-generated code.
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
		
	}
	
	@Override
	public String toString() {
		return String.format("(%s,%s)", this.a, this.b);
	}
	
}
