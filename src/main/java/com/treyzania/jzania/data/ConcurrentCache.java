package com.treyzania.jzania.data;

import java.util.Collection;

public class ConcurrentCache<T> extends Cache<T> {

	public ConcurrentCache(long cachingTime) {
		
		super(cachingTime);
		
	}
	
	public ConcurrentCache(long cachingTime, int maxSize) {
		
		super(cachingTime, maxSize);
		
	}

	@Override
	public void add(T item) {
		
		synchronized (this) {
			super.add(item);
		}
		
	}

	@Override
	public void addAll(Collection<T> items) {
		
		synchronized (this) {
			super.addAll(items);
		}
		
	}

	@Override
	public void purgeOldest(int count) {
		
		synchronized (this) {
			super.purgeOldest(count);
		}
		
	}

	@Override
	public int ensureSpace(int count) {
		
		synchronized (this) {
			return super.ensureSpace(count);
		}
		
	}

	@Override
	public int verify() {
		
		synchronized (this) {
			return super.verify();
		}
		
	}
	
}
