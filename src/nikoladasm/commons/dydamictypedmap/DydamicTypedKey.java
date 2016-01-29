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

public final class DydamicTypedKey<T> {
	
	protected Class<T> type;
	protected String name;
	
	public DydamicTypedKey(Class<T> type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static <T> DydamicTypedKey<T> valueOf(Class<T> type, String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name can't be null or empty");
		if (type == null)
			throw new IllegalArgumentException("Class can't be null or empty");
		return new DydamicTypedKey<T>(type, name);
	}

	public Class<?> type() {
		return type;
	}
	
	public String name() {
		return name;
	}
	
	@Override
	public String toString() {
		return type.getName()+'#'+name;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		return toString().equals(object.toString());
	}
}
