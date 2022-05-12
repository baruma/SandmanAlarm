package com.example.sandmanalarm.alarmList

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView



class SwipeHelperCallback(val adapter : ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = 0
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags( dragFlags, swipeFlags )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(source.adapterPosition,
            target.adapterPosition)
        return true    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }

}

interface ItemTouchHelperAdapter {
    /**
     * Called when an item has been dragged far enough to trigger a move. This is called every time
     * an item is shifted, and **not** at the end of a "drop" event.<br></br>
     * <br></br>
     * Implementations should call {link RecyclerView.Adapter#notifyItemMoved(int, int)} after
     * adjusting the underlying data to reflect this move.
     *
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then resolved position of the moved item.
     * @return True if the item was moved to the new adapter position.
     *
     *
     * see RecyclerView#getAdapterPositionFor(RecyclerView.ViewHolder)
     * see RecyclerView.ViewHolder#getAdapterPosition()
     */
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean


    /**
     * Called when an item has been dismissed by a swipe.<br></br>
     * <br></br>
     * Implementations should call {link RecyclerView.Adapter#notifyItemRemoved(int)} after
     * adjusting the underlying data to reflect this removal.
     *
     * @param position The position of the item dismissed.
     *
     *
     * see RecyclerView#getAdapterPositionFor(RecyclerView.ViewHolder)
     * see RecyclerView.ViewHolder#getAdapterPosition()
     */
    fun onItemDismiss(position: Int)
}