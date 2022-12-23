package Book_Api;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklibrary.R;

import java.util.List;

public class Api_Book_Adapter extends RecyclerView.Adapter<Api_Book_Adapter.BookViewHolder> {

    private List<Book> mBooks;
    private Context mContext;
    Activity activity;

    public Api_Book_Adapter(Activity activity, Context context, List<Book> books) {
        activity = activity;
        mBooks = books;
        mContext = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.api_book_card_view_complete, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = mBooks.get(position);

        // Set the title and authors
        holder.titleTextView.setText(book.getTitle());
        holder.authorsTextView.setText(book.getAuthors().get(0));

        // Set the cover image
        Bitmap coverImageBitmap = BitmapFactory.decodeByteArray(book.getCoverImage(), 0, book.getCoverImage().length);
        holder.bookCoverImageView.setImageBitmap(coverImageBitmap);

        // Set the other book details
        holder.numberOfPagesMedianTextView.setText(String.format("Number of Pages (Median): %d", book.getNumberOfPagesMedian()));
        holder.firstPublishYearTextView.setText(String.format("First Publish Year: %d", book.getFirstPublishYear()));
        holder.isbnTextView.setText(String.format("ISBN: %s", book.getIsbn().get(0)));
        holder.lccTextView.setText(String.format("LCC: %s", book.getLcc().get(0)));
        holder.ddcTextView.setText(String.format("DDC: %s", book.getDdc().get(0)));
        holder.lccnTextView.setText(String.format("LCCN: %s", book.getLccn().get(0)));

        // holder.mainLayout.setOnClickListener(view -> {}); Hier den Add für meine Datenbank einbauen

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorsTextView;
        ImageView bookCoverImageView;
        TextView numberOfPagesMedianTextView;
        TextView firstPublishYearTextView;
        TextView isbnTextView;
        TextView lccTextView;
        TextView ddcTextView;
        TextView lccnTextView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            authorsTextView = itemView.findViewById(R.id.authors_text_view);
            bookCoverImageView = itemView.findViewById(R.id.book_cover_image_view);
            numberOfPagesMedianTextView = itemView.findViewById(R.id.number_of_pages_median_text_view);
            firstPublishYearTextView = itemView.findViewById(R.id.first_publish_year_text_view);
            isbnTextView = itemView.findViewById(R.id.isbn_text_view);
            lccTextView = itemView.findViewById(R.id.lcc_text_view);
            ddcTextView = itemView.findViewById(R.id.ddc_text_view);
            lccnTextView = itemView.findViewById(R.id.lccn_text_view);
        }
    }
}

