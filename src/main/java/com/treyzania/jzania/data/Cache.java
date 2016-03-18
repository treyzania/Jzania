package com.treyzania.jzania.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A simple non-concurrent caching mechanism.
 * 
 * @author Treyzania
 *
 * @param <T> The type of object stored by this cache.
 */
public class Cache<T> implements Iterable<T> {

	private List<Entry<T>> cacheEntries;
	private long cacheTime;
	private int cacheMaxSize;
	
	public Cache(long cachingTime, int maxSize) {
		
		this.cacheTime = cachingTime;
		this.cacheMaxSize = maxSize;
		
		this.cacheEntries = new ArrayList<>();
		
	}
	
	public Cache(long cachingTime) {
		this(cachingTime, Integer.MAX_VALUE);
	}
	
	/**
	 * Adds the single item to the cache.
	 * 
	 * @param item
	 */
	public void add(T item) {
		
		this.verify();
		this.ensureSpace(1);
		
		this.cacheEntries.add(new Entry<>(System.currentTimeMillis(), item));
		
	}
	
	/**
	 * Adds the collection of items to the cache.
	 * 
	 * @param items
	 */
	public void addAll(Collection<T> items) {
		
		this.verify();
		this.ensureSpace(items.size());
		
		long time = System.currentTimeMillis();
		for (T item : items) {
			this.cacheEntries.add(new Entry<>(time, item));
		}
		
	}
	
	/**
	 * Verifies the cache, then removes the specified number of items from the cache.
	 * 
	 * @param count The number of items removed.
	 */
	public void purgeOldest(int count) {
		
		this.verify();
		this.purgeOldest_noVerify(count);
		
	}
	
	private void purgeOldest_noVerify(int count) {
		
		if (count <= 0) return;
		
		// Works as fast as I care to make it.
		this.cacheEntries.removeAll(this.cacheEntries.subList(0, count));
		
	}
	
	/**
	 * Ensures that the cache can contain the specified number of items passed by removing old items as necessary.
	 * 
	 * @param count
	 * @return The number of items removed.
	 */
	public int ensureSpace(int count) {
		
		// Calculate and remove the amount we need to purge.
		int removed = Math.max(0, count + this.size() - this.cacheMaxSize); // TODO Verify this math.
		this.purgeOldest_noVerify(removed);
		return removed;
		
	}
	
	/**
	 * Verifies the integrity of the cache, purging old +
	 * 
	 * @return The quantity of items cleared.
	 */
	public int verify() {
		
		int removed = 0;
		long currentTime = System.currentTimeMillis();
		Iterator<Entry<T>> iter = this.cacheEntries.iterator();
		
		while (iter.hasNext()) {
			
			Entry<T> entry = iter.next();
			
			if (entry.time + this.cacheTime <= currentTime) {
				
				removed++;
				iter.remove();
				
			} else {
				
				// Since things are stored in order of introduction, we don't need to worry about the rest of the things.
				break;
				
			}
			
		}
		
		return removed;
		
	}
	

	/**
	 * @return The current size of the cache.
	 */
	public int size() {
		return this.cacheEntries.size();
	}
	
	/**
	 * @return An iterator of the values in the cache.
	 */
	@Override
	public Iterator<T> iterator() {
		return new CacheIterator<>(this.cacheEntries.iterator());
	}
	
	/**
	 * @return A iterator of the entries in the internal list.
	 */
	public ListIterator<Entry<T>> entryIterator() {
		return this.cacheEntries.listIterator();
	}
	
	public static class Entry<T> implements Comparable<Entry<T>> {
		
		public final long time;
		public final T value;
		
		private Entry(long time, T value) {
			
			this.time = time;
			this.value = value;
			
		}
		
		@Override
		public int compareTo(Entry<T> o) {
			return (int) Math.signum(Long.compare(this.time, o.time)); // Could possibly be faster.
		}
		
		@Override
		public int hashCode() {
			
			// Auto-generated yuck.
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (time ^ (time >>> 32));
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
			
		}
		
		@Override
		public boolean equals(Object obj) {
			
			// Auto-generated yuck.
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entry<?> other = (Entry<?>) obj;
			if (time != other.time)
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
			
		}
		
	}
	
	public static class CacheIterator<T> implements Iterator<T> {
		
		private Iterator<Entry<T>> dependency;
		
		private CacheIterator(Iterator<Entry<T>> dep) {
			this.dependency = dep;
		}
		
		@Override
		public boolean hasNext() {
			return this.dependency.hasNext();
		}

		@Override
		public T next() {
			return this.dependency.next().value;
		}
		
	}
	
}