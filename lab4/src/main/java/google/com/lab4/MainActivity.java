package google.com.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;
import google.com.lab4.show_view.ShowActivity;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private Date lastDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        saveDefaultUsers();
        realm = Realm.getDefaultInstance();
    }

    private void saveDefaultUsers() {
        DataProvider.deleteAllUsers();
        DataProvider.saveNewUser(new GroupMate("Алексей", "Позняков", "Иванович"));
        DataProvider.saveNewUser(new GroupMate("Иван", "Прыгун", "Васильевич"));
        DataProvider.saveNewUser(new GroupMate("Сергей", "Рыба", "Федорович"));
        DataProvider.saveNewUser(new GroupMate("Грегорий", "Касперский", "Анатольевич"));
        DataProvider.saveNewUser(new GroupMate("Дарья", "Одинокая", "Викторовна"));
    }


    @OnClick(R.id.show_button)
    public void onShowButtonClicked(){
        Intent intent = new Intent(this, ShowActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.add_record)
    public void onAddButtonClicked(){
        RealmBaseOperation.makeWriteRequest(new RealmBaseOperation.WriteRealmMethod() {
            @Override
            public void execute(Realm realm) {
                GroupMate record = new GroupMate();
                record.setName("Апатий");
                record.setSecondName("Трубович");
                record.setPatronymic("Апатьевич");
                realm.copyToRealm(record);
            }
        });
        showToast("Added success");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.change_last_rec)
    public void onChangeButtonClicked(){
        RealmBaseOperation.makeWriteRequest(new RealmBaseOperation.WriteRealmMethod() {
            @Override
            public void execute(Realm realm) {
                lastDate = realm.where(GroupMate.class).maximumDate("createDate");
                GroupMate lastRecord = realm.where(GroupMate.class).equalTo("createDate", lastDate).findFirst();
                lastRecord.setName("Иван");
                lastRecord.setSecondName("Иванов");
                lastRecord.setPatronymic("Иванович");
            }
        });
        showToast("Changed success");
    }

    @OnClick(R.id.show_city_button)
    public void onShowCityButtonClicked(){
        Intent intent = new Intent(this, CityActivity.class);
        startActivity(intent);
    }
}
