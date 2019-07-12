package com.example.recycleviewpractice;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jointfragment.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    declaring recyclerView
    RecyclerView recyclerView;
    PersonAdaptor personAdaptor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


//        setting/initializing the recyclerView & Adaptor
        personAdaptor = new PersonAdaptor(getPersonList());
        recyclerView = findViewById(R.id.rvlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(personAdaptor);
    }

    private List<Person> getPersonList() {
        List<Person> people = new ArrayList<>();

        people.add(new Person("John", "770-555-3211", "https://upload.wikimedia.org/wikipedia/commons/0/04/John_Legend_2019_by_Glenn_Francis.jpg"));
        people.add(new Person("Mary", "770-555-3211", "https://images-na.ssl-images-amazon.com/images/I/71JnP68rXLL.jpg"));
        people.add(new Person("Blake", "770-555-3211", "https://radioimg.s3.amazonaws.com/wdsyfm/styles/delta__775x515/s3/Blake-Shelton.jpg?itok=I73PfjwO&c=906f663236323dec9db79388f99f3b15"));
        people.add(new Person("Jessica", "770-555-3211", "https://www.caa.com/sites/default/files/styles/headshot_500x500/public/2018-08/Jessica-Alba_Web.jpg?itok=uV_039qI"));
        people.add(new Person("Frank", "770-555-3211", "https://s1.r29static.com//bin/entry/4db/720x864,85/2105979/image.webp"));
        people.add(new Person("Robert", "770-555-3211", "https://specials-images.forbesimg.com/imageserve/5b44f42f31358e2c990eadbe/416x416.jpg?background=000000&cropX1=527&cropX2=2481&cropY1=73&cropY2=2027"));
        people.add(new Person("Ronnie", "770-555-3211", "https://s2.r29static.com//bin/entry/660/720x864,85/1959527/image.webp"));
        people.add(new Person("Joe", "770-555-3211", "https://www.famousbirthdays.com/faces/gatto-joe-image.jpg"));
        people.add(new Person("Riley", "770-555-3211", "https://floridagators.com/images/2018/7/24/FischerRiley_headshot_31_180724_4914.jpg?width=300"));
        people.add(new Person("George", "770-555-3211", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/George_Foreman_071516.jpg/220px-George_Foreman_071516.jpg"));
        people.add(new Person("Gary", "770-555-3211", "https://static01.nyt.com/images/2017/12/17/travel/17comedians-owen/10comedians-owen-articleLarge.jpg?quality=75&auto=webp&disable=upscale"));

        return people;
    }
}
