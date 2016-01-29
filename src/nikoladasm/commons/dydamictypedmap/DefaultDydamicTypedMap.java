/*
 *  DydamicTypedMap
 *  Copyright (C) 2015  Nikolay Platov
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nikoladasm.commons.dydamictypedmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.requireNonNull;

public class DefaultDydamicTypedMap implements DydamicTypedMap {

	private ConcurrentHashMap<DydamicTypedKey<?>, DydamicTypedValue<?>> map =
		new ConcurrentHashMap<>();
	
	private static final class DefaultDydamicTypedValue<T> implements DydamicTypedValue<T> {

		private final DydamicTypedKey<T> key;		
		private final AtomicReference<T> value = new AtomicReference<>();
		
		public DefaultDydamicTypedValue(DydamicTypedKey<T> key, T value) {
			this.key = key;
			this.value.set(value);
		}
		
		@Override
		public DydamicTypedKey<T> key() {
			return key;
		}

		@Override
		public T get() {
			return value.get();
		}

		@Override
		public void set(T value) {
			this.value.set(value);
		}

		@Override
		public T getAndSet(T value) {
			return this.value.getAndSet(value);
		}

		@Override
		public T setIfAbsent(T value) {
			this.value.compareAndSet(null, value);
			return this.value.get();
		}

		@Override
		public T getAndRemove() {
			return value.getAndSet(null);
		}

		@Override
		public boolean compareAndSet(T oldValue, T newValue) {
			return value.compareAndSet(oldValue, newValue);
		}

		@Override
		public void remove() {
			value.set(null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> DydamicTypedValue<T> value(DydamicTypedKey<T> key) {
		requireNonNull(key,"Key can't be null");
		map.putIfAbsent(key, new DefaultDydamicTypedValue<T>(key, null));
		return (DydamicTypedValue<T>) map.get(key);
	}

	@Override
	public <T> boolean containsKey(DydamicTypedKey<T> key) {
		requireNonNull(key,"Key can't be null");
		return (!map.containsKey(key)) ? false : map.get(key) != null;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public <T> void remove(DydamicTypedKey<T> key) {
		map.remove(key);
	}

}
