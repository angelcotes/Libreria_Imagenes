package angelcotes.imagelibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    private String fileName = null;
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
    }

    public void Image_touch(View view) {

        final CharSequence[] options = {"Change Image", "Cut Image", "Rotate 90°", "Save Image"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Opciones");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pick) {
                switch (pick) {
                    case 0:
                        degree = 0;
                        imageTemp = imagesWEb[index].toString();
                        Picasso.with(MainActivity.this).load(imageTemp).into(imageView);
                        if (index + 1 >= imagesWEb.length)
                            index = 0;
                        else
                            index++;
                        break;
                    case 1:
                        degree = 0;
                        Picasso.with(MainActivity.this).load(imageTemp).resize(imageView.getWidth()/2, imageView.getHeight()/2).into(imageView);
                        break;
                    case 2:
                        Picasso.with(MainActivity.this).load(imageTemp).rotate(degree + 90).into(imageView);
                        if (degree + 90 > 359)
                            degree = 0;
                        else {
                            degree = degree + 90;
                        }
                        break;
                    case 3:
                        File file = new File(Environment.getExternalStorageDirectory(),"MyPhoto/data/com.usd.pop");
                        Picasso.with(MainActivity.this).load(imageTemp).into((Target) file);
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
