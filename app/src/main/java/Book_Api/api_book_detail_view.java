package Book_Api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booklibrary.R;

public class api_book_detail_view extends AppCompatActivity {

    // Declare UI elements as member variables
    private TextView mTitleTextView;
    private TextView mAuthorsTextView;
    private ImageView mBookCoverImageView;
    private TextView mNumberOfPagesMedianTextView;
    private TextView mFirstPublishYearTextView;
    private TextView mIsbnTextView;
    private TextView mLccTextView;
    private TextView mDdcTextView;
    private TextView mLccnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_book_card_view_complete);

        // Initialize UI elements
        mTitleTextView = findViewById(R.id.title_text_view);
        mAuthorsTextView = findViewById(R.id.authors_text_view);
        mBookCoverImageView = findViewById(R.id.book_cover_image_view);
        mNumberOfPagesMedianTextView = findViewById(R.id.number_of_pages_median_text_view);
        mFirstPublishYearTextView = findViewById(R.id.first_publish_year_text_view);
        mIsbnTextView = findViewById(R.id.isbn_text_view);

        // Retrieve book data from the intent or savedInstanceState
        Api_Book apiBook = (Api_Book) getIntent().getSerializableExtra("book");
        if (apiBook == null && savedInstanceState != null) {
            apiBook = (Api_Book) savedInstanceState.getSerializable("book");
        }

        // Update the UI with the book data
        if (apiBook != null) {
            mTitleTextView.setText(apiBook.getTitle());
            mAuthorsTextView.setText(apiBook.getAuthors().get(0));
            mNumberOfPagesMedianTextView.setText(apiBook.getNumberOfPagesMedian());
            mFirstPublishYearTextView.setText(apiBook.getFirstPublishYear());
            mIsbnTextView.setText(apiBook.getIsbn().get(0));
        }
        if (apiBook.getCoverImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(apiBook.getCoverImage(), 0, apiBook.getCoverImage().length);
            mBookCoverImageView.setImageBitmap(bitmap);
        }
    }
}