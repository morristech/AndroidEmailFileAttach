package snappii.snappiitest.misc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;

public class Helpers {

    public static void call(Context context, String phone) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            context.startActivity(intent);
        } catch (Throwable ignored) {

        }
    }

    public static void sendEmail(Context view, String email, String avatar) {
        if (avatar != null) {
            Picasso.with(view).load(avatar).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("vnd.android.cursor.dir/email");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");

                    try {
                        Uri imageUri = getImageUri(view, bitmap);
                        emailIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                        view.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(view, "Нет приложений для отправки почты ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Toast.makeText(view, "Ошибка при отправке почты", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    Toast.makeText(view, "Ошибка при отправке почты", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            try {
                view.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(view, "Нет приложений для отправки почты ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
            return Uri.parse(path);
        } catch (Throwable ignored) {
            return null;
        }
    }
}
