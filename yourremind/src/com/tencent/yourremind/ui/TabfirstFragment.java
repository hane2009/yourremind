package com.tencent.yourremind.ui;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.tencent.yourremind.R;

/**
 * Created by sidyang on 14-4-9.
 */
public class TabFirstFragment extends SherlockFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private View contentView;

    private ListView listView;

    private SimpleCursorAdapter mAdapter;

    private Context context;

    static final String[] PROJECTION = new String[] {ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME};
    static final String SELECTION = "((" +
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView =  super.onCreateView(inflater, container, savedInstanceState);

        context = container.getContext();
        if(contentView == null ){
            contentView = inflater.inflate(R.layout.tab1,container,false);
        }

        listView = (ListView)contentView.findViewById(R.id.add_import_people_list);

        String[] fromColumns = {ContactsContract.Data.DISPLAY_NAME};
        int[] toViews = {R.id.people};

        mAdapter = new SimpleCursorAdapter(container.getContext(),
                R.layout.import_people_list, null,
                fromColumns, toViews, 0);

        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tx = (TextView)view.findViewById(R.id.people);
                final String name = tx.getText().toString();
                new AlertDialog.Builder(context).setMessage("是否添加重要用户？")
                        .setPositiveButton("是",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Intent intent = new Intent(getActivity(),AddImportPeopleActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("name",name);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton("否",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
            }
        });

        getLoaderManager().initLoader(0, null, this);
        return contentView;
    }

    // Called when a new Loader needs to be created
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(context, ContactsContract.Data.CONTENT_URI,
                PROJECTION, SELECTION, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }


}
