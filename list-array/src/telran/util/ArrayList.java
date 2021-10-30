package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

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
		boolean res = false;
		if (index == size) {
			add(element);
			res = true;
			
		} else if(isValidIndex(index)) {
			res = true;
			if (size == array.length) {
				allocate();
			}
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = element;
			size++;
		}
		return res;
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
		T res = null;
		if (isValidIndex(index)) {
			res = array[index];
			size--;
			System.arraycopy(array, index + 1, array, index, size - index);
			//FIXME regarding setting null
		}
		
		return res;
	}
	@Override
	public boolean contains(T pattern) {
		// V.R. The implementation has to be in file List.java.
		boolean res = false;
		for(int i = 0; i < size; i++) {
			if (array[i].equals(pattern)) {
				res = true;
				break;
			}
		}
		return res;
	}
	@Override
	public int indexOf(T pattern) {
		// TODO Auto-generated method stub
		// V.R. The implementation has to be in file List.java.
		for ( int i=0; i<size; i++) 
			if (array[i].equals(pattern)) 
				return(i);
		return (-1);
	}
	@Override
	public int lastIndexOf(T pattern) {
		// TODO Auto-generated method stub
		// V.R. The implementation has to be in file List.java.
		for ( int i=size-1; i>=0; i--)
			if (array[i].equals(pattern)) 
				return(i);
		return (-1);
	}
	@Override
	public boolean contains(Predicate<T> predicate) {
		// V.R. The implementation has to be in file List.java.
		boolean res = false;
		for(int i = 0; i < size; i++) {
			if (predicate.test(array[i])) {
				res = true;
				break;
			}
		}
		return res;
	}
	@Override
	public int indexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		for ( int i=0; i<size; i++) 
			if (predicate.test(array[i])) return(i);
		return (-1);
	}
	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		for ( int i=size-1; i>=0; i--) 
			if (predicate.test(array[i])) 
				return(i);
		return (-1);
	}
	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		int pSize=size;
 		for(int i=size-1; i>=0; i--) 
 			if (predicate.test(array[i]))remove(i);
		return pSize >size;
	}

}
