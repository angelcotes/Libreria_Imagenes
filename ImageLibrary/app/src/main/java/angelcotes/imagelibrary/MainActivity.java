package angelcotes.imagelibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int index = 0;
    private int degree = 0;
    private String imageTemp;
    private CharSequence[] imagesWEb = {"http://www.gettyimages.ca/gi-resources/images/Homepage/Category-Creative/UK/UK_Creative_462809583.jpg","http://descargararesparaandroid.com/wp-content/uploads/2013/10/musica-para-android.jpg", "https://www.planwallpaper.com/static/images/Winter-Tiger-Wild-Cat-Images.jpg", "http://images.panda.org/assets/images/pages/welcome/orangutan_1600x1000_279157.jpg"};
    private Picasso picasso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);


        String imageUrl = "http://descargararesparaandroid.com/wp-content/uploads/2013/10/musica-para-android.jpg";

        picasso = new Picasso.Builder(this).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        }).build();
        picasso.load(imageUrl).into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });

    }

    public void Image_touch(View view) {

        final CharSequence[] options = {"Change Image", "Cut Image", "Rotate 90°", "Save Image", "Cancel"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Opciones");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pick) {
                switch (pick){
                    case 0:
                        degree = 0;
                        imageTemp = imagesWEb[index].toString();
                        picasso.load(imagesWEb[index].toString()).into(imageView, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });
                        if (index + 1 >= imagesWEb.length)
                            index = 0;
                        else
                            index++;
                        break;
                    case 1:
                        degree = 0;
                        picasso.load(imageTemp).resize(imageView.getWidth(),imageView.getHeight()).into(imageView, new com.squareup.picasso.Callback(){

                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });
                        break;
                    case 2:
                        picasso.load(imageTemp).rotate(degree + 90).into(imageView, new com.squareup.picasso.Callback(){

                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });
                        if (degree + 90 > 359)
                            degree = 0;
                        else
                        {
                            degree = degree + 90;
                        }
                        break;
                    case 3:
                        picasso.load(imageTemp).into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });
                        break;
                    case 4:

                        break;
                    default:
                        dialog.dismiss();
                        break;
                }
            }
        });
        builder.show();
    }
}
