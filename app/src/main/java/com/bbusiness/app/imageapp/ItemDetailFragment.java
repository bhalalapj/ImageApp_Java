package com.bbusiness.app.imageapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bbusiness.app.imageapp.dummy.DummyContent;
import com.bbusiness.app.imageapp.model.Result;
import com.bbusiness.app.imageapp.model.UnSplashResponse;
import com.bbusiness.app.imageapp.rest.APIClient;
import com.bbusiness.app.imageapp.rest.GetPhotoService;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    List<Result> photos;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.item_detail, container, false);

        //TODO: place an api call here to get list of images from the API

        GetPhotoService photoService = APIClient.getRetrofitClient().create(GetPhotoService.class);
        Call<UnSplashResponse> call = photoService.GetPhotos(mItem.id, APIClient.getAccessKey());

        call.enqueue(new Callback<UnSplashResponse>() {
            @Override
            public void onResponse(Call<UnSplashResponse> call, Response<UnSplashResponse> response) {
                if (response.isSuccessful()) {
                    photos = response.body().getResults();
                    populateList(rootView);
                }
//                else
//                    Log.e(TAG, response.errorBody().toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("API", t.getMessage(), t);
            }
        });

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }

        return rootView;
    }

    private void populateList(View rootView) {
        if (photos.isEmpty() || photos.size() == 0)
            Toast.makeText(getActivity(), "List is empty", Toast.LENGTH_SHORT).show();
        else {
//            ((ImageView) rootView.findViewById(R.id.imgView)).setImageURI(Uri.parse(photos.get(0).getUrls().getThumb()));

            for(Result r : photos)
            {
                Log.d("Photo",r.getUrls().getThumb());
            }
        }
    }
}
