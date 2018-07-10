package de.ph1b.audiobook.features.bookOverview.list

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.ViewGroup
import de.ph1b.audiobook.data.Book
import java.util.UUID

class BookOverviewAdapter(
  private val bookClicked: (Book, BookOverviewClick) -> Unit
) : ListAdapter<Book, BookOverviewHolder>(BookOverviewDiff()) {

  init {
    setHasStableIds(true)
  }

  fun reloadBookCover(bookId: UUID) {
    for (i in 0 until itemCount) {
      if (getItem(i).id == bookId) {
        notifyItemChanged(i)
        break
      }
    }
  }

  override fun getItemId(position: Int) = getItem(position).id.mostSignificantBits

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    BookOverviewHolder(parent, bookClicked)

  override fun onBindViewHolder(holder: BookOverviewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}