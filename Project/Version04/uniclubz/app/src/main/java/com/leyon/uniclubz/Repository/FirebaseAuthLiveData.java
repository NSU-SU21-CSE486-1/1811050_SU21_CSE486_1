package com.leyon.uniclubz.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthLiveData extends LiveData<Boolean> {

    FirebaseAuth firebaseAuth;

    MyAuthStateListener listener = new MyAuthStateListener();

    public FirebaseAuthLiveData(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    protected void onActive() {
        super.onActive();
        firebaseAuth.addAuthStateListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        firebaseAuth.removeAuthStateListener(listener);
    }

    private class MyAuthStateListener implements FirebaseAuth.AuthStateListener {

        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            boolean loggedInStatus = ( firebaseAuth.getCurrentUser() != null );
            setValue(loggedInStatus);
        }
    }
}
