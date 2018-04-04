package com.example.guest.app_foundation.util;

/**
 * Created by Guest on 4/4/18.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
