package club.dagomys.src;

import java.util.Comparator;
import java.util.Objects;

public class UniListQuickSort<T> {

    private final UniList<T> uniList;

    private final Comparator<T> comparator;

    public UniListQuickSort(UniList<T> uniList, Comparator<T> comparator) {
        if (!uniList.isEmpty()) {
            this.uniList = uniList;
            this.comparator = comparator;
        } else throw new NullPointerException("Передан пустой список");
    }

    public void quickSort() {
        int startIndex = 0;
        int endIndex = uniList.getSize() - 1;
        partition(uniList, startIndex, endIndex);
    }

    private void partition(UniList<T> uniList, int startIndex, int endIndex) {
        T mid = (T) uniList.get((startIndex + endIndex) / 2);
        int left = startIndex;
        int right = endIndex;
        while (left <= right) {
            while (comparator.compare(uniList.get(left), mid) < 0) {
                left++;
            }
            while (comparator.compare(uniList.get(right), mid) > 0) {
                right--;
            }


            if (left <= right) {
                T temp = uniList.get(left);
                uniList.set(uniList.get(right), left);
                uniList.set(temp, right);
                left++;
                right--;
            }

        }

        if (startIndex < right)
            partition(uniList, startIndex, right);
        if (endIndex > left)
            partition(uniList, left, endIndex);


    }

}
