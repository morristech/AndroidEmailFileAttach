package snappii.snappiitest.paging;

import java.util.ArrayList;

public interface PagerAdapter<T> {
    void addMoreItems(ArrayList<T> items);
}
