import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()

}

fun RecyclerView.setUpRecyclerView(
    recyclerLayoutManager: RecyclerView.LayoutManager,
    recyclerAdapter: RecyclerView.Adapter<*>
) {
    this.apply {
        layoutManager = recyclerLayoutManager
        adapter = recyclerAdapter
//        rv fields have the same size
        setHasFixedSize(true)
    }
}

fun TextView.setDynamicColorOnTextView(color: Int) {
    // for changing drawable item color we use position index if it be on:
//  start- left =0 , top=1 , end-right =2 , bottom=3
    this.compoundDrawables[1].setTint(
        ContextCompat.getColor(
            context,
            color
        )
    )
    this.setTextColor(
        ContextCompat.getColor(
            context,
            color
        )
    )
}