package com.bbusiness.app.imageapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bbusiness.app.imageapp.dummy.DummyContent;
import com.bbusiness.app.imageapp.model.Result;
import com.bbusiness.app.imageapp.model.UnSplashResponse;
import com.bbusiness.app.imageapp.rest.APIClient;
import com.bbusiness.app.imageapp.rest.GetPhotoService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemDetailFragment extends Fragment {
    List<Result> photos;
    public static final String ARG_ITEM_ID = "item_id";
    private DummyContent.DummyItem mItem;
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
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
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("API", t.getMessage(), t);
            }
        });

        return rootView;
    }

    private void populateList(View rootView) {
        if (photos.isEmpty() || photos.size() == 0)
            Toast.makeText(getActivity(), "List is empty", Toast.LENGTH_SHORT).show();
        else {
            RecyclerView container = rootView.findViewById(R.id.imageList);
            container.setLayoutManager(new LinearLayoutManager(getContext()));
            container.setAdapter(new SimpleDetailItemRecyclerViewAdapter(photos));
        }
    }

    public static class SimpleDetailItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleDetailItemRecyclerViewAdapter.ViewHolder> {
        private final List<Result> mResult;

        SimpleDetailItemRecyclerViewAdapter(List<Result> photos) {
            mResult = photos;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_details, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return mResult.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String url = mResult.get(position).getUrls().getThumb();
            Picasso.get().load(url).into(holder.mImageView);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImageView;

            ViewHolder(View view) {
                super(view);
                mImageView = view.findViewById(R.id.img);
            }
        }
    }
}
