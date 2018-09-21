package com.quickly.common;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import com.quickly.fragment.circle.Item;
import com.quickly.fragment.circle.ArticlePanel;
import com.quickly.fragment.circle.VideoPanel;

public class SmartPagerAdapter extends FragmentStatePagerAdapter {

        private final Item[] items;

        private  Fragment[] mSmartFragments;

        public SmartPagerAdapter(FragmentManager fragmentManager,Item... items) {
            super(fragmentManager);
            this.items = items;
            this.mSmartFragments = new Fragment[items.length];
        }

        @Override
        public int getCount() {
            return mSmartFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items[position].name;
        }

        @Override
        public Fragment getItem(int position) {

            if(mSmartFragments[position] == null && position < mSmartFragments.length) {

                if(position == 0) {
                    mSmartFragments[position] = new ArticlePanel();
                }else{
                    mSmartFragments[position] = new VideoPanel();
                }
            }
            return mSmartFragments[position];
        }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // super.destroyItem(container, position, object);
    }
}