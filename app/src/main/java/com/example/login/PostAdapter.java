package com.example.login;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

public class PostAdapter extends ArrayAdapter<post> {

   private Activity activity ;
   GestureDetector detector;
   ImageView Profile_image , post_img  , download_img;
    TextView account_name , post_time ,  post_header , post_description ,number_of_ratting_post ,number_of_favorite_post;
    private LikeButton likeButton;
    private boolean mode = false;
    private int selectedPosition;
    View smileView;
    LinearLayout love, rating;
    AlertDialog.Builder builder;

    public PostAdapter(Activity context, List<post> posts) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, posts);
        activity = context;
    }

    public void updateSelectedPosition (int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        smileView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.post, parent, false);
        }


        // Get the {@link AndroidFlavor} object located at this position in the list
        post currentPost = getItem(position);

        Profile_image = listItemView.findViewById(R.id.profile_image);
        account_name= listItemView.findViewById(R.id.account_name);
        post_time = listItemView.findViewById(R.id.time_post);
         post_header = listItemView.findViewById(R.id.post_header);
         post_description = listItemView.findViewById(R.id.post_description);
         number_of_ratting_post = listItemView.findViewById(R.id.number_of_ratting_post);
          number_of_favorite_post = listItemView.findViewById(R.id.number_of_favorite_post);
         post_img = listItemView.findViewById(R.id.post_img);
          download_img = listItemView.findViewById(R.id.download_img);

        likeButton = listItemView.findViewById(R.id.love);
        love = listItemView.findViewById(R.id.favorite_icon);
        rating = listItemView.findViewById(R.id.rating_icon);

        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        Profile_image.setImageResource(currentPost.getProfile_image());
        account_name.setText(currentPost.getAccount_name());
        post_time.setText(currentPost.getPost_time());
        post_header.setText(currentPost.getPost_header());
        post_description.setText(currentPost.getPost_description());
        number_of_ratting_post.setText("+"+currentPost.getNumber_of_ratting_post());
        number_of_favorite_post.setText(""+currentPost.getmNumber_of_favorite_post());
        post_img.setImageResource(currentPost.getPost_img());

            // change the image view to orange here


            love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        likeButton.setVisibility(View.VISIBLE);
                        mode = !mode;
                        likeButton.setLiked(mode);
                        number_of_favorite_post.setCompoundDrawablesWithIntrinsicBounds(mode ? R.drawable.love24: R.drawable.nolove24, 0, 0, 0);

                        Toast.makeText(activity, "Adding to favorite.... , position =  " + position, Toast.LENGTH_SHORT).show();

                }
            });


            post_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    download_img.animate().scaleX(1).scaleY(1).setDuration(800).start();
                    Toast.makeText(activity, "Downloading pdf.... , position =  "+ position , Toast.LENGTH_SHORT).show();

                }
            });


            rating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, "rating, position =  "+ position, Toast.LENGTH_SHORT).show();



                    builder = new AlertDialog.Builder(activity);

                    if(smileView == null) {
                        smileView = LayoutInflater.from(getContext()).inflate(R.layout.smiley_rating, null);
                    }

                    SmileRating smileRating = (SmileRating) view.findViewById(R.id.smile_rating);
             /*       smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
                        @Override
                        public void onRatingSelected(int level, boolean reselected) {
                            Toast.makeText(activity, "level :  " + level, Toast.LENGTH_SHORT).show();
                        }

                    });
*/
           builder.setView(smileView);
           AlertDialog dialog = builder.create();
           dialog.show();

                }
            });


            post_img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    likeButton.setVisibility(View.VISIBLE);
                    mode = !mode;
                    likeButton.setLiked(mode);
                    number_of_favorite_post.setCompoundDrawablesWithIntrinsicBounds(mode ? R.drawable.love24 : R.drawable.nolove24, 0, 0, 0);

                    Toast.makeText(activity, "Adding to favorite...., position =  "+ position, Toast.LENGTH_SHORT).show();

                    return true;
                }
            });

            likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    Toast.makeText(activity, "like, position =  "+ position, Toast.LENGTH_SHORT).show();
                    number_of_favorite_post.setCompoundDrawablesWithIntrinsicBounds(R.drawable.love24, 0, 0, 0);
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    Toast.makeText(activity, "unlike, position =  "+ position , Toast.LENGTH_SHORT).show();
                    number_of_favorite_post.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nolove24, 0, 0, 0);

                }
            });

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
