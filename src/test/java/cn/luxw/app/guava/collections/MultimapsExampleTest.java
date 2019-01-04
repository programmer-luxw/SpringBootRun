package cn.luxw.app.guava.collections;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.junit.Test;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;

/***************************************
 * @author:Alex Wang
 * @Date:2018/1/14
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class MultimapsExampleTest
{

    @Test
    public void testBasic()
    {
    	//Multiset<String> multiset = HashMultiset.create();
    	//TreeMultimap.create();
    	//Multimap<String, String> multimap a = HashMultimap.create();
    	// Multimap<String, String> multimap = ArrayListMultimap.create();
        LinkedListMultimap<String, String> multipleMap = LinkedListMultimap.create();
        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("1", "1");
        hashMap.put("1", "2");
        assertThat(hashMap.size(), equalTo(1));


        multipleMap.put("1", "1");
        multipleMap.put("1", "2");
        assertThat(multipleMap.size(), equalTo(2));
        System.out.println(multipleMap.get("1"));
    }
}