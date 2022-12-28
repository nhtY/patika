import java.util.Arrays;
import java.util.Collections;

public class MyList<T> {
	private int  capacity;
	private int size;
	private T[] dataArr;
	
	public MyList() {
		this.size = 0;
		this.capacity = 10;
		this.dataArr = (T[]) new Object[this.capacity];
	}
	
	public MyList(int capacity) {
		this.size = 0;
		if(capacity>0) {
			this.capacity = capacity;
			this.dataArr = (T[]) new Object[this.capacity];
		}else if(capacity == 0) {
			this.dataArr = (T[]) new Object[0];
		}else {
			throw new IllegalArgumentException("Illegal capacity: "
												+ capacity);
		}
	}
	
	
	public int getCapacity() {
		return this.capacity;
	}

	
	public int size() {
		return this.size;
	}
	
	public void add(T data) {
		if(this.size != this.capacity) {
			this.dataArr[this.size] = data;
			this.size ++;
		}else {
			this.expandCapacity();
			this.add(data);
		}
	}

	private void expandCapacity() {
		this.capacity *= 2;
		T[] tempArr = (T[]) new Object[this.capacity];
		
		for(int i=0; i<this.size; i++) {
			tempArr[i] = this.dataArr[i];
		}
		this.dataArr = tempArr;
	}
	
	public T get(int index) {
		return this.dataArr[index];
	}
	
	public T remove(int index) {
		if(0<=index && index<this.size) {
			T oldValue = this.dataArr[index];
			this.shift(index);
			this.size --;
			return oldValue;
		}
		
		return null;
	}

	private void shift(int index) {
		for(int i=index; i<this.size; i++) {
			if(i==this.size-1) {
				this.dataArr[i] = null;
			}
			this.dataArr[i] = this.dataArr[i+1];
		}
		
	}
	
	public T set(int index, T data) {
		if(0<=index && index<this.size) {
			this.dataArr[index] = data;
			return this.dataArr[index];
		}
		return null;	
	}
	
	public String toString() {
		if(this.size == 0) {
			return "[]";
		}
		
		String result = "[";
		for(int i=0; i<this.size; i++) {
			T element = this.dataArr[i];
			if(i==this.size-1) {
				result += String.valueOf(element);
			}else {
				result += String.valueOf(element) + ", ";
			}
		}
		result += "]";
		return result;
	}
	
	public int indexOf(T data) {
		for(int i=0; i<this.size; i++) {
			if(data == this.dataArr[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public int lastIndexOf(T data) {
		for(int i=this.size-1; 0<=i; i--) {
			if(data == this.dataArr[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return this.size==0;
	}
	
	public T[] toArray() {
		return this.dataArr;
	}
	
	public void clear() {
		for(T element:this.dataArr) {
			element = null;
		}
		this.size = 0;
	}
	
	public MyList<T> subList(int start, int finish){
		boolean isStartInRange = 0<=start && start<this.size() && start<=finish;
		boolean isFinishInRange = 0<=finish && finish<this.size;
		
		MyList<T> newList = new MyList<>();
		if(isStartInRange && isFinishInRange) {
			for(int i=start; i<=finish; i++) {
				newList.add(this.dataArr[i]);
			}
		}
		return newList;
	}
	
	public boolean contains(T data) {
		for(T element:this.dataArr) {
			if(element == data) {
				return true;
			}
		}
		return false;
	}
}
