package snappii.snappiitest.paging;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class PagerScroller extends RecyclerView.OnScrollListener {
    private final static int visibleThreshold = 5;
    private final LinearLayoutManager layoutManager;
    private boolean loading = true;
    private int currentPage;
    private Pager loader;

    public PagerScroller(LinearLayoutManager layoutManager, Pager pager, int start) {
        this.layoutManager = layoutManager;
        this.currentPage = start;
        this.loading = true;
        this.loader = pager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            loading = true;
            start();
        }
    }

    public void start() {
        loading = true;
        loader.loadMore(currentPage);
    }

    public void add(List data) {
        if (isNotEmptyNull(data)) {
            currentPage++;
        }
        loading = false;
    }

    public void end() {
        loading = true;
    }

    public void proceed() {
        loading = false;
    }

    private static boolean isNotEmptyNull(List list) {
        return list != null && !list.isEmpty();
    }
}
