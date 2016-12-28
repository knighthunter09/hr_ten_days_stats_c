package com.stats.day.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MMM {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		try {
			MMM_SC sa = new MMM().new MMM_SC(N);
			for (int i = 0; i < N; i++) {
				sa.add(sc.nextInt());
			}
			sa.sort();
			System.out.println(String.format("%.1f",sa.calculateAverage()));
			System.out.println(String.format("%.1f",sa.calculateMedian()));
			System.out.println(sa.calculateMode());
		} catch (CapacityNotDefinedException e) {
			e.printStackTrace();
		}
		sc.close();		
	}
	
	
	private class MMM_SC {
		private Integer capacity;
		private Integer[] arr;
		private int count;
		private float sum;
		private Map<Integer, Integer> frequencyMap = new HashMap<>();
		
		MMM_SC(Integer c) throws CapacityNotDefinedException {
			if (c != null) {
				this.capacity = c;
				this.arr = new Integer[c];
				this.count = 0;
				this.sum = 0;
			} else {
				throw new CapacityNotDefinedException();
			}
		}
		
		private void add(Integer x) {
			//Add 
			if (this.count < this.capacity) {
				this.arr[this.count++] = x;
				this.sum += x;
				if (this.frequencyMap.containsKey(x)) {
					int c = this.frequencyMap.get(x);
					this.frequencyMap.put(x, ++c);
				} else {
					this.frequencyMap.put(x, 1);
				}
			}
		}
		
		private float calculateAverage() {
			return (this.sum / this.arr.length);
		}
		
		private float calculateMedian() {			
			if (this.arr.length % 2 == 0) {
				//Even
				int x = this.arr.length / 2;
				float med = (this.arr[x-1] + this.arr[x]) / 2.0f;
				return med;
			} else {
				//Odd
				return this.arr[this.arr.length/2];
			}
		}
		
		private Integer calculateMode() {
		    List<Integer> kList = new ArrayList<>();
			kList.addAll(this.frequencyMap.keySet());
		    List<Integer> values = new ArrayList<>();
		    values.addAll(frequencyMap.values());
		    Collections.sort(values);
		    Collections.sort(kList);
		    
		    if (values.size() > 0) {
		    	if (values.size() == 1) {
		    		return kList.get(0);
		    	} else {
		    		int valR = 0;
		    		if (values.get(values.size()-2) == values.get(values.size() -1)) {
		    			valR =  values.get(values.size()-2);
		    		} else {
		    			valR = values.get(values.size() - 1);
		    		}
		    		
		    		for (int x : kList) {
		    			if (frequencyMap.get(x) == valR) {
		    				return x;
		    			}
		    		}
		    	}
		    }
			return null;
		}
		
		private void sort() {
			for (int i = 0; i < this.capacity-1; i++) {
				int min = i;
				for (int j = i+1; j < this.capacity; j++) {
					if (this.arr[j] < this.arr[i]) {
						min = j;
						int tem = this.arr[i];
						this.arr[i] = this.arr[min];
						this.arr[min] = tem;
					}
				}
			}
		}
		
	}
	
	private class CapacityNotDefinedException extends Exception {
		
		public CapacityNotDefinedException() {
			super("Initial capacity of the data structure must be defined.");
		}
	}
	
}
