import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()

}

fun RecyclerView.setUpRecyclerView(
    recyclerlayoutManager: RecyclerView.LayoutManager,
    recyclerAdapter: RecyclerView.Adapter<*>
) {
    this.apply {
        layoutManager = recyclerlayoutManager
        adapter = recyclerAdapter
//        rv fields have the same size
        setHasFixedSize(true)
    }
}