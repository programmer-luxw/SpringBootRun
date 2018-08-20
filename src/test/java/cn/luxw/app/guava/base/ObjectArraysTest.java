package cn.luxw.app.guava.base;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ObjectArrays;

public class ObjectArraysTest {
	String[] array1 = new String[] { "one", "two", "three" };
	String[] array2 = new String[] { "four", "five" };

	@Test
	public void testConcat() {
		// when
		String[] newArray = ObjectArrays.concat(array1, array2, String.class);

		// then
		Assert.assertThat("新数组长度", newArray.length, CoreMatchers.is(5));
	}

	@Test
	public void testAppendElement() {

		// when
		String[] newArray = ObjectArrays.concat(array2, "six");

		// then
		Assert.assertThat(newArray.length, CoreMatchers.is(3));
		Assert.assertThat(newArray[2], CoreMatchers.is("six"));
	}

	@Test
	public void testPrependElement() {

		// when
		String[] newArray = ObjectArrays.concat("zero", array1);

		// then
		Assert.assertThat(newArray.length, CoreMatchers.is(4));
		Assert.assertThat(newArray[0], CoreMatchers.is("zero"));
	}

	@Test
	public void testCreate() {
		Integer[] array = ObjectArrays.newArray(Integer.class, 3);
		System.out.println(array[0]);
	}

}
