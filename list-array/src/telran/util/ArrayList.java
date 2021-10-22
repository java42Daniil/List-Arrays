package telran.util;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size = 0; 
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	@Override
	public void add(T element) {
		if (size == array.length) {
			//size is capacity
			allocate();
		}
		array[size++] = element;
		
		
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
		
	}
	@Override
	public boolean add(int index, T element) {
		if (index < 0 || index > size) {
			return false;
		}
		if (size == array.length) {
			add(element);
			return true;
		}
		//[YG] major bug, You don't check size for the allocation need
	System.arraycopy(array, index, array, index+1, size-index);
		size++;
		array[index]=element;
		
		return true;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public T get(int index) {
		
		return isValidIndex(index) ? array[index] : null;
	}

	private boolean isValidIndex(int index) {
		
		return index >= 0 && index < size;
	}
	@Override
	public T remove(int index) {
		T res = get(index);
		//[YG] cosmetic bug, the condition of the following "if" is never true, so 69 - 71 will never run
		if(res == null && index == size-1) {
			array[index] = null;
			size--;
			return res;
		}
		//[YG] better to apply arraycopy method. Your code has major bug for case size == length (the last iteration: array[i+1] out of array, as i+1 == array.length)
		for(int i=index; i<size;i++)
		{
		array[i]=array[i+1];
		}
		size--;
		return res;
	}

}
