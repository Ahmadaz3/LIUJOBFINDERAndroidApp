package com.example.seniorprojecttest2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdminViewPagerAdapter  extends FragmentStateAdapter {
    public AdminViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new PostRequests();
            case 1: return new AdminMessagesFragment();
            default: return new PostRequests();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
