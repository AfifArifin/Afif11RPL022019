package com.example.afif11rpl022019;


import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final MovieModelRealm mahasiswaModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(MovieModelRealm.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    mahasiswaModel.setId(nextId);
                    MovieModelRealm model = realm.copyToRealm(mahasiswaModel);


                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }


    // untuk memanggil semua data
    public List<MovieModelRealm> getAllMahasiswa(){
        RealmResults<MovieModelRealm> results = realm.where(MovieModelRealm.class).findAll();
        return results;
    }

//    // untuk meng-update data
//    public void update(final Integer id, final Integer nim, final String nama){
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                MovieModelRealm model = realm.where(MovieModelRealm.class)
//                        .equalTo("id", id)
//                        .findFirst();
//                model.setNim(nim);
//                model.setNama(nama);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                Log.e("pppp", "onSuccess: Update Successfully");
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                error.printStackTrace();
//            }
//        });
//    }

    // untuk menghapus data
    public void delete(Integer id){
        final RealmResults<MovieModelRealm> model = realm.where(MovieModelRealm.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}