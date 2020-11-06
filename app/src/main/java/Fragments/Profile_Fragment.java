package Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.semester_project.smd_project.R;

import java.awt.font.TextAttribute;
import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.getSystemService;

public class Profile_Fragment extends Fragment
{
    TextView editprofile_, cancelprofileeditbtn;
    Button savebtn;
    EditText user, email, phone, address, country;
    CircleImageView coverphoto, profilepic, cicularprofilepicture;
    static final int PICK_IMG = 1, PICK_IMG1 = 2;
    Uri photo;
    Bitmap xyz;
    LinearLayout coverpic;

    public Profile_Fragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.profile_fragment, container, false);
        getActivity().setTitle("Profile");

        editprofile_ = root.findViewById(R.id.editprofile);
        cancelprofileeditbtn = root.findViewById(R.id.cancelprofileedit);
        savebtn = root.findViewById(R.id.saveprofilebtn);
        user = root.findViewById(R.id.user);
        email = root.findViewById(R.id.email);
        phone = root.findViewById(R.id.phonenum);
        address = root.findViewById(R.id.address);
        country = root.findViewById(R.id.country);
        coverphoto = root.findViewById(R.id.editcoverphoto);
        profilepic = root.findViewById(R.id.profilepictureicon);
        coverpic = root.findViewById(R.id.l1);
        cicularprofilepicture = root.findViewById(R.id.pic);

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Picture"), PICK_IMG1);
            }
        });

        coverphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Picture"), PICK_IMG);
            }
        });

        editprofile_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editprofile_.getText().equals("Edit"))
                {
                    visibility_gone_save_cover_profile (savebtn, profilepic, coverphoto, View.VISIBLE);
                    set_edittext_disable(user, true);
                    set_edittext_disable(email, true);
                    set_edittext_disable(phone, true);
                    set_edittext_disable(address, true);
                    set_edittext_disable(country, true);
                    editprofile_.setText("Cancel");
                    editprofile_.setTextSize(16);
                }
                else if(editprofile_.getText().equals("Cancel"))
                {
                    editprofile_.setText("Edit");
                    editprofile_.setTextSize(15);
                    visibility_gone_save_cover_profile (savebtn, profilepic, coverphoto, View.GONE);
                    set_edittext_disable(user, false);
                    set_edittext_disable(email, false);
                    set_edittext_disable(phone, false);
                    set_edittext_disable(address, false);
                    set_edittext_disable(country, false);
                }
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editprofile_.setText("Edit");
                editprofile_.setTextSize(15);
                visibility_gone_save_cover_profile (savebtn, profilepic, coverphoto, View.GONE);
                set_edittext_disable(user, false);
                set_edittext_disable(email, false);
                set_edittext_disable(phone, false);
                set_edittext_disable(address, false);
                set_edittext_disable(country, false);
            }
        });

        cancelprofileeditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editprofile_.setVisibility(View.VISIBLE);
                cancelprofileeditbtn.setVisibility(View.GONE);
                visibility_gone_save_cover_profile (savebtn, profilepic, coverphoto, View.GONE);
                set_edittext_disable(user, false);
                set_edittext_disable(email, false);
                set_edittext_disable(phone, false);
                set_edittext_disable(address, false);
                set_edittext_disable(country, false);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMG1 && resultCode == RESULT_OK)
        {
            photo  = data.getData();
            try
            {
                ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), photo);
                xyz = ImageDecoder.decodeBitmap(source);
                //BitmapDrawable background = new BitmapDrawable(xyz);
                cicularprofilepicture.setImageBitmap(xyz);
            }
            catch(Exception e)
            {
                e.getStackTrace();
            }
        }
        else if (requestCode == PICK_IMG && resultCode == RESULT_OK)
        {
            photo  = data.getData();
            try
            {
                ImageDecoder.Source source = ImageDecoder.createSource(getContext().getContentResolver(), photo);
                xyz = ImageDecoder.decodeBitmap(source);
                BitmapDrawable background = new BitmapDrawable(xyz);
                coverpic.setBackgroundDrawable(background);
            }
            catch(Exception e)
            {
                e.getStackTrace();
            }

        }
    }

    private void set_edittext_disable(EditText t, boolean bool)
    {
        t.setFocusable(bool);
        t.setEnabled(bool);
    }

    private  void visibility_gone_save_cover_profile (Button s, CircleImageView m1, CircleImageView m2, int v1)
    {
        s.setVisibility(v1);
        m1.setVisibility(v1);
        m2.setVisibility(v1);
    }
}
