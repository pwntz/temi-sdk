package truerobotics.robotsdkplatform;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TesT extends Activity {
    MainSDK mainSDK;
    EditText etLocation;
    Button btLoadlocation,btSaveLocation;
    ListView lvLocation;
    List<String> location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etLocation = findViewById(R.id.etLocation);
        btLoadlocation = findViewById(R.id.btloadlocation);
        btSaveLocation = findViewById(R.id.btsavelocation);
        lvLocation = findViewById(R.id.lvlocation);

        mainSDK = new MainSDK(getApplicationContext(),"");
        location = new ArrayList<>();


        btSaveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etLocation.getText().toString())){
                    mainSDK.setLocation(etLocation.getText().toString());
                    etLocation.setText("");
                }
            }
        });

        btLoadlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = mainSDK.getLocation();
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,location);
                lvLocation.setAdapter(dataAdapter);
            }
        });

        lvLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mainSDK.moveRobot(location.get(position));
            }
        });




    }
}
