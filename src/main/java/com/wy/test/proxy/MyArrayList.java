package com.wy.test.proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArrayList<E>{
    // 默认容量
    private static final int DEFAULT_CAPACITY = 10;
    // 如果list为空，默认元素
    private static final Object[] EMPTY_ELEMENT = new Object[DEFAULT_CAPACITY];
    // 使用默认构造后的初始值
    private static final Object[] DEFAULT_EMPTY_ELEMENT = new Object[DEFAULT_CAPACITY];

    private Object[] elementData;
    private int size;

    public MyArrayList(){
        this.elementData = DEFAULT_EMPTY_ELEMENT;
    }

    public MyArrayList(int initCapacity)throws Exception{
        if (initCapacity > 0){
            this.elementData = new Object[initCapacity];
        }else if(initCapacity == 0){
            this.elementData = EMPTY_ELEMENT;
        }else {
            throw new Exception("ERROR");
        }
    }

    /**
     * 添加元素
     */
    public boolean add(E e) throws Exception {
        ensureCapacity(size + 1);
        elementData[size++] = e;
        return true;
    }

    public boolean add(int index, E e) throws Exception {
        rangeCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        size ++;
        return true;
    }

    public int indexOf(E e){
        for (int i = 0; i < size; i++){
            if (e.equals(elementData[i]))
                return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) throws Exception {
        rangeCheck(index);
        return (E)elementData[index];
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E e) throws Exception {
        rangeCheck(index);
        E oldValue = (E)elementData[index];
        elementData[index] = e;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) throws Exception {
        rangeCheck(index);
        E oldValue = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData,index + 1,elementData,index, numMoved); // 将其后面元素向前移动一位
        elementData[--size] = null;  // GC
        return oldValue;
    }

    public boolean remove(E e) throws Exception {
        if (e == null){
            for (int i = 0 ;i<size ;i++){
                if (elementData == null){
                    remove(i);
                    return true;
                }
            }
        }else{
            for (int i = 0 ;i<size ;i++){
                if (e.equals(elementData[i])){
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public int size(){
        return this.size;
    }

    private void rangeCheck(int index) throws Exception {
        if (index < 0 || index > size)
            throw new Exception("out of bounds");
    }

    /**
     * 确保添加元素时有地方储存
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity) throws Exception {
        if (elementData == DEFAULT_EMPTY_ELEMENT){
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCap(minCapacity);

    }

    /**
     * 判断是否需要扩容
     * @param minCapacity
     * @throws Exception
     */
    private void ensureExplicitCap(int minCapacity) throws Exception {
        if (minCapacity - elementData.length > 0){
            grow(minCapacity);
        }
    }

    /**
     * 扩容
     * @param minCapacity
     */
    private void grow(int minCapacity) throws Exception {
        int newCap = newCapacity(minCapacity);
        this.elementData = Arrays.copyOf(this.elementData, newCap);
    }

    /**
     * 计算扩容过后数组长度
     * @param minCapacity
     * @return 数组长度
     */
    private int newCapacity(int minCapacity) throws Exception {
        int oldCap = elementData.length;
        int newCap = oldCap + (oldCap >> 1); // 扩容后数组长度为原来的1.5倍
        if (newCap - oldCap < 0){ // 如果新长度小于旧长度
            if (elementData == EMPTY_ELEMENT)
                return Math.min(minCapacity, DEFAULT_CAPACITY);
            if (minCapacity < 0)
                throw new Exception("结果溢出");
        }
        return Math.min(newCap,Integer.MAX_VALUE - 8);

    }


}
