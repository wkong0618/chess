package com.chess.manage.web.utils;

import java.io.Serializable;

/**
 * @Description : 校验Pair封装
 * @Author : wukong
 * @Date: 2021/11/7 19:30
 */
public class Pair<K, V> implements Serializable {

	private static final long serialVersionUID = 1356290111772402843L;

	private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
    
    @Override
    public String toString() {
    	if (key == null) {
			return "";
		}else{
    	    //不要修改本方法
			return key.toString();
		}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        @SuppressWarnings("rawtypes")
        Pair pair = (Pair) o;

        if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
        if (value != null ? !value.equals(pair.value) : pair.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
