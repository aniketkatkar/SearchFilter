package com.aniketkatkar.SearchFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.content.Context;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    ArrayList<Subjects> SubjectList = new ArrayList<Subjects>();
    String HttpURL = "http://example.com/SubjectFullForm.php";
    ListViewAdapter listViewAdapter;
    ProgressBar progressBar ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView1);

        editText = (EditText) findViewById(R.id.edittext1);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Subjects ListViewClickData = (Subjects) parent.getItemAtPosition(position);

                Toast.makeText(MainActivity.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence stringVar, int start, int before, int count) {

                listViewAdapter.getFilter().filter(stringVar.toString());
            }
        });

        new ParseJSonDataClass(this).execute();

    }

    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpParseClass httpParseClass = new HttpParseClass(HttpURL);

            try {
                httpParseClass.ExecutePostRequest();

                if (httpParseClass.getResponseCode() == 200) {

                    FinalJSonResult = httpParseClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);

                            JSONObject jsonObject;

                            Subjects subjects;

                            SubjectList = new ArrayList<Subjects>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("name").toString();

                                String tempFullForm = jsonObject.getString("information").toString();

                                subjects = new Subjects(tempName, tempFullForm);

                                SubjectList.add(subjects);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpParseClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBar.setVisibility(View.INVISIBLE);
            listViewAdapter = new ListViewAdapter(MainActivity.this, R.layout.listview_items_layout, SubjectList);
            listView.setAdapter(listViewAdapter);
        }
    }

}