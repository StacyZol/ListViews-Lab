package ly.generalassemb.drewmahrt.bookshelf;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BaseAdapter mBookAdapter;
    ListView mListView;
    List<Book> mBookList = new ArrayList<Book>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use helper method to add books to the list
       mBookList = generateBooks();

        mListView = (ListView)findViewById(R.id.listview);

        // Instantiate BaseAdapter
        //Below is a partially complete example for one Adapter
        mBookAdapter = new BaseAdapter() {
            mBookAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_2, android.R.id.text1, mBookList)

            {


                @Override
                public int getCount () {
                return mBookList.size();
            }

                @Override
                public Object getItem ( int position){
                return mBookList.get(position);
            }

                @Override
                public long getItemId ( int position){
                return position;
            }

                @Override
                public View getView ( int position, View convertView, ViewGroup parent){
                // Update the view
                View view = convertView;
                if (view == null) {
                    LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(android.R.layout.simple_list_item_1, null);
                }
                final TextView title = (TextView) view.findViewById(android.R.id.text1);
                final TextView author = (TextView) view.findViewById(android.R.id.text2);

                title.setText(mBookList.get(position).getTitle());
                author.setText(mBookList.get(position).getAuthor());

                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        title.setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
                        author.setTextColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
                    }
                });
                return view;
            }
            }

        };

        // Set the ListView's adapter
        mListView.setAdapter(mBookAdapter);
    }

    //Method to generate a list of Books
    private List<Book> generateBooks(){
        List<Book> books = new ArrayList<>();

        books.add(new Book("Apples Book","Brad"));
        books.add(new Book("Cats Book","Ryan"));
        books.add(new Book("Eagles Book","Kate"));
        books.add(new Book("Strawberries Cathy","Brad"));
        books.add(new Book("Dogs Book","Tom"));
        books.add(new Book("Ants Book","Zane"));

        return books;
    }
}
