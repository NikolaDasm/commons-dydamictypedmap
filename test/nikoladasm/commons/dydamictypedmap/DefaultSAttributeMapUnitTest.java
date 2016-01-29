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

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import nikoladasm.commons.dydamictypedmap.DefaultDydamicTypedMap;
import nikoladasm.commons.dydamictypedmap.DydamicTypedKey;
import nikoladasm.commons.dydamictypedmap.DydamicTypedMap;

public class DefaultSAttributeMapUnitTest {

	DydamicTypedMap map = new DefaultDydamicTypedMap();
	
	@Test
	public void shouldBeSetAndGetIntegerValue() {
		DydamicTypedKey<Integer> fkey1 = DydamicTypedKey.valueOf(Integer.class, "first key");
		assertThat(fkey1.type(), is(equalTo(Integer.class)));
		assertThat(fkey1.name(), is(equalTo("first key")));
		DydamicTypedKey<Integer> fkey2 = DydamicTypedKey.valueOf(Integer.class, "first key");
		Integer setValue = Integer.valueOf(345);
		assertThat(map.containsKey(fkey1), is(equalTo(false)));
		map.value(fkey1).set(setValue);
		assertThat(map.containsKey(fkey1), is(equalTo(true)));
		Integer getValue = map.value(fkey2).get();
		assertThat(getValue, is(equalTo(setValue)));
	}

	@Test
	public void shouldBeSetAndRemoveIntegerValue() {
		DydamicTypedKey<Integer> fkey1 = DydamicTypedKey.valueOf(Integer.class, "first key");
		DydamicTypedKey<Integer> fkey2 = DydamicTypedKey.valueOf(Integer.class, "first key");
		Integer setValue = Integer.valueOf(345);
		map.value(fkey1).set(setValue);
		assertThat(map.containsKey(fkey2), is(equalTo(true)));
		map.remove(fkey2);
		assertThat(map.containsKey(fkey2), is(equalTo(false)));
	}

	@Test
	public void shouldBeClearMap() {
		DydamicTypedKey<Integer> fkey1 = DydamicTypedKey.valueOf(Integer.class, "first key");
		DydamicTypedKey<Integer> fkey2 = DydamicTypedKey.valueOf(Integer.class, "first key");
		Integer setValue = Integer.valueOf(345);
		map.value(fkey1).set(setValue);
		assertThat(map.containsKey(fkey2), is(equalTo(true)));
		map.clear();
		assertThat(map.containsKey(fkey2), is(equalTo(false)));
	}
}
