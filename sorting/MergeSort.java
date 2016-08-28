package ctci;

// O(n*log(n))
public class MergeSort {
	public static void main(String[] args) {
		int[] arr = { 4, 6, 5, 1, 3, 8 };

		mergeSort(arr, 0, arr.length - 1);

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	// end is inclusive
	public static void mergeSort(int[] arr, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;

			// Divide
			mergeSort(arr, start, mid);
			mergeSort(arr, mid + 1, end);

			// Conquer
			merge(arr, start, mid, end);
		}
	}

	public static void merge(int[] arr, int start, int mid, int end) {
		// Initialize left and right array and copy contents from arr
		int[] left = new int[mid - start + 1];
		for (int i = 0; i < left.length; i++) {
			left[i] = arr[start + i];
		}
		int[] right = new int[end - mid];
		for (int i = 0; i < right.length; i++) {
			right[i] = arr[mid + 1 + i];
		}

		// indexes
		int i = 0;
		int j = 0;
		int k = start;

		while (k <= end) {
			if (i < left.length && j < right.length) {
				if (left[i] <= right[j]) {
					arr[k++] = left[i++];
				} else {
					arr[k++] = right[j++];
				}
			} else {
				// when either left or right is exhausted, finish by copying the
				// rest of either right or left array into arr
				if (i == left.length) {
					arr[k++] = right[j++];
				} else if (j == right.length) {
					arr[k++] = left[i++];
				}
			}
		}
	}
}
