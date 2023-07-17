package com.example.seniorprojecttest2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BusinessViewPagerAdapter extends FragmentStateAdapter {
    public BusinessViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new BusinessPostsFragment();
            case 1: return new AddPostFragment();
            case 2: return new AppliedFragment();
            default: return new BusinessPostsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
