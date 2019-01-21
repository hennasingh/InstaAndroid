package com.coder.instaandroid.profile;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coder.instaandroid.R;
import com.coder.instaandroid.model.Comments;
import com.coder.instaandroid.model.Images;
import com.coder.instaandroid.model.Likes;
import com.coder.instaandroid.model.MediaDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ImageViewHolder> {

    private List<MediaDetails> mMediaDetailsList;

    public MediaAdapter() {
    }

    public void setImages(List<MediaDetails> media){
        mMediaDetailsList = media;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_view,viewGroup,
                false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int position) {

        MediaDetails mediaDetails = mMediaDetailsList.get(position);
        imageViewHolder.bindTo(mediaDetails);
    }

    @Override
    public int getItemCount() {
        return mMediaDetailsList!=null? mMediaDetailsList.size():0;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_likes)
        TextView mLikesTV;

        @BindView(R.id.tv_comments)
        TextView mCommentsTV;

        @BindView(R.id.iv_image)
        ImageView mMedia;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindTo(MediaDetails mediaDetails) {

            Likes likes = mediaDetails.getLikes();
            mLikesTV.setText(String.valueOf(likes.getCount()));

            Comments comments = mediaDetails.getComments();
            mCommentsTV.setText(String.valueOf(comments.getCount()));

            Images images = mediaDetails.getImages();

            Picasso.get()
                    .load(images.getStandardResolution().getUrl())
                    .error(R.drawable.image_not_found)
                    .placeholder(R.drawable.loading)
                    .into(mMedia);

        }
    }
}
