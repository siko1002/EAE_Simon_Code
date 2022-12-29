package Book_Api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklibrary.Book_DBHelper;
import com.example.booklibrary.MainActivity;
import com.example.booklibrary.MyDatabaseHelper;
import com.example.booklibrary.R;
import com.example.booklibrary.Update_Book;

import java.util.List;

public class Api_Book_Adapter extends RecyclerView.Adapter<Api_Book_Adapter.BookViewHolder> {

    private List<Api_Book> mApiBooks;
    private Context mContext;
    private Activity activity;

    public Api_Book_Adapter(Activity activity,Context context, List<Api_Book> apiBooks) {
        mApiBooks = apiBooks;
        mContext = context;
        activity = activity;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.api_book_card_view_complete, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Api_Book apiBook = mApiBooks.get(position);

        // Set the title and authors
        holder.titleTextView.setText(apiBook.getTitle());
        holder.authorsTextView.setText(apiBook.getAuthors().get(0));

        // Set the cover image
        if(apiBook.getCoverImage() != null) {
            Bitmap coverImageBitmap = BitmapFactory.decodeByteArray(apiBook.getCoverImage(), 0, apiBook.getCoverImage().length);
            holder.bookCoverImageView.setImageBitmap(coverImageBitmap);
        }


        // Set the other book details
        holder.numberOfPagesMedianTextView.setText(String.format("Number of Pages (Median): %d", apiBook.getNumberOfPagesMedian()));
        holder.firstPublishYearTextView.setText(String.format("First Publish Year: %d", apiBook.getFirstPublishYear()));
        holder.isbnTextView.setText(String.format("ISBN: %s", apiBook.getIsbn().get(0)));
        holder.lccTextView.setText(String.format("LCC: %s", apiBook.getLcc().get(0)));
        holder.ddcTextView.setText(String.format("DDC: %s", apiBook.getDdc().get(0)));
        holder.lccnTextView.setText(String.format("LCCN: %s", apiBook.getLccn().get(0)));

        holder.bookCoverImageView.setOnClickListener(view -> {
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(mContext);
            myDatabaseHelper.addBook(holder.titleTextView.getText().toString().trim(), holder.authorsTextView.getText().toString().trim(), holder.numberOfPagesMedianTextView.getText().toString().trim());
            Intent intent = new Intent(mContext, Update_Book.class);
            activity.startActivityForResult(intent, 1);
        });
        // holder.mainLayout.setOnClickListener(view -> {}); Hier den Add für meine Datenbank einbauen

    }

    @Override
    public int getItemCount() {
        return mApiBooks.size();
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
    public void clear() {
        int size = mApiBooks.size();
        mApiBooks.clear();
        notifyItemRangeRemoved(0, size);
    }
    public void addBook(Api_Book book){
        mApiBooks.add(book);
    }
    public void addBooks(List<Api_Book> books){
        mApiBooks.addAll(books);
    }
}

