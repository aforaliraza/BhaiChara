package com.techpp.utils;

import java.util.List;
import java.util.Map;

public class AppUtils {

	
	public static boolean isNull(Object object) {
		if(null == object)
			return true;
		return false;
	}
	
	public static boolean isNullOEmptyList(List list) {
		if(null == list || list.isEmpty())
			return true;
		return false;
	}
	
	public static boolean isNullOEmptyMap(Map map) {
		if(null == map || map.isEmpty())
			return true;
		return false;
	}
}
