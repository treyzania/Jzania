package com.treyzania.jzania.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class MemberReferenceSet<T> implements Set<T> {
	
	private ToIntFunction<T> hasher;
	private Set<MREntry> entries;
	
	public MemberReferenceSet(ToIntFunction hasher) {
		
		this.hasher = hasher;
		this.entries = new HashSet<>();
		
	}
	
	@Override
	public int size() {
		return this.entries.size();
	}

	@Override
	public boolean isEmpty() {
		return this.entries.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		
		for (MREntry e : this.entries) {
			if (e.equals(o)) return true;
		}
		
		return false;
		
	}

	@Override
	public Iterator<T> iterator() {
		return new MREIterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("hiding") // FIXME
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T e) {
		return this.entries.add(new MREntry(e));
	}

	@SuppressWarnings("unchecked") // FIXME
	@Override
	public boolean remove(Object o) {
		return this.entries.remove(new MREntry((T) o));
	}

	@SuppressWarnings("unchecked") // FIXME
	@Override
	public boolean containsAll(Collection<?> c) {
		
		for (Object o : c) {
			if (!this.entries.contains(new MREntry((T) o))) return false;
		}
		
		return true;
		
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		
		boolean res = false;
		for (T t : c) res |= this.entries.add(new MREntry(t));
		
		return res;
		
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		this.entries.clear();
	}
	
	private class MREntry {
		
		private final T obj;
		
		public MREntry(T o) {
			this.obj = o;
		}
		
		@Override
		public int hashCode() {
			return hasher.applyAsInt(this.obj);
		}

		@Override
		public boolean equals(Object obj) {
			return this.obj.equals(obj);
		}
		
		public T getObject() {
			return this.obj;
		}
		
	}
	
	private class MREIterator implements Iterator<T> {
		
		private Iterator<MREntry> iter;
		
		public MREIterator() {
			this.iter = entries.iterator();
		}
		
		@Override
		public boolean hasNext() {
			return this.iter.hasNext();
		}

		@Override
		public T next() {
			return this.iter.next().getObject();
		}

		@Override
		public void remove() {
			this.iter.remove();
		}
		
	}
	
}
