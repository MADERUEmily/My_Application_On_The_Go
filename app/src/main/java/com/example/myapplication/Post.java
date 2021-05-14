package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.fragments.PostFragment;
import com.example.myapplication.models.PostAPI;
import com.example.myapplication.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/////https://www.udemy.com/course/android-development-java-android-studio-masterclass/learn/lecture/14549914#questions
public class Post extends AppCompatActivity implements View.OnClickListener {

    private static final int GALLERY_CODE = 1;
    ImageView addPhotoButton;
    ImageView backgroundImageView;
    EditText titleEditText;
    EditText contentEditText;
    TextView currentUserTextView, postDateTextView;
    Button saveButton;
    ProgressBar progressBar3;
    String user;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference myRefUser, myRef;
    //    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri imageUri = null;
    String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postDateTextView = findViewById(R.id.postDateTextView);
        titleEditText = findViewById(R.id.title);
        addPhotoButton = findViewById(R.id.photoBtn);
        contentEditText = findViewById(R.id.content);
        currentUserTextView = findViewById(R.id.postUserNameTextView);
        saveButton = findViewById(R.id.save);
        progressBar3 = findViewById(R.id.progressBar3);
        backgroundImageView = findViewById(R.id.background);

        postDateTextView.setText(date);
        addPhotoButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefUser = database.getReference().child("users").child(fAuth.getUid());
        myRef = database.getReference("posts").child(fAuth.getUid());

        myRefUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    currentUserTextView.setText(user.getFullName());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Post.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        if (User.getInstance() != null) {
//            user = User.getInstance().getUsername();
//            currentUserTextView.setText(user);
//        }
//
//        authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                user = String.valueOf((fAuth.getCurrentUser()));
//                if (user != null) {
//
//                } else {
//
//                }
//            }
//        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                if (TextUtils.isEmpty(title)) {
                    titleEditText.setError("title is required.");
                    return;
                }
                if (TextUtils.isEmpty(content)) {
                    contentEditText.setError("content is required.");
                    return;
                }
                if (imageUri == null) {
                    Toast.makeText(getApplicationContext(), "image is required.", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage(currentUserTextView.getText().toString(), title, content);
                }
                break;
            case R.id.photoBtn:
                ////get image from gallery/phone
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                //the path that the image we are going to get
                imageUri = data.getData();
                backgroundImageView.setImageURI(imageUri);///show image
            }
        }
    }

    //    @Override
//    protected void onStart() {
//        super.onStart();
//        user = String.valueOf(fAuth.getCurrentUser());
//        fAuth.addAuthStateListener(authStateListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (fAuth != null) {
//            fAuth.removeAuthStateListener(authStateListener);
//        }
//    }
////https://www.youtube.com/watch?v=IVnZn-SSTiQ   ///https://www.youtube.com/watch?v=IVnZn-SSTiQ&t=980s
    private void uploadImage(final String currentUser, final String title, final String content) {
        progressBar3.setVisibility(View.VISIBLE);
        StorageReference ref = storageReference.child(UUID.randomUUID().toString());
        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                PostAPI post = new PostAPI(currentUser, date, title, content, imageUrl,false);
                                myRef.child(UUID.randomUUID().toString()).setValue(post);
                            }
                        });

                        progressBar3.setVisibility(View.INVISIBLE);
                        Toast.makeText(Post.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Post.this, Home.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar3.setVisibility(View.INVISIBLE);
                        Toast.makeText(Post.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    }
                });

    }
}
