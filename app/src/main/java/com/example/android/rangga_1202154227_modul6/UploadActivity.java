package com.example.android.rangga_1202154227_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity {
    private final int SELECT_PICTURE = 1;
    String idCurrentUser;
    StorageReference store;
    Uri imageUri;
    DatabaseReference dataref;
    ImageView gambardiupload;
    EditText title;
    EditText caption;
    ProgressDialog dlg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        idCurrentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        dlg = new ProgressDialog(this);
        gambardiupload = findViewById(R.id.gambardiupload);
        title = findViewById(R.id.bikinposttitle); caption = findViewById(R.id.bikinpostcaption);
        store = FirebaseStorage.getInstance().getReference();
        dataref = FirebaseDatabase.getInstance().getReference().child("post");
    }


    public void setfoto(View view) {
        Intent pickImage = new Intent(Intent.ACTION_PICK);
        pickImage.setType("image/*");


        startActivityForResult(pickImage, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==RESULT_OK){
            if(requestCode==SELECT_PICTURE){


                imageUri = data.getData();
                try {

                    InputStream stream = getContentResolver().openInputStream(imageUri);
                    Bitmap gambar = BitmapFactory.decodeStream(stream);
                    gambardiupload.setImageBitmap(gambar);
                } catch (FileNotFoundException e) {
                    Log.w("FileNotFoundException", e.getMessage());
                    Toast.makeText(this, "Unable to load image", Toast.LENGTH_SHORT).show();
                }
            }


        }else{
            Toast.makeText(this, "Picture not selected", Toast.LENGTH_SHORT).show();
        }
    }


    public void uploadingambar(View view) {


        dlg.setMessage("Uploading!"); dlg.show();


        StorageReference filepath = store.child(title.getText().toString());


        gambardiupload.setDrawingCacheEnabled(true);
        gambardiupload.buildDrawingCache();

        Bitmap bitmap = gambardiupload.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask task = filepath.putBytes(data);


        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String image = taskSnapshot.getDownloadUrl().toString();
                Database user = new Database(caption.getText().toString(), image, title.getText().toString(), idCurrentUser);


                dataref.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {

                    
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UploadActivity.this, "Post uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    //Ketika menyimpan data gagal
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                dlg.dismiss();
            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadActivity.this, "Upload Failure!", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });

    }
}
