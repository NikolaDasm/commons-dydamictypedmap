/*
 *  SAttributeMap
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

package nikoladasm.attributemap;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import nikoladasm.sattributemap.SAttributeKey;
import nikoladasm.sattributemap.SAttributeMap;
import nikoladasm.sattributemap.DefaultSAttributeMap;

public class DefaultSAttributeMapUnitTest {

	SAttributeMap map = new DefaultSAttributeMap();
	
	@Test
	public void shouldBeSetAndGetIntegerValue() {
		SAttributeKey<Integer> key = SAttributeKey.valueOf(Integer.class, "first key");
		Integer setValue = Integer.valueOf(345);
		map.attr(key).set(setValue);
		Integer getValue = map.attr(key).get();
		assertThat(getValue, is(equalTo(setValue)));
	}

}
